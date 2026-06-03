package com.xstore.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.xstore.model.Pedido;
import com.xstore.service.PedidoService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class PedidoHandler implements HttpHandler {

    private final PedidoService service =
            new PedidoService();

    private final ObjectMapper mapper =
            new ObjectMapper();

    @Override
    public void handle(
            HttpExchange exchange
    ) throws IOException {

        try {

            String metodo =
                    exchange.getRequestMethod();

            String path =
                    exchange.getRequestURI()
                            .getPath();

            if (
                    metodo.equals("POST")
                            && path.equals("/pedidos")
            ) {

                criar(exchange);

            } else if (
                    metodo.equals("GET")
                            && path.equals("/pedidos")
            ) {

                listarTodos(exchange);

            } else if (
                    metodo.equals("GET")
                            && path.startsWith("/pedidos/")
            ) {

                buscarPorId(exchange);

            } else {

                exchange.sendResponseHeaders(
                        404,
                        -1
                );
            }

        } catch (Exception e) {

            String erro =
                    """
                    {
                      "erro":"%s"
                    }
                    """.formatted(
                            e.getMessage()
                    );

            responder(
                    exchange,
                    erro,
                    500
            );

            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void criar(
            HttpExchange exchange
    ) throws Exception {

        InputStream body =
                exchange.getRequestBody();

        Map<?, ?> dados =
                mapper.readValue(
                        body,
                        Map.class
                );

        Long usuarioId =
                Long.valueOf(
                        dados.get("usuarioId")
                                .toString()
                );

        List<Integer> produtosInt =
                (List<Integer>) dados.get(
                        "produtos"
                );

        List<Long> produtos =
                produtosInt
                        .stream()
                        .map(Integer::longValue)
                        .toList();

        Pedido pedido =
                service.criarPedido(
                        usuarioId,
                        produtos
                );

        String json =
                mapper.writeValueAsString(
                        pedido
                );

        responder(
                exchange,
                json,
                201
        );
    }

    private void listarTodos(
            HttpExchange exchange
    ) throws Exception {

        List<Pedido> pedidos =
                service.listarTodos();

        String json =
                mapper.writeValueAsString(
                        pedidos
                );

        responder(
                exchange,
                json,
                200
        );
    }

    private void buscarPorId(
            HttpExchange exchange
    ) throws Exception {

        String path =
                exchange.getRequestURI()
                        .getPath();

        Long id =
                Long.parseLong(
                        path.split("/")[2]
                );

        Pedido pedido =
                service.buscarPorId(id);

        String json =
                mapper.writeValueAsString(
                        pedido
                );

        responder(
                exchange,
                json,
                200
        );
    }

    private void responder(
            HttpExchange exchange,
            String json,
            int status
    ) throws IOException {

        exchange.getResponseHeaders()
                .add(
                        "Content-Type",
                        "application/json"
                );

        exchange.sendResponseHeaders(
                status,
                json.getBytes().length
        );

        OutputStream os =
                exchange.getResponseBody();

        os.write(
                json.getBytes()
        );

        os.close();
    }
}