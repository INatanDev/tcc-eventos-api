package com.eventos_api.services.organizador;

import com.eventos_api.dto.organizador.OrganizadorDTO;
import com.eventos_api.entities.organizador.Organizador;
import com.eventos_api.repositories.organizador.OrganizadorRepository;
import com.eventos_api.services.exceptions.DatabaseException;
import com.eventos_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Transactional(readOnly = true)
    public Page<OrganizadorDTO> getAllOrganizadorPaged(PageRequest pageRequest){
        Page<Organizador> list = organizadorRepository.findAll(pageRequest);
        return list.map(x -> new OrganizadorDTO(x));
    }

    @Transactional(readOnly = true)
    public OrganizadorDTO getOrganizadorById(Long id){
        Optional<Organizador> obj = organizadorRepository.findById(id);
        Organizador entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new OrganizadorDTO(entity);
    }

    @Transactional
    public OrganizadorDTO insertOrganizador(OrganizadorDTO dto) {
        Organizador entity = new Organizador();
        entity.setNome(dto.getNome());
        entity.setContato(dto.getContato());
        entity = organizadorRepository.save(entity);
        return new OrganizadorDTO(entity);
    }

    @Transactional
    public OrganizadorDTO update(Long id, OrganizadorDTO dto) {
        try {
            Organizador entity = organizadorRepository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity.setContato(dto.getContato());
            entity = organizadorRepository.save(entity);
            return new OrganizadorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!organizadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado " );
        }

        try {
            organizadorRepository.deleteById(id);

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
