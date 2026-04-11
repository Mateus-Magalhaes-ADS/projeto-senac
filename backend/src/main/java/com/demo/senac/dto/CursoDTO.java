package com.demo.senac.dto;

import com.demo.senac.entities.Curso;
import com.demo.senac.entities.Matricula;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CursoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String duracao;
    private String modalidade;
    private Double valorMensalidade;
    private List<MatriculaResumoDTO> matriculas;

    public CursoDTO() {}

    public CursoDTO(Long id, String nome, String descricao, String duracao, String modalidade, Double valorMensalidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.modalidade = modalidade;
        this.valorMensalidade = valorMensalidade;
    }

    public CursoDTO(Curso entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.duracao = entity.getDuracao();
        this.modalidade = entity.getModalidade();
        this.valorMensalidade = entity.getValorMensalidade();
        // CORRETO: itera List<Matricula> e converte cada uma
        this.matriculas = entity.getMatriculas()
                .stream()
                .map(MatriculaResumoDTO::new)
                .toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(Double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public List<MatriculaResumoDTO> getMatriculas() {
        return matriculas;
    }
}
