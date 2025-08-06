package com.caroline.revisao.domain.repository;

import com.caroline.revisao.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("From Aluno a join fetch a.curso")
    List<Aluno> findAll();
}
