package com.produtosdb.ai.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "quantidade_disponivel")
    private Integer quantidadeDisponivel;
}