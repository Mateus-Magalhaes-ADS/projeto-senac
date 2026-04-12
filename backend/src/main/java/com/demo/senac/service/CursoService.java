package com.demo.senac.service;

import com.demo.senac.dto.CursoDTO;
import com.demo.senac.entities.Curso;
import com.demo.senac.repositories.CursoRepository;
import com.demo.senac.service.exceptions.DatabaseException;
import com.demo.senac.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    // bucas por todos ordenados por nome
    public List<CursoDTO> findAll() {
        List<Curso> list = repository.findAll(Sort.by("nome"));
        return list.stream().map(x -> new CursoDTO(x)).toList();
    }

    // busca por id
    @Transactional(readOnly = true)
    public CursoDTO findById(Long id) {
        Optional<Curso> obj = repository.findById(id);
        Curso entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new CursoDTO(entity);
    }

    @Transactional
    public CursoDTO uptade(Long id , CursoDTO dto) {
        try {
            Curso entity = repository.getReferenceById(id);
            copyDtoToEntity(dto,entity);
            entity = repository.save(entity);
            return new CursoDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Recurso não encontrado" + id);
        }
    }

    //inserir novo curso
    @Transactional
    public CursoDTO insert(CursoDTO dto) {
        Curso entity = new Curso();
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return new CursoDTO(entity);
    }

    //deletar curso
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
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
