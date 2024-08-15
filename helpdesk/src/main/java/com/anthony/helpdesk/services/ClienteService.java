package com.anthony.helpdesk.services;

import com.anthony.helpdesk.domain.Cliente;
import com.anthony.helpdesk.domain.Pessoa;
import com.anthony.helpdesk.domain.dtos.ClienteDTO;
import com.anthony.helpdesk.repository.ClienteRepository;
import com.anthony.helpdesk.repository.PessoaRepository;
import com.anthony.helpdesk.services.exceptions.DataIntegrityException;
import com.anthony.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado para o id - " + id));

    }


    public List<Cliente> findAll() {

        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);

        Cliente newObj = new Cliente(objDTO);

        return clienteRepository.save(newObj);

    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())){
            throw new DataIntegrityException("cpf já cadastrado");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())) {
            throw new DataIntegrityException("email já cadastrado");
        }

    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);

        Cliente clienteExistente =  findById(id);

        validaPorCpfEEmail(objDTO);

        clienteExistente = new Cliente(objDTO);

        return clienteRepository.save(clienteExistente);

    }

    public void delete(Integer id) {

        Cliente clienteExistente =  findById(id);

        if (clienteExistente.getChamados().size() > 0) {
            throw new DataIntegrityException("Existem chamados associados a esse técnico");
        }

        clienteRepository.deleteById(id);
    }
}
