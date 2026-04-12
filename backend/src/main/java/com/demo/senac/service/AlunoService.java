package com.demo.senac.service;

import com.demo.senac.dto.AlunoDTO;
import com.demo.senac.entities.Aluno;
import com.demo.senac.repositories.AlunoRepository;
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
public class AlunoService {

    @Autowired
    private AlunoRepository repository;


    // bucas por todos ordenados por nome
    public List<AlunoDTO> findAll() {
        List<Aluno> list = repository.findAll(Sort.by("nome"));
        return list.stream().map(x -> new AlunoDTO(x)).toList();
    }

    // busca por id
    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        Aluno entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AlunoDTO(entity);
    }

    @Transactional
    public AlunoDTO uptade(Long id , AlunoDTO dto) {
        try {
            Aluno entity = repository.getReferenceById(id);
            copyDtoToEntity(dto,entity);
            entity = repository.save(entity);
            return new AlunoDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Recurso não encontrado" + id);
        }
    }

    //inserir novo Aluno
    @Transactional
    public AlunoDTO insert(AlunoDTO dto) {
        Aluno entity = new Aluno();
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return new AlunoDTO(entity);
    }

    //deletar Aluno
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


    private void copyDtoToEntity(AlunoDTO dto, Aluno aluno) {
        aluno.setNome(dto.getNome());
        aluno.setCpf(dto.getCpf());
        aluno.setEmail(dto.getEmail());
        aluno.setTelefone(dto.getTelefone());
        aluno.setEndereco(dto.getEndereco());
    }

}
