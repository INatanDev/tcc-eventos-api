package com.eventos_api.resources.organizador;

import com.eventos_api.dto.organizador.OrganizadorDTO;
import com.eventos_api.services.organizador.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/organizadores")
public class OrganizadoresResource {

    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping
    public ResponseEntity<Page<OrganizadorDTO>> getAllOrganizador(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy )
            {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy );

        Page<OrganizadorDTO> list = organizadorService.getAllOrganizadorPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> getOrganizadorById(@PathVariable Long id){
        OrganizadorDTO dto = organizadorService.getOrganizadorById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<OrganizadorDTO> InsertOrganizador(@RequestBody OrganizadorDTO dto){
        dto = organizadorService.insertOrganizador(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> atualizarOrganizador (@PathVariable Long id, @RequestBody OrganizadorDTO dto){
       dto = organizadorService.update(id, dto);
       return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizador (@PathVariable Long id){
        organizadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
