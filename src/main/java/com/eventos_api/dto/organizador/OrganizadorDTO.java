package com.eventos_api.dto.organizador;

import com.eventos_api.entities.organizador.Organizador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganizadorDTO {

    private Long id;

    private String nome;

    private String contato;

    public OrganizadorDTO(Organizador entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.contato = entity.getContato();
    }
}
