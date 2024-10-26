package com.eventos_api.services.eventos;

import com.eventos_api.dto.eventos.EventosDTO;
import com.eventos_api.entities.eventos.Evento;
import com.eventos_api.repositories.eventos.EventosRepository;
import com.eventos_api.services.exceptions.DatabaseException;
import com.eventos_api.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventosService {

    @Autowired
    private EventosRepository repository;

    @Transactional(readOnly = true)
    public Page<EventosDTO> getAllEventos(Pageable pageable) {
        Page<Evento> list = repository.findAll(pageable);
        return list.map(x -> new EventosDTO(x));
    }

    @Transactional(readOnly = true)
    public EventosDTO getEventoId(Long id) {
        Optional<Evento> obj = repository.findById(id);
        Evento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Evento not found"));
        return new EventosDTO(entity);
    }

    @Transactional
    public EventosDTO insertEvento(EventosDTO dto) {
        Evento entity = new Evento();
        entity.setNomeEvento(dto.getNomeEvento());
        entity.setDataDoEvento(dto.getDataDoEvento());
        entity.setDescricao(dto.getDescricao());
        entity.setCapacidadeMaxima(dto.getCapacidadeMaxima());
        entity = repository.save(entity);
        return new EventosDTO(entity);
    }

    @Transactional
    public EventosDTO updateEvento(Long id, EventosDTO dto) {
        try {
            Evento entity = repository.getReferenceById(id);
            entity.setNomeEvento(dto.getNomeEvento());
            entity.setDataDoEvento(dto.getDataDoEvento());
            entity.setDescricao(dto.getDescricao());
            entity.setCapacidadeMaxima(dto.getCapacidadeMaxima());
            entity = repository.save(entity);
            return new EventosDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Evento not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteEvento(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Evento not found: " + id);
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
