package com.puc.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.hospital.entity.Paciente;
import com.puc.hospital.service.PacienteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/paciente")
@Slf4j
public class GestaoPacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping(value = "/inicializar")
	public ResponseEntity<List<Paciente>> gerarPacientes() {
		
		List<Paciente> listaPacientes = pacienteService.gerarListaPacientes();
		
		return ResponseEntity.ok(listaPacientes);
	}
	
	@PostMapping(value = "/novoPaciente")
	public void novoPaciente() {
		pacienteService.novoPaciente();
	}
}
