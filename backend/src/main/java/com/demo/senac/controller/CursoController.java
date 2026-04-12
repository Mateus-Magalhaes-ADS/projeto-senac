package com.demo.senac.controller;

import com.demo.senac.dto.CursoDTO;
import com.demo.senac.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() {
        List<CursoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id){
        CursoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> uptade(@PathVariable Long id ,@Valid @RequestBody CursoDTO dto){
        CursoDTO newDto = service.uptade(id , dto);
        return ResponseEntity.ok().body(newDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CursoDTO> insert(@Valid @RequestBody CursoDTO dto){
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
