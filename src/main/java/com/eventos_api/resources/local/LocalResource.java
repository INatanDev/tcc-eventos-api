package com.eventos_api.resources.local;

import com.eventos_api.dto.local.LocalDTO;
import com.eventos_api.services.local.LocalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/local")
public class LocalResource {

    @Autowired
    private LocalService service;

    @GetMapping
    public ResponseEntity<Page<LocalDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                       @RequestParam(value = "orderBy", defaultValue = "nomeDoLocal") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<LocalDTO> list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> findById(@PathVariable Long id){
        LocalDTO dto = service.findLocalById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<LocalDTO> insert(@RequestBody LocalDTO dto){
        dto = service.insertLocal(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    public ResponseEntity<LocalDTO> update(@PathVariable Long id ,@RequestBody LocalDTO dto){
        dto = service.updateLocal(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
