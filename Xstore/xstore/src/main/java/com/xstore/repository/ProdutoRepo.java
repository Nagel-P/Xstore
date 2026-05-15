package com.xstore.repository;

import com.xstore.database.DbConnection;
import com.xstore.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepo {

    public List<Produto> listarTodos() {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        try (
                Connection conn =
                        DbConnection.getConnection();

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(
                        rs.getLong("id")
                );

                produto.setNome(
                        rs.getString("nome")
                );

                produto.setDescricao(
                        rs.getString("descricao")
                );

                produto.setPreco(
                        rs.getDouble("preco")
                );

                produto.setEstoque(
                        rs.getInt("estoque")
                );

                produto.setCategoriaId(
                        rs.getLong("categoria_id")
                );

                produtos.add(produto);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return produtos;
    }

    public Produto buscarPorId(Long id) {

        String sql =
                "SELECT * FROM produto WHERE id = ?";

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Produto produto = new Produto();

                produto.setId(
                        rs.getLong("id")
                );

                produto.setNome(
                        rs.getString("nome")
                );

                produto.setDescricao(
                        rs.getString("descricao")
                );

                produto.setPreco(
                        rs.getDouble("preco")
                );

                produto.setEstoque(
                        rs.getInt("estoque")
                );

                produto.setCategoriaId(
                        rs.getLong("categoria_id")
                );

                return produto;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void salvar(Produto produto) {

        String sql = """
            INSERT INTO produto
            (nome, descricao, preco, estoque, categoria_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setLong(5, produto.getCategoriaId());

            stmt.execute();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Produto produto) {

        String sql = """
            UPDATE produto
            SET nome = ?,
                descricao = ?,
                preco = ?,
                estoque = ?,
                categoria_id = ?
            WHERE id = ?
        """;

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setLong(5, produto.getCategoriaId());
            stmt.setLong(6, id);

            stmt.execute();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void deletar(Long id) {

        String sql =
                "DELETE FROM produto WHERE id = ?";

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            stmt.execute();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}