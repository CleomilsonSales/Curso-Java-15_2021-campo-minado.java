package br.com.cleomilsonsales.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cleomilsonsales.cm.excecao.ExplosaoException;

class CampoTeste {
	
	private Campo campo;
	
	//@BeforeEach - exigi que ao reiniciar o teste o metodo seja iniciado
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	//colunas e linha
	@Test
	void testeVizinhoDistEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistDiagonal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoEVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlterarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlterarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		//não terar exceção como o campo esta marcado
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		Campo vizinhoDoVizinho1 = new Campo(1, 1);
		Campo vizinho1 = new Campo(2, 2);
		vizinho1.adicionarVizinhos(vizinhoDoVizinho1);
		
		campo.adicionarVizinhos(vizinho1);
		//abrindo campo atual 3, 3
		campo.abrir();
		
		assertTrue(vizinhoDoVizinho1.isAberto() && vizinho1.isAberto());
		
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		Campo vizinhoDoVizinho1 = new Campo(1, 1);
		Campo vizinhoDoVizinho2 = new Campo(1, 1);
		vizinhoDoVizinho2.minar();
		
		Campo vizinho1 = new Campo(2, 2);
		vizinho1.adicionarVizinhos(vizinhoDoVizinho1);
		vizinho1.adicionarVizinhos(vizinhoDoVizinho2);
		
		campo.adicionarVizinhos(vizinho1);
		//abrindo campo atual 3, 3
		campo.abrir();
		
		assertTrue(vizinho1.isAberto() && vizinhoDoVizinho1.isFechado());
		
	}
	
	

}
