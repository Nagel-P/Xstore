package com.xstore;

import com.sun.net.httpserver.HttpServer;
import com.xstore.database.H2Console;
import com.xstore.database.DbScript;
import com.xstore.handler.ProdutoHandler;

import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args)
            throws Exception {

        DbScript.executarScript();

        H2Console.iniciarConsole();

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8080),
                        0
                );

        server.createContext(
                "/produtos",
                new ProdutoHandler()
        );

        server.setExecutor(null);

        server.start();

        System.out.println(
                "Servidor iniciado na porta 8080"
        );
    }
}