package com.anthony.helpdesk.services;

import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.repository.TecnicoRepository;
import com.anthony.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);

        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado para o id - " + id));

    }


    public List<Tecnico> findAll() {

        return tecnicoRepository.findAll();
    }
}
