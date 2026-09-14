package com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import com.ecommerce.auth.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioDataGatewayImpl implements UsuarioGateway {

    private final MapperUsuario mapperUsuario;
    private final UsuarioDataJpaRepository repository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public Usuario buscarPorIdUsuario(Long id) {
//        UsuarioData usuarioData = repository.findById(id).get();
//        return mapperUsuario.toUsuario(usuarioData);
        return repository.findById(id)
                .map(mapperUsuario::toUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);

        if(!repository.existsById(usuario.getIdUsuario())){
            throw new RuntimeException("Usuario no existe");
        }
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarPorIdUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("El usuario no existe");
        }
        repository.deleteById(id);
    }
}
