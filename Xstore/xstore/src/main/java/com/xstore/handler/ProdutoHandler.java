package com.xstore.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.xstore.dto.ProdutoDto;
import com.xstore.model.Produto;
import com.xstore.service.ProdutoService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoHandler implements HttpHandler {

    private ProdutoService service =
            new ProdutoService();

    private ObjectMapper mapper =
            new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange)
            throws IOException {

        try {

            String metodo =
                    exchange.getRequestMethod();

            String path =
                    exchange.getRequestURI().getPath();

            if(metodo.equals("GET")
                    && path.equals("/produtos")) {

                listarTodos(exchange);
            }

            else if(metodo.equals("GET")
                    && path.matches("/produtos/\\d+")) {

                buscarPorId(exchange);
            }

            else if(metodo.equals("POST")
                    && path.equals("/produtos")) {

                salvar(exchange);
            }

            else if(metodo.equals("PUT")
                    && path.matches("/produtos/\\d+")) {

                atualizar(exchange);
            }

            else if(metodo.equals("DELETE")
                    && path.matches("/produtos/\\d+")) {

                deletar(exchange);
            }

            else {

                exchange.sendResponseHeaders(404, -1);
            }

        } catch (Exception e) {

            enviarErro(exchange, e.getMessage());
        }
    }

    private void listarTodos(
            HttpExchange exchange
    ) throws IOException {

        List<ProdutoDto> produtos =
                service.listarTodos()
                        .stream()
                        .map(ProdutoDto::new)
                        .collect(Collectors.toList());

        String json =
                mapper.writeValueAsString(produtos);

        enviarResposta(exchange, json, 200);
    }

    private void buscarPorId(
            HttpExchange exchange
    ) throws IOException {

        String path =
                exchange.getRequestURI().getPath();

        Long id = Long.parseLong(
                path.split("/")[2]
        );

        ProdutoDto dto =
                new ProdutoDto(
                        service.buscarPorId(id)
                );

        String json =
                mapper.writeValueAsString(dto);

        enviarResposta(exchange, json, 200);
    }

    private void salvar(
            HttpExchange exchange
    ) throws IOException {

        Produto produto =
                mapper.readValue(
                        exchange.getRequestBody(),
                        Produto.class
                );

        service.salvar(produto);

        enviarResposta(
                exchange,
                "{\"mensagem\":\"Produto criado\"}",
                201
        );
    }

    private void atualizar(
            HttpExchange exchange
    ) throws IOException {

        String path =
                exchange.getRequestURI().getPath();

        Long id = Long.parseLong(
                path.split("/")[2]
        );

        Produto produto =
                mapper.readValue(
                        exchange.getRequestBody(),
                        Produto.class
                );

        service.atualizar(id, produto);

        enviarResposta(
                exchange,
                "{\"mensagem\":\"Produto atualizado\"}",
                200
        );
    }

    private void deletar(
            HttpExchange exchange
    ) throws IOException {

        String path =
                exchange.getRequestURI().getPath();

        Long id = Long.parseLong(
                path.split("/")[2]
        );

        service.deletar(id);

        enviarResposta(
                exchange,
                "{\"mensagem\":\"Produto deletado\"}",
                200
        );
    }

    private void enviarResposta(
            HttpExchange exchange,
            String resposta,
            int status
    ) throws IOException {

        exchange.getResponseHeaders().add(
                "Content-Type",
                "application/json"
        );

        exchange.sendResponseHeaders(
                status,
                resposta.getBytes().length
        );

        OutputStream os =
                exchange.getResponseBody();

        os.write(resposta.getBytes());

        os.close();
    }

    private void enviarErro(
            HttpExchange exchange,
            String erro
    ) throws IOException {

        String json =
                "{\"erro\":\"" + erro + "\"}";

        enviarResposta(exchange, json, 500);
    }
}