package com.xstore.database;

import org.h2.tools.Server;

public class H2Console {

    public static void iniciarConsole() {

        try {

            Server.createWebServer(
                    "-web",
                    "-webAllowOthers",
                    "-webPort",
                    "8082"
            ).start();

            System.out.println(
                    "H2 Console iniciado na porta 8082"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}