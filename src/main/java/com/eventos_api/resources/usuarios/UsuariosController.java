package com.eventos_api.resources.usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @GetMapping
    public ResponseEntity<String> getUsuarios(){
        return ResponseEntity.ok().body("Usuario criado");
    }
}
