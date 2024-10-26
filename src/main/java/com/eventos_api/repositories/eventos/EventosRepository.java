package com.eventos_api.repositories.eventos;

import com.eventos_api.entities.eventos.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Evento, Long> {
}
