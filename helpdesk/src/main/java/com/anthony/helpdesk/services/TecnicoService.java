package com.anthony.helpdesk.services;

import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);

        return obj.orElse(null);

    }


}
