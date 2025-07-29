package com.caroline.revisao.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_curso")
public class Curso {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Curso é obrigatório")
    @Column(name = "nome_curso", length = 100)
    private String nomeCurso;

    @Column(name = "data_cadastro", columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}
