package com.caroline.revisao.domain.repository;

import com.caroline.revisao.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository {
    List<Aluno> listar();
    Aluno buscar (Long id);
    Aluno salvar (Aluno aluno);
    void remover (Long id);
}
