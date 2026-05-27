package com.xstore.repository;

import com.xstore.database.DbConnection;
import com.xstore.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo {

    public void salvar(
            Usuario usuario
    ) {

        String sql =
                """
                INSERT INTO usuario
                (
                    email,
                    nome,
                    cpf,
                    telefone,
                    cep,
                    rua,
                    numero,
                    bairro,
                    cidade,
                    uf
                )
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    usuario.getEmail()
            );

            stmt.setString(
                    2,
                    usuario.getNome()
            );

            stmt.setString(
                    3,
                    usuario.getCpf()
            );

            stmt.setString(
                    4,
                    usuario.getTelefone()
            );

            stmt.setString(
                    5,
                    usuario.getCep()
            );

            stmt.setString(
                    6,
                    usuario.getRua()
            );

            stmt.setString(
                    7,
                    usuario.getNumero()
            );

            stmt.setString(
                    8,
                    usuario.getBairro()
            );

            stmt.setString(
                    9,
                    usuario.getCidade()
            );

            stmt.setString(
                    10,
                    usuario.getUf()
            );

            stmt.executeUpdate();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao salvar usuário"
            );
        }
    }

    public List<Usuario> listarTodos() {

        List<Usuario> usuarios =
                new ArrayList<>();

        String sql =
                "SELECT * FROM usuario";

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Usuario usuario =
                        new Usuario();

                usuario.setEmail(
                        rs.getString("email")
                );

                usuario.setNome(
                        rs.getString("nome")
                );

                usuario.setCpf(
                        rs.getString("cpf")
                );

                usuario.setTelefone(
                        rs.getString("telefone")
                );

                usuario.setCep(
                        rs.getString("cep")
                );

                usuario.setRua(
                        rs.getString("rua")
                );

                usuario.setNumero(
                        rs.getString("numero")
                );

                usuario.setBairro(
                        rs.getString("bairro")
                );

                usuario.setCidade(
                        rs.getString("cidade")
                );

                usuario.setUf(
                        rs.getString("uf")
                );

                usuarios.add(usuario);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao listar usuários"
            );
        }

        

        return usuarios;
    }

    public Usuario buscarPorEmail(
        String email
) {

    String sql =
            """
            SELECT * FROM usuario
            WHERE email = ?
            """;

    try (
            Connection conn =
                    DbConnection.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)
    ) {

        stmt.setString(1, email);

        ResultSet rs =
                stmt.executeQuery();

        if (rs.next()) {

            Usuario usuario =
                    new Usuario();

            usuario.setEmail(
                    rs.getString("email")
            );

            usuario.setNome(
                    rs.getString("nome")
            );

            usuario.setCpf(
                    rs.getString("cpf")
            );

            usuario.setTelefone(
                    rs.getString("telefone")
            );

            usuario.setCep(
                    rs.getString("cep")
            );

            usuario.setRua(
                    rs.getString("rua")
            );

            usuario.setNumero(
                    rs.getString("numero")
            );

            usuario.setBairro(
                    rs.getString("bairro")
            );

            usuario.setCidade(
                    rs.getString("cidade")
            );

            usuario.setUf(
                    rs.getString("uf")
            );

            return usuario;
        }

        return null;

    } catch (Exception e) {

        throw new RuntimeException(
                "Erro ao buscar usuário"
        );
    }
}
}

