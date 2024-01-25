package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.security.Sha256PasswordEncoder;
import com.bake.BakeFLowBackend.service.UsuarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<BaseResponse> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(BaseResponse.success("Usuario registrado con éxito", usuarioService.registrarUsuario(usuarioDTO)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> obtenerUsuarios() {
        return ResponseEntity.ok(BaseResponse.success("Usuarios obtenidos con éxito", usuarioService.obtenerUsuarios()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> obtenerUsuario(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(id);
        return ResponseEntity.ok(BaseResponse.success("Usuario obtenido con éxito", usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(BaseResponse.success("Usuario actualizado con éxito", usuarioService.actualizarUsuario(id,usuarioDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(BaseResponse.success("Usuario eliminado con éxito", null));
    }


}
