package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import page.BasePage;

public class TesteCampoTreinamento extends BasePage {
	
	@Test
	public void testeTextField(){
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		dsl.escrever("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveIntegarirComCombo(){
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?"));
	}
	
	@Test
	public void deveinteragirComBotoes(){
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveinteragirComLinks(){
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.obterTexto(By.className("facilAchar")));
	}

	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarNomeTabelas("Nome", "Maria", "Botao",  "elementosForm:tableUsuarios");
	}
}


