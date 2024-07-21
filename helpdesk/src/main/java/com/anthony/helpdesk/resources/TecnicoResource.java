package com.anthony.helpdesk.resources;

import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.domain.dtos.TecnicoDTO;
import com.anthony.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

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

}
