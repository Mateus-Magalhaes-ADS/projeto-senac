package com.demo.senac.dto;

import com.demo.senac.entities.Aluno;

import java.util.List;

public class AlunoDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    // senha NÃO entra no DTO de resposta por segurança
    private List<MatriculaResumoDTO> matriculas;

    public AlunoDTO() {}

    public AlunoDTO(Long id, String nome, String cpf, String email, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Construtor que converte a entidade para DTO
    public AlunoDTO(Aluno entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.telefone = entity.getTelefone();
        this.endereco = entity.getEndereco();
        // Converte cada Matricula para MatriculaResumoDTO
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<MatriculaResumoDTO> getMatriculas() {
        return matriculas;
    }
}
