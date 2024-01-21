package com.bake.BakeFLowBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    /*@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Sha256PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setContraseña(passwordEncoder.encode(usuarioDTO.getContraseña()));
        usuarioService.registrarUsuario(usuarioDTO);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }*/

    @GetMapping("/hola")
    public ResponseEntity<String> hola() {
        return ResponseEntity.ok("Hola");
    }
}
