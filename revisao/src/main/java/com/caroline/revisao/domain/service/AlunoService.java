package com.caroline.revisao.domain.service;

import com.caroline.revisao.domain.exception.EntidadeEmUsoException;
import com.caroline.revisao.domain.exception.EntidadeNaoEncontradaException;
import com.caroline.revisao.domain.model.Aluno;
import com.caroline.revisao.domain.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno salvar (Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public void excluir(Long id) {
        try{
            alunoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Aluno ou código %d não pode ser removida, pois está em uso.", id));
        }
        catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de aluno com código %d", id));
        }
    }
}
