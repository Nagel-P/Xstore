package com.xstore.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DbScript {

    public static void executarScript() {

        try (
                Connection conn =
                        DbConnection.getConnection();

                Statement stmt =
                        conn.createStatement()
        ) {

            String sql = Files.readString(
                Paths.get("./Xstore/xstore/schema.sql")
            );

            String[] comandos =
                    sql.split(";");

            for (String comando : comandos) {

                if (!comando.trim().isEmpty()) {

                    System.out.println(
                            "EXECUTANDO:\n" + comando
                    );

                    stmt.execute(comando);
                }
            }

            System.out.println(
                    "Banco inicializado!"
            );

        } catch (Exception e) {

            System.out.println(
                    "ERRO NO SCRIPT SQL:"
            );

            e.printStackTrace();
        }
    }
}