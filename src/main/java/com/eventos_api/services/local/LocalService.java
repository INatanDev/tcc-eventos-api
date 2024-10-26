package com.eventos_api.services.local;

import com.eventos_api.dto.local.LocalDTO;
import com.eventos_api.entities.locais.Local;
import com.eventos_api.repositories.local.LocalRepository;
import com.eventos_api.services.exceptions.DatabaseException;
import com.eventos_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LocalService {

    @Autowired
    private LocalRepository repository;

    @Transactional(readOnly = true)
    public Page<LocalDTO> findAll(Pageable pageable) {
        Page<Local> list = repository.findAll(pageable);
        return list.map(l -> new LocalDTO(l));
    }

    @Transactional(readOnly = true)
    public LocalDTO findLocalById(Long id) {
        Optional<Local> obj = repository.findById(id);
        Local entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new LocalDTO(entity);
    }

    @Transactional
    public LocalDTO insertLocal(LocalDTO dto) {
        Local entity = new Local();
        entity.setNomeDoLocal(dto.getNomeDoLocal());
        entity.setEndereco(dto.getEndereco());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity = repository.save(entity);
        return new LocalDTO(entity);
    }

    @Transactional
    public LocalDTO updateLocal(Long id, LocalDTO dto) {
        try {
            Local entity = repository.getReferenceById(id);
            entity.setNomeDoLocal(dto.getNomeDoLocal());
            entity.setEndereco(dto.getEndereco());
            entity.setCidade(dto.getCidade());
            entity.setEstado(dto.getEstado());
            entity = repository.save(entity);
            return new LocalDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteLocal(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ressource not found: " + id);
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
