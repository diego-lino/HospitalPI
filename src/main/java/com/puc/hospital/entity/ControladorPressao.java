package com.puc.hospital.entity;

import java.util.Random;

import lombok.Data;

@Data
public class ControladorPressao {

	private Integer sistolica;
	private Integer diastolica;
	
	public ControladorPressao() {
		geradorPressaoDiastolica();
		geradorPressaoSistolica();
	}
	
	private void geradorPressaoSistolica() {
		Random random = new Random();
		Integer sistolica = 0;
		Integer diferenca = 0;
		do {
			sistolica = random.nextInt(160 - 80 + 1) + 80;
			diferenca = sistolica - this.getDiastolica();
		} while (sistolica < this.getDiastolica() && diferenca < 10);
		
		this.setSistolica(sistolica);
	}
	
	private void geradorPressaoDiastolica() {
		Random random = new Random();
		this.setDiastolica(random.nextInt(100 - 50 + 1) + 50);
	}
}
