package com.eventos_api.repositories.inscricao;

import com.eventos_api.entities.inscricao.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
}
