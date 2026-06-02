package com.xstore.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.xstore.model.Usuario;
import com.xstore.service.UsuarioService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class UsuarioHandler implements HttpHandler {

    private final ObjectMapper mapper =
            new ObjectMapper();

    private final UsuarioService service =
            new UsuarioService();

    @Override
    public void handle(
            HttpExchange exchange
    ) throws IOException {

        try {

            String metodo =
                    exchange.getRequestMethod();

            switch (metodo) {

                case "POST" -> criar(exchange);

                case "GET" -> {

                    String path =
                            exchange.getRequestURI()
                                    .getPath();

                    String[] partes =
                            path.split("/");

                    if (partes.length <= 2) {

                        listar(exchange);

                    } else {

                        buscarPorEmail(exchange);
                    }
                }

                default ->
                        exchange.sendResponseHeaders(
                                405,
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

            enviarResposta(
                    exchange,
                    erro,
                    500
            );
        }
    }

    private void criar(
            HttpExchange exchange
    ) throws Exception {

        InputStream is =
                exchange.getRequestBody();

        Usuario usuario =
                mapper.readValue(
                        is,
                        Usuario.class
                );

        service.salvar(usuario);

        String json =
                mapper.writeValueAsString(
                        usuario
                );

        enviarResposta(
                exchange,
                json,
                201
        );
    }

    private void listar(
            HttpExchange exchange
    ) throws Exception {

        List<Usuario> usuarios =
                service.listarTodos();

        String json =
                mapper.writeValueAsString(
                        usuarios
                );

        enviarResposta(
                exchange,
                json,
                200
        );
    }

    private void buscarPorEmail(
            HttpExchange exchange
    ) throws Exception {

        String path =
                exchange.getRequestURI()
                        .getPath();

        String[] partes =
                path.split("/");

        String email =
                partes[2];

        Usuario usuario =
                service.buscarPorEmail(
                        email
                );

        String json =
                mapper.writeValueAsString(
                        usuario
                );

        enviarResposta(
                exchange,
                json,
                200
        );
    }

    private void enviarResposta(
            HttpExchange exchange,
            String resposta,
            int status
    ) throws IOException {

        exchange.getResponseHeaders()
                .add(
                        "Content-Type",
                        "application/json"
                );

        exchange.sendResponseHeaders(
                status,
                resposta.getBytes().length
        );

        OutputStream os =
                exchange.getResponseBody();

        os.write(
                resposta.getBytes()
        );

        os.close();
    }
}