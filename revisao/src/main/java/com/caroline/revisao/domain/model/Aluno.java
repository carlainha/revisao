package com.caroline.revisao.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O nome do aluno é obrigatório")
    @Column(name = "nome_aluno", length = 100)
    private String nome;

    @Column(name = "data_cadastro", columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    private Curso curso_id;

}
