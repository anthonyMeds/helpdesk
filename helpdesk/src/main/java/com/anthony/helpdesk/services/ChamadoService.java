package com.anthony.helpdesk.services;

import com.anthony.helpdesk.domain.Chamado;
import com.anthony.helpdesk.domain.Cliente;
import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.domain.dtos.ChamadoDTO;
import com.anthony.helpdesk.domain.enuns.Prioridade;
import com.anthony.helpdesk.domain.enuns.Status;
import com.anthony.helpdesk.repository.ChamadoRepository;
import com.anthony.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {

        Optional<Chamado> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado - id " + id));

    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO obj) {
        return repository.save(newChamado(obj));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }

}
