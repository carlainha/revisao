package com.caroline.revisao.infrastructure.repository;

import com.caroline.revisao.domain.model.Curso;
import com.caroline.revisao.domain.repository.CursoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class CursoRepositoryImpl implements CursoRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Curso> listar() {
        return manager.createQuery("from Curso",Curso.class).getResultList();
    }

    @Override
    public Curso buscar(Long id) {
        return manager.find(Curso.class, id);
    }

    @Transactional
    @Override
    public Curso salvar(Curso curso) {
        return manager.merge(curso);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Curso curso = buscar(id);
        manager.remove(curso);
    }
}
