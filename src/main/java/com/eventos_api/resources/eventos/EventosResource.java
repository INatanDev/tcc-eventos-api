package com.eventos_api.resources.eventos;

import com.eventos_api.dto.eventos.EventosDTO;
import com.eventos_api.services.eventos.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/eventos")
public class EventosResource {

    @Autowired
    private EventosService service;

    @GetMapping
    public ResponseEntity<Page<EventosDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                         @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                         @RequestParam(value = "orderBy", defaultValue = "nomeDoLocal") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<EventosDTO> list = service.getAllEventos(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosDTO> findById(@PathVariable Long id){
        EventosDTO dto = service.getEventoId(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<EventosDTO> insert(@RequestBody EventosDTO dto){
        dto = service.insertEvento(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    public ResponseEntity<EventosDTO> update(@PathVariable Long id ,@RequestBody EventosDTO dto){
        dto = service.updateEvento(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
