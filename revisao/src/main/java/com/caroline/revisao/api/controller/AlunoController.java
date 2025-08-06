package com.caroline.revisao.api.controller;

import com.caroline.revisao.domain.exception.EntidadeEmUsoException;
import com.caroline.revisao.domain.exception.EntidadeNaoEncontradaException;
import com.caroline.revisao.domain.model.Aluno;
import com.caroline.revisao.domain.repository.AlunoRepository;
import com.caroline.revisao.domain.service.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listar(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long alunoId){
        Optional<Aluno> aluno = alunoRepository.findById(alunoId);

        if (aluno.isPresent()){
            return ResponseEntity.ok(aluno.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Aluno> adicionar(@RequestBody Aluno aluno){
        aluno = alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long alunoId, @RequestBody Aluno aluno){
        Optional<Aluno> alunoAtual = alunoRepository.findById(alunoId);

        if (alunoAtual.isPresent()){
            BeanUtils.copyProperties(aluno, alunoAtual, "id");

            Aluno alunoSalva = alunoService.salvar(alunoAtual.get());
            return ResponseEntity.ok(alunoSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{alunoId}")
    public ResponseEntity<Aluno> remover(@PathVariable Long cozinhaId) {
        try{
            alunoService.excluir(cozinhaId);
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
