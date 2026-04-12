package com.demo.senac.controller;

import com.demo.senac.dto.MatriculaDTO;
import com.demo.senac.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    // GET /matriculas → retorna todas as matrículas
    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll() {
        List<MatriculaDTO> matriculas = matriculaService.findAll();
        return ResponseEntity.ok(matriculas);
    }

    // GET /matriculas/{id} → retorna matrícula por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable Long id) {
        MatriculaDTO dto = matriculaService.findById(id);
        return ResponseEntity.ok(dto);
    }

    // GET /matriculas/aluno/{alunoId} → todas as matrículas de um aluno
    @GetMapping(value = "/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaDTO>> findByAluno(@PathVariable Long alunoId) {
        List<MatriculaDTO> matriculas = matriculaService.findByAluno(alunoId);
        return ResponseEntity.ok(matriculas);
    }

    // GET /matriculas/curso/{cursoId} → todas as matrículas de um curso
    @GetMapping(value = "/curso/{cursoId}")
    public ResponseEntity<List<MatriculaDTO>> findByCurso(@PathVariable Long cursoId) {
        List<MatriculaDTO> matriculas = matriculaService.findByCurso(cursoId);
        return ResponseEntity.ok(matriculas);
    }

    // POST /matriculas → cria nova matrícula
    @PostMapping
    public ResponseEntity<MatriculaDTO> insert(@RequestBody MatriculaDTO dto) {
        dto = matriculaService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto); // 201 Created
    }

    // PUT /matriculas/{id} → atualiza matrícula existente
    @PutMapping(value = "/{id}")
    public ResponseEntity<MatriculaDTO> update(@PathVariable Long id, @RequestBody MatriculaDTO dto) {
        dto = matriculaService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    // DELETE /matriculas/{id} → deleta matrícula
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}