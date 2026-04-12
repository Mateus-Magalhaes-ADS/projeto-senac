package com.demo.senac.service;

import com.demo.senac.dto.MatriculaDTO;
import com.demo.senac.entities.Aluno;
import com.demo.senac.entities.Curso;
import com.demo.senac.entities.Matricula;
import com.demo.senac.repositories.AlunoRepository;
import com.demo.senac.repositories.CursoRepository;
import com.demo.senac.repositories.MatriculaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;


    // Busca todas as matrículas
    @Transactional(readOnly = true)
    public List<MatriculaDTO> findAll() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        return matriculas.stream()
                .map(MatriculaDTO::new)
                .toList();
    }

    // Busca matrícula por ID
    @Transactional(readOnly = true)
    public MatriculaDTO findById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada. Id: " + id));
        return new MatriculaDTO(matricula);
    }

    // Busca matrículas de um aluno específico
    @Transactional(readOnly = true)
    public List<MatriculaDTO> findByAluno(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new EntityNotFoundException("Aluno não encontrado. Id: " + alunoId);
        }
        return matriculaRepository.findByAlunoId(alunoId)
                .stream()
                .map(MatriculaDTO::new)
                .toList();
    }

    // Busca matrículas de um curso específico
    @Transactional(readOnly = true)
    public List<MatriculaDTO> findByCurso(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new EntityNotFoundException("Curso não encontrado. Id: " + cursoId);
        }
        return matriculaRepository.findByCursoId(cursoId)
                .stream()
                .map(MatriculaDTO::new)
                .toList();
    }

    // Cria nova matrícula
    @Transactional
    public MatriculaDTO insert(MatriculaDTO dto) {
        Matricula matricula = new Matricula();
        copyDtoToEntity(dto, matricula);
        matricula = matriculaRepository.save(matricula);
        return new MatriculaDTO(matricula);
    }

    // Atualiza status da matrícula
    @Transactional
    public MatriculaDTO update(Long id, MatriculaDTO dto) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada. Id: " + id));
        copyDtoToEntity(dto, matricula);
        matricula = matriculaRepository.save(matricula);
        return new MatriculaDTO(matricula);
    }

    // Deleta matrícula por ID
    @Transactional
    public void delete(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new EntityNotFoundException("Matrícula não encontrada. Id: " + id);
        }
        matriculaRepository.deleteById(id);
    }

    // Copia os campos do DTO para a entidade, buscando Aluno e Curso pelo ID
    private void copyDtoToEntity(MatriculaDTO dto, Matricula matricula) {
        matricula.setDataMatricula(dto.getDataMatricula());
        matricula.setStatus(dto.getStatus());

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));
        matricula.setAluno(aluno);

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado. Id: " + dto.getCursoId()));
        matricula.setCurso(curso);
    }


}
