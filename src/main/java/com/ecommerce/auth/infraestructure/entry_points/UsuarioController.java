package com.ecommerce.auth.infraestructure.entry_points;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.usecase.UsuarioUseCase;
import com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository.UsuarioData;
import com.ecommerce.auth.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;
    private final MapperUsuario mapperUsuario;

    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioData usuarioData){
        Usuario usuario = mapperUsuario.toUsuario(usuarioData);
        Usuario usuarioValidadoGuardado = usuarioUseCase.guardarUsuario(usuario);

        if(usuarioValidadoGuardado.getIdUsuario() != null){
            return new ResponseEntity<>(usuarioValidadoGuardado, HttpStatus.OK);
        }

        return new ResponseEntity<>(usuarioValidadoGuardado, HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdUsuario(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioUseCase.buscarPorIdUsuario(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioData usuarioData){
        try{
            Usuario usuario = mapperUsuario.toUsuario(usuarioData);
            Usuario usuarioValidadActualizado = usuarioUseCase.actualizaUsuario(usuario);
            return new ResponseEntity<>(usuarioValidadActualizado, HttpStatus.OK);
        }catch (Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            usuarioUseCase.eliminarPorIdUsuario(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}