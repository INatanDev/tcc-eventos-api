package com.eventos_api.entities.locais;

import com.eventos_api.entities.organizador.Organizador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "local")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDoLocal;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private char estado;


    /*
    Quando tem muitos para muitos, criar terceira tabela para fazer relacionamento
    entre eles. primeiro definir nome da tabela, depois definir colunas. Depois informar o
    inverso que ira fazer relacionamento
     */
    @ManyToMany
    @JoinTable(name = "tb_organizador_locais", joinColumns = @JoinColumn(name = "local_id"),
            inverseJoinColumns = @JoinColumn(name = "organizador_id")
    )
    Set<Organizador> organizador = new HashSet<>();


}
