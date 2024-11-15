package com.eventos_api.dto.inscricao;


import com.eventos_api.dto.eventos.EventosDTO;
import com.eventos_api.entities.eventos.Evento;
import com.eventos_api.entities.inscricao.Inscricao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InscricaoDTO {

    private Long id;

    private String usuariosId;

    private Long eventoId;

    private List<EventosDTO> eventos = new ArrayList<>();

    /*
    caso tenha erro na requisicao dessa parte this.usuariosId = Long.valueOf(entity.getUsuarios().getId());
    verificar o tipo
     */
    public InscricaoDTO(Inscricao entity){
        this.id = entity.getId();
        this.usuariosId = entity.getUsuarios().getId();
        this.eventoId = entity.getEvento().getId();
    }

    public InscricaoDTO(Inscricao entity, Set<Evento> eventos){
        this(entity);
        eventos.forEach(x -> this.eventos.add(new EventosDTO(x)));
    }
}
