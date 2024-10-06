package com.eventos_api.services.organizador;

import com.eventos_api.entities.organizador.Organizador;
import com.eventos_api.repositories.organizador.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public List<Organizador> getAllOrganizador(){
        return organizadorRepository.findAll();
    }
}
