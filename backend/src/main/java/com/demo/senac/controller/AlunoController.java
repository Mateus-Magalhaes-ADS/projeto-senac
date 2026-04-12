package com.demo.senac.controller;

import com.demo.senac.dto.AlunoDTO;
import com.demo.senac.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<AlunoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id){
        AlunoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> uptade(@PathVariable Long id ,@Valid @RequestBody AlunoDTO dto){
        AlunoDTO newDto = service.uptade(id , dto);
        return ResponseEntity.ok().body(newDto);
    }


    @PostMapping
    public ResponseEntity<AlunoDTO> insert(@Valid @RequestBody AlunoDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
