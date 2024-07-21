package com.anthony.helpdesk;

import com.anthony.helpdesk.domain.Chamado;
import com.anthony.helpdesk.domain.Cliente;
import com.anthony.helpdesk.domain.Tecnico;
import com.anthony.helpdesk.domain.enuns.Perfil;
import com.anthony.helpdesk.domain.enuns.Prioridade;
import com.anthony.helpdesk.domain.enuns.Status;
import com.anthony.helpdesk.repository.ChamadoRepository;
import com.anthony.helpdesk.repository.ClienteRepository;
import com.anthony.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico t1 = new Tecnico(null, "joao", "84976789071", "joao@gmail.com", "123");
		t1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "linus", "90912065001", "linus@gmail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "primeiro chamado", t1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));

	}


}
