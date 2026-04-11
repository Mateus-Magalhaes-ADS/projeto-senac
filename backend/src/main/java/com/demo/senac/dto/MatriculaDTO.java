package com.demo.senac.dto;

import com.demo.senac.entities.Aluno;
import com.demo.senac.entities.Curso;
import com.demo.senac.entities.Matricula;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class MatriculaDTO {

    private Long id;
    private LocalDate dataMatricula;
    private String status;
    // Guarda apenas o ID — evita loop infinito e não expõe dados desnecessários
    private Long alunoId;
    private String alunoNome;
    private Long cursoId;
    private String cursoNome;

    public MatriculaDTO() {}

    public MatriculaDTO(Long id, LocalDate dataMatricula, String status, Long alunoId, String alunoNome, Long cursoId, String cursoNome) {
        this.id = id;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.alunoId = alunoId;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
        this.cursoNome = cursoNome;
    }

    public MatriculaDTO(Matricula entity) {
        this.id = entity.getId();
        this.dataMatricula = entity.getDataMatricula();
        this.status = entity.getStatus();
        // Acessa só o necessário do relacionamento
        this.alunoId = entity.getAluno().getId();
        this.alunoNome = entity.getAluno().getNome();
        this.cursoId = entity.getCurso().getId();
        this.cursoNome = entity.getCurso().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }
}
