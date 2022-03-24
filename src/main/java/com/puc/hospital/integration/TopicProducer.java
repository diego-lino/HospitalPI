package com.puc.hospital.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.puc.hospital.entity.Paciente;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TopicProducer {

	@Value("${topic.name.producer}")
	private String topicName;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	public void send(Paciente paciente) {
		log.info("Payload enviado: {} - {}/{}", paciente.getNome(), paciente.getPressaoSistolica(), paciente.getPressaoDiastolica());
		kafkaTemplate.send(topicName, paciente.toJson());
	}
}
