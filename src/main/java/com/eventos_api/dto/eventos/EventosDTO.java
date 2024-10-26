package com.eventos_api.dto.eventos;

import com.eventos_api.entities.eventos.Evento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventosDTO {

    private Long id;

    private String nomeEvento;

    private Date dataDoEvento;

    private String descricao;

    private Long capacidadeMaxima;

    public EventosDTO(Evento entity){
        this.id = entity.getId();
        this.nomeEvento = entity.getNomeEvento();
        this.dataDoEvento = entity.getDataDoEvento();
        this.descricao = entity.getDescricao();
        this.capacidadeMaxima = entity.getCapacidadeMaxima();
    }
}
