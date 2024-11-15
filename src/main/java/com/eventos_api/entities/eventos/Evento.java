package com.eventos_api.entities.eventos;

import com.eventos_api.entities.locais.Local;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "eventos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEvento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDoEvento;

    private String descricao;

    private Long capacidadeMaxima;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
}
