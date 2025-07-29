package com.caroline.revisao.domain.repository;

import com.caroline.revisao.domain.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository {
    List<Curso> listar();
    Curso buscar (Long id);
    Curso salvar (Curso curso);
    void remover(Long id);
}
