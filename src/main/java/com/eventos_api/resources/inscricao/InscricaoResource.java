package com.eventos_api.resources.inscricao;

import com.eventos_api.dto.inscricao.InscricaoDTO;

import com.eventos_api.services.inscricao.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/Inscricao")
public class InscricaoResource {

    @Autowired
    private InscricaoService service;

    @GetMapping
    public ResponseEntity<Page<InscricaoDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<InscricaoDTO> list = service.getAllInscricao(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> findById(@PathVariable Long id){
        InscricaoDTO dto = service.getInscricaoId(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<InscricaoDTO> insert(@RequestBody InscricaoDTO dto){
        dto = service.insertInscricao(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    public ResponseEntity<InscricaoDTO> update(@PathVariable Long id ,@RequestBody InscricaoDTO dto){
        dto = service.updateInscricao(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
