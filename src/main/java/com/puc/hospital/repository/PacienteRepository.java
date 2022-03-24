package com.puc.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.hospital.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Paciente findTopByOrderByIdDesc();

}
