package br.com.cleomilsonsales.cm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {
	//Um exemplo simples de teste para entender melhor
	//tem que usar o @Test para ativar o teste
	@Test
	void testarSeIgualADois() {
		int a = 1 + 1;
		
		assertEquals(2, a);
	}
	
	@Test
	void testarSeIgualATres() {
		int a = 2 + 10 - 7;
		
		assertEquals(3, a);
	}

}
