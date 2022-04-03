package com.puc.hospital.integration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.puc.hospital.entity.Paciente;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TopicProducer {

	@Autowired
    private RabbitTemplate template;

    public void publishMessage(@RequestBody Paciente paciente) {
        template.convertAndSend(RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY, paciente);
        log.info("Payload enviado: {} - {}/{}", paciente.getNome(), paciente.getPressaoSistolica(), paciente.getPressaoDiastolica());
    }
}
