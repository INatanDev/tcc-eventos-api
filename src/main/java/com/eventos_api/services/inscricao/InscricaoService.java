package com.eventos_api.services.inscricao;


import com.eventos_api.dto.inscricao.InscricaoDTO;
import com.eventos_api.entities.eventos.Evento;
import com.eventos_api.entities.inscricao.Inscricao;
import com.eventos_api.entities.user.Usuarios;
import com.eventos_api.repositories.eventos.EventosRepository;
import com.eventos_api.repositories.inscricao.InscricaoRepository;
import com.eventos_api.repositories.usuarios.UsuarioRepository;
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
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private EventosRepository eventoRepository;

    @Transactional(readOnly = true)
    public Page<InscricaoDTO> getAllInscricao(Pageable pageable) {
        Page<Inscricao> list = inscricaoRepository.findAll(pageable);
        return list.map(x -> new InscricaoDTO(x));
    }

    @Transactional(readOnly = true)
    public InscricaoDTO getInscricaoId(Long id) {
        Optional<Inscricao> obj = inscricaoRepository.findById(id);
        Inscricao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Inscricao not found"));
        return new InscricaoDTO(entity);
    }

    @Transactional
    public InscricaoDTO insertInscricao(InscricaoDTO dto) {
        Inscricao entity = new Inscricao();

        Usuarios usuario = usuarioRepository.findById(dto.getUsuariosId()).orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        Evento evento = eventoRepository.findById(dto.getEventoId()).orElseThrow(() -> new ResourceNotFoundException("Evento not found"));

        entity.setUsuarios(usuario);
        entity.setEvento(evento);

        entity = inscricaoRepository.save(entity);
        return new InscricaoDTO(entity);
    }

    @Transactional
    public InscricaoDTO updateInscricao(Long id, InscricaoDTO dto) {
        try {
            Inscricao entity = inscricaoRepository.getReferenceById(id);

            Usuarios usuario = usuarioRepository.findById(dto.getUsuariosId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
            Evento evento = eventoRepository.findById(dto.getEventoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Evento not found"));

            entity.setUsuarios(usuario);
            entity.setEvento(evento);

            entity = inscricaoRepository.save(entity);
            return new InscricaoDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Inscricao not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteInscricao(Long id) {
        if (!inscricaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inscricao not found: " + id);
        }

        try {
            inscricaoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}

