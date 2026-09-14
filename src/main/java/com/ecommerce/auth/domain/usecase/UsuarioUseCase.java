package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public Usuario guardarUsuario(Usuario usuario){
        validarCampos(usuario);
        return usuarioGateway.guardarUsuario(usuario);
    }

    public Usuario buscarPorIdUsuario(Long id) {
        return usuarioGateway.buscarPorIdUsuario(id);
    }

    public Usuario actualizaUsuario(Usuario usuario){
        if(usuario.getIdUsuario() == null){
            throw new IllegalArgumentException("El id es obligatorio para actualizar");
        }
        validarCampos(usuario);
        return usuarioGateway.actualizarUsuario(usuario);
    }

    public void eliminarPorIdUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id debe ser válido");
        }
        usuarioGateway.eliminarPorIdUsuario(id);
    }

    private void validarCampos (Usuario usuario) {
        if(usuario == null){
            throw new NullPointerException("Ojo con eso!!");
        }

        if(usuario.getCorreo() == null || usuario.getCorreo().isBlank()){
            throw new NullPointerException("El correo es obligatorio");
        }

        if(usuario.getNombre() == null || usuario.getNombre().isBlank()){
            throw new NullPointerException("El nombre es obligatorio");
        }

        if(usuario.getEdad() == null){
            throw new NullPointerException("La edad es obligatorio");
        }

        if(usuario.getEdad() < 18){
            throw new RuntimeException("El usuario debe ser mayor de edad");
        }

        if(usuario.getClave() == null || usuario.getClave().isBlank()){
            throw new NullPointerException("La clave es obligatorio");
        }

        if(usuario.getNumeroTelefonico() == null || usuario.getNumeroTelefonico().isBlank()){
            throw new NullPointerException("El número telefónico es obligatorio");
        }

        if(usuario.getRol() == null){
            throw new NullPointerException("El rol es obligatorio");
        }
    }
}
