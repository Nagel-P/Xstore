package com.xstore.repository;

import com.xstore.database.DbConnection;
import com.xstore.model.Pedido;
import com.xstore.model.Produto;
import com.xstore.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepo {

    private final UsuarioRepo usuarioRepo =
            new UsuarioRepo();

    private final ProdutoRepo produtoRepo =
            new ProdutoRepo();

    public void salvar(
            Pedido pedido
    ) {

        String sqlPedido =
                """
                INSERT INTO pedido
                (
                    usuario_id,
                    preco_produtos,
                    preco_frete,
                    preco_total
                )
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn =
                        DbConnection.getConnection()
        ) {

            PreparedStatement stmtPedido =
                    conn.prepareStatement(
                            sqlPedido,
                            Statement.RETURN_GENERATED_KEYS
                    );

            stmtPedido.setLong(
                    1,
                    pedido.getUsuario().getId()
            );

            stmtPedido.setDouble(
                    2,
                    pedido.getprecoProdutos()
            );

            stmtPedido.setDouble(
                    3,
                    pedido.getprecoFrete()
            );

            stmtPedido.setDouble(
                    4,
                    pedido.getprecoTotal()
            );

            stmtPedido.executeUpdate();

            ResultSet generatedKeys =
                    stmtPedido.getGeneratedKeys();

            Long pedidoId = null;

            if (generatedKeys.next()) {

                pedidoId =
                        generatedKeys.getLong(1);
            }

            String sqlPedidoProduto =
                    """
                    INSERT INTO pedido_produto
                    (
                        pedido_id,
                        produto_id
                    )
                    VALUES (?, ?)
                    """;

            for (Produto produto :
                    pedido.getProdutos()) {

                PreparedStatement stmtProduto =
                        conn.prepareStatement(
                                sqlPedidoProduto
                        );

                stmtProduto.setLong(
                        1,
                        pedidoId
                );

                stmtProduto.setLong(
                        2,
                        produto.getId()
                );

                stmtProduto.executeUpdate();
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao salvar pedido"
            );
        }
    }

    public List<Pedido> listarTodos() {

        List<Pedido> pedidos =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pedido";

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Pedido pedido =
                        montarPedido(
                                rs,
                                conn
                        );

                pedidos.add(pedido);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao listar pedidos"
            );
        }

        return pedidos;
    }

    public Pedido buscarPorId(
            Long id
    ) {

        String sql =
                """
                SELECT *
                FROM pedido
                WHERE id = ?
                """;

        try (
                Connection conn =
                        DbConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return montarPedido(
                        rs,
                        conn
                );
            }

            return null;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao buscar pedido"
            );
        }
    }

    private Pedido montarPedido(
            ResultSet rs,
            Connection conn
    ) throws Exception {

        Pedido pedido =
                new Pedido();

        pedido.setId(
                rs.getInt("id")
        );

        Long usuarioId =
                rs.getLong(
                        "usuario_id"
                );

        Usuario usuario =
                usuarioRepo.buscarPorId(
                        usuarioId
                );

        pedido.setUsuario(usuario);

        List<Produto> produtos =
                new ArrayList<>();

        String sqlProdutos =
                """
                SELECT produto_id
                FROM pedido_produto
                WHERE pedido_id = ?
                """;

        PreparedStatement stmtProdutos =
                conn.prepareStatement(
                        sqlProdutos
                );

        stmtProdutos.setLong(
                1,
                rs.getLong("id")
        );

        ResultSet rsProdutos =
                stmtProdutos.executeQuery();

        while (rsProdutos.next()) {

            Long produtoId =
                    rsProdutos.getLong(
                            "produto_id"
                    );

            Produto produto =
                    produtoRepo.buscarPorId(
                            produtoId
                    );

            if (produto != null) {

                produtos.add(produto);
            }
        }

        pedido.setProdutos(produtos);

        pedido.calcPreco();

        return pedido;
    }
}