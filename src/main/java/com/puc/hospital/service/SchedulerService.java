package com.puc.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableScheduling
@Log4j2
public class SchedulerService {

	@Autowired
	private PacienteService pacienteService;
	
	@Scheduled(fixedRate = 120000)
	public void criaTaskAtualizacaoPaciente() {
		log.info("Iniciando atualização de pacientes");
		pacienteService.getAllPacientes().stream().forEach(paciente -> {
			paciente.atualizaPressao();
			pacienteService.salvarPaciente(paciente);
		});
		log.info("Finalizando envio de pacientes");
	}
	
}
