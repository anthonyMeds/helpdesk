package com.anthony.helpdesk.controller;

import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.domain.dtos.TecnicoDTO;
import com.anthony.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById
            (
                    @PathVariable Integer id
            ) {

        Tecnico tecnico = service.findById(id);

        TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnico);

        return ResponseEntity.ok(tecnicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {

        List<Tecnico> tecnicos = service.findAll();

        List<TecnicoDTO> tecnicoDTOList = tecnicos.stream()
                .map( tecnico -> new TecnicoDTO(tecnico) )
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(tecnicoDTOList);
    }

}
