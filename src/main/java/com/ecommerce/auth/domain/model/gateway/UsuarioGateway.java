package com.ecommerce.auth.domain.model.gateway;

import com.ecommerce.auth.domain.model.Usuario;

public interface UsuarioGateway {
    Usuario guardarUsuario(Usuario usuario);
    Usuario buscarPorIdUsuario(Long idUsuario);
    Usuario actualizarUsuario(Usuario usuario);
    void eliminarPorIdUsuario(Long idUsuario);
}
