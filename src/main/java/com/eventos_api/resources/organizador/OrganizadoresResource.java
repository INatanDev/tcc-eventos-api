package com.eventos_api.resources.organizador;

import com.eventos_api.entities.organizador.Organizador;
import com.eventos_api.services.organizador.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/organizadores")
public class OrganizadoresResource {

    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping
    public ResponseEntity<List<Organizador>> getAllOrganizador() {
        List<Organizador> list = organizadorService.getAllOrganizador();
        return ResponseEntity.ok().body(list);
    }
}
