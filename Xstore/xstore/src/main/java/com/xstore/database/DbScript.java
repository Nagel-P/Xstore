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
                    Paths.get("schema.sql")
            );

            stmt.execute(sql);

            System.out.println(
                    "Banco inicializado!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}