package com.demo.senac.service;

import com.demo.senac.dto.CursoDTO;
import com.demo.senac.entities.Curso;
import com.demo.senac.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    // bucas por todos ordenados por nome
    public List<CursoDTO> findAll() {
        List<Curso> list = repository.findAll(Sort.by("nome"));
        return list.stream().map(x -> new CursoDTO(x)).toList();
    }

    //inserir novo curso
    @Transactional
    public CursoDTO insert(CursoDTO dto) {
        Curso entity = new Curso();
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return new CursoDTO(entity);
    }



    // Copia os campos do DTO para a entidade
    private void copyDtoToEntity(CursoDTO dto, Curso curso) {
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        curso.setDuracao(dto.getDuracao());
        curso.setModalidade(dto.getModalidade());
        curso.setValorMensalidade(dto.getValorMensalidade());
    }
}
