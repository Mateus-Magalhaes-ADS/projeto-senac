package com.demo.senac.repositories;

import com.demo.senac.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository  extends JpaRepository<Matricula, Long> {

    List<Matricula> findByAlunoId(Long alunoId);
    List<Matricula> findByCursoId(Long cursoId);

}
