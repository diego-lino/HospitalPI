package com.puc.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PACIENTE")
public class Paciente {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer numeroPaciente;
	private Integer pressaoSistolica;
	private Integer pressaoDiastolica;
	
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();

		try {
		    // convert user object to json string and return it 
			mapper.registerModule(new JavaTimeModule());
		    return mapper.writeValueAsString(this);
		}
		catch (JsonProcessingException e) {
		    // catch various errors
		    e.printStackTrace();
		}
		return null;

	}

	public void novoPaciente(Paciente pacienteAnterior) {
		if(pacienteAnterior == null)
			this.numeroPaciente = 1;
		else
			this.numeroPaciente = pacienteAnterior.getNumeroPaciente() + 1;
		
		this.nome = "Paciente " + this.numeroPaciente;
		ControladorPressao controladorPressao = new ControladorPressao();
		this.pressaoSistolica = controladorPressao.getSistolica();
		this.pressaoDiastolica = controladorPressao.getDiastolica();
	}
	
	public void atualizaPressao() {
		ControladorPressao controladorPressao = new ControladorPressao();
		this.pressaoSistolica = controladorPressao.getSistolica();
		this.pressaoDiastolica = controladorPressao.getDiastolica();
	}
}
