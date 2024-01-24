package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
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
/*
    @Autowired
    private Sha256PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setContraseña(passwordEncoder.encode(usuarioDTO.getContraseña()));
        usuarioService.registrarUsuario(usuarioDTO);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> obtenerUsuario(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(id);
        return ResponseEntity.ok(BaseResponse.success("Usuario obtenido con éxito", usuarioDTO));
    }
}
