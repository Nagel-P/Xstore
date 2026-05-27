package com.xstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xstore.model.ViaCepResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {

    private final HttpClient client =
            HttpClient.newHttpClient();

    private final ObjectMapper mapper =
            new ObjectMapper();

    public ViaCepResponse buscarCep(
            String cep
    ) throws Exception {

        String url =
                "https://viacep.com.br/ws/"
                + cep
                + "/json/";

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        return mapper.readValue(
                response.body(),
                ViaCepResponse.class
        );
    }
}