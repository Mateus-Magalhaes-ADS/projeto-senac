package com.demo.senac.dto;

import com.demo.senac.entities.Matricula;

import java.time.LocalDate;

public class MatriculaResumoDTO {

    private Long id;
    private LocalDate dataMatricula;
    private String status;

    public MatriculaResumoDTO() {
    }

    public MatriculaResumoDTO(Long id, LocalDate dataMatricula, String status) {
        this.id = id;
        this.dataMatricula = dataMatricula;
        this.status = status;
    }

    public MatriculaResumoDTO(Matricula entity) {
        this.id = entity.getId();
        this.dataMatricula = entity.getDataMatricula();
        this.status = entity.getStatus();
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
}