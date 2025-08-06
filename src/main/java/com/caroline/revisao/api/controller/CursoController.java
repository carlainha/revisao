package com.caroline.revisao.api.controller;

import com.caroline.revisao.domain.exception.EntidadeEmUsoException;
import com.caroline.revisao.domain.exception.EntidadeNaoEncontradaException;
import com.caroline.revisao.domain.model.Curso;
import com.caroline.revisao.domain.repository.CursoRepository;
import com.caroline.revisao.domain.service.CursoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar(){
        return cursoRepository.findAll();
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> buscar(@PathVariable Long cursoId){
        Optional<Curso> curso = cursoRepository.findById(cursoId);

        if (curso.isPresent()){
            return ResponseEntity.ok(curso.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Curso> adicionar(@RequestBody Curso curso){
        curso = cursoService.salvar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @PutMapping("/{cursoId}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long cursoId, @RequestBody Curso curso){
        Optional<Curso> cursoAtual = cursoRepository.findById(cursoId);

        if (cursoAtual.isPresent()){
            BeanUtils.copyProperties(curso, cursoAtual, "id");

            Curso cursoSalva = cursoService.salvar(cursoAtual.get());
            return ResponseEntity.ok(cursoSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cursoId}")
    public ResponseEntity<Curso> remover(@PathVariable Long cursoId) {
        try{
            cursoService.excluir(cursoId);
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

