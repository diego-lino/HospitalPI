package com.puc.hospital.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.hospital.entity.Paciente;
import com.puc.hospital.integration.TopicProducer;
import com.puc.hospital.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private TopicProducer topicProducer;
	
//	@Autowired
//	private TopicProducer topicProducer;
	
	
	public List<Paciente> gerarListaPacientes(){
		List<Paciente> listaPacientes = new LinkedList<Paciente>();
		Paciente ultimoPaciente = null;
		do {
			Paciente pacienteAtual = new Paciente();
			pacienteAtual.novoPaciente(ultimoPaciente);
			
			listaPacientes.add(pacienteAtual);
			ultimoPaciente = pacienteAtual;
			salvarPaciente(pacienteAtual);
		} while (listaPacientes.size() < 200);
		
		return listaPacientes;
	}

	public void novoPaciente() {
		Paciente ultimoPaciente = pacienteRepository.findTopByOrderByIdDesc();
		Paciente novoPaciente = new Paciente();
		novoPaciente.novoPaciente(ultimoPaciente);
		salvarPaciente(novoPaciente);
		
	}
	
	public void salvarPaciente(Paciente paciente) {
		pacienteRepository.save(paciente);
		enviaPacienteParaRabbitMQ(paciente);
	}
	
	private void enviaPacienteParaRabbitMQ(Paciente paciente) {
		topicProducer.publishMessage(paciente);
	}

	public List<Paciente> getAllPacientes() {
		return pacienteRepository.findAll();
	}
}
