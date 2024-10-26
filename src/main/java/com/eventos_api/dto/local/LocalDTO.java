package com.eventos_api.dto.local;

import com.eventos_api.entities.locais.Local;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalDTO {

    private Long id;

    private String nomeDoLocal;

    private String endereco;

    private String cidade;

    private char estado;

    public LocalDTO(Local entity) {
        this.id = entity.getId();
        this.nomeDoLocal = entity.getNomeDoLocal();
        this.endereco = entity.getEndereco();
        this.cidade = entity.getCidade();
        this.estado = entity.getEstado();
    }
}
