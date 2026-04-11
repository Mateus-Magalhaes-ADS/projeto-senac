package com.demo.senac.repositories;

import com.demo.senac.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository  extends JpaRepository<Matricula, Long> {
}
