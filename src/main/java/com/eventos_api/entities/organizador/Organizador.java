package com.eventos_api.entities.organizador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Table(name = "organizadores")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeOrganizador")
    private String nome;

    private String contato;

}
