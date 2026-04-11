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
    private List<Matricula> matriculas;

    public CursoDTO(){}

    public CursoDTO(Long id, String nome, String descricao, String duracao, String modalidade, Double valorMensalidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.modalidade = modalidade;
        this.valorMensalidade = valorMensalidade;
    }

    public CursoDTO(Curso entity) {
        id = entity.getId();
        nome = entity.getNome();
        descricao = entity.getDescricao();
        duracao = entity.getDuracao();
        modalidade = entity.getModalidade();
        valorMensalidade = entity.getValorMensalidade();
        for (Curso curso : entity.getMatriculas()) {
            MatriculaDTO matriculaDTO= new MatriculaDTO(curso);
           matriculaDTO.add(MatriculaDTO);
        }


    }
    }
