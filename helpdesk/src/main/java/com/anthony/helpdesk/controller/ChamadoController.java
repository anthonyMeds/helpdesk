package com.anthony.helpdesk.controller;

import com.anthony.helpdesk.domain.Chamado;
import com.anthony.helpdesk.domain.dtos.ChamadoDTO;
import com.anthony.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {

        Chamado obj = service.findById(id);

        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

}
