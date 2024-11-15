package com.eventos_api.entities.inscricao;

import com.eventos_api.entities.eventos.Evento;
import com.eventos_api.entities.user.Usuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "inscricao")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarios;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @PrePersist
    public void prePersist(){
        dataCadastro = new Date();
    }
}
