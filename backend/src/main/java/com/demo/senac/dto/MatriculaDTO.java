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
    private Aluno aluno;
    private Curso curso;

    public MatriculaDTO(){}

    public MatriculaDTO(Long id, LocalDate dataMatricula, String status, Aluno aluno, Curso curso) {
        this.id = id;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.aluno = aluno;
        this.curso = curso;
    }

    public MatriculaDTO(Matricula e){
        id = e.getId();
        dataMatricula = e.getDataMatricula();
        status = e.getStatus();
        aluno = e.getAluno();
        curso = e.getCurso();
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
