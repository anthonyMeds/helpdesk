package com.anthony.helpdesk.services;

import com.anthony.helpdesk.domain.Pessoa;
import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.domain.dtos.TecnicoDTO;
import com.anthony.helpdesk.repository.PessoaRepository;
import com.anthony.helpdesk.repository.TecnicoRepository;
import com.anthony.helpdesk.services.exceptions.DataIntegrityException;
import com.anthony.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);

        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado para o id - " + id));

    }


    public List<Tecnico> findAll() {

        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {

        validaPorCpfEEmail(objDTO);

        Tecnico newObj = new Tecnico(objDTO);

        return tecnicoRepository.save(newObj);

    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())){
            throw new DataIntegrityException("cpf já cadastrado");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())) {
            throw new DataIntegrityException("email já cadastrado");
        }

    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        objDTO.setId(id);

        Tecnico tecnicoExistente =  findById(id);

        validaPorCpfEEmail(objDTO);

        tecnicoExistente = new Tecnico(objDTO);

        return tecnicoRepository.save(tecnicoExistente);

    }

    public void delete(Integer id) {

        Tecnico tecnicoExistente =  findById(id);

        if (tecnicoExistente.getChamados().size() > 0) {
            throw new DataIntegrityException("Existem chamados associados a esse técnico");
        }

        tecnicoRepository.deleteById(id);
    }
}
