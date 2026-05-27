package com.xstore.service;

import com.xstore.model.Usuario;
import com.xstore.model.ViaCepResponse;
import com.xstore.repository.UsuarioRepo;

public class UsuarioService {

    private final ViaCepService viaCepService =
            new ViaCepService();

    private final UsuarioRepo repo =
            new UsuarioRepo();

    public Usuario preencherEndereco(
            Usuario usuario
    ) throws Exception {

        ViaCepResponse response =
                viaCepService.buscarCep(
                        usuario.getCep()
                );

        usuario.setRua(
                response.getLogradouro()
        );

        usuario.setBairro(
                response.getBairro()
        );

        usuario.setCidade(
                response.getLocalidade()
        );

        usuario.setUf(
                response.getUf()
        );

        return usuario;
    }

    public Usuario buscarPorEmail(
            String email
    ) {

        Usuario usuario =
                repo.buscarPorEmail(email);

        if (usuario == null) {

            throw new RuntimeException(
                    "Usuário não encontrado"
            );
        }

        return usuario;
    }
}