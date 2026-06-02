package com.xstore.service;

import com.xstore.model.Usuario;
import com.xstore.model.ViaCepResponse;
import com.xstore.repository.UsuarioRepo;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepo repo =
            new UsuarioRepo();

    private final ViaCepService viaCepService =
            new ViaCepService();

    public Usuario salvar(
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

        repo.salvar(usuario);

        return usuario;
    }

    public List<Usuario> listarTodos() {

        return repo.listarTodos();
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