package br.com.teste.evoluum.controler;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.evoluum.dto.Localizacao;
import br.com.teste.evoluum.service.LocalizacaoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalizacaoRestControllerTest {
	
	@Autowired
	private LocalizacaoService localizacaoService;
	
	@Test
	public void getAllEstadosFromEndPointVerificandoAExistenciaDoConteudo() throws Exception{
		List<Localizacao> localizacoes = localizacaoService.getEstados();
		Assert.assertTrue(!localizacoes.isEmpty());
	}
	
	@Test
	public void getCidadesFromEstadoQueNaoRetornaValorAlgum() throws Exception{
		int ufQueNaoExiste = 1000;
		List<Localizacao> localizacoes = localizacaoService.getMunicipios(ufQueNaoExiste);
		Assert.assertTrue(localizacoes.isEmpty());
	}
	
	@Test
	public void getCidadesFromEstadoQueRetornaTodasAsCidadesDeUf11() throws Exception{
		int ufQueExiste = 11;
		List<Localizacao> localizacoes = localizacaoService.getMunicipios(ufQueExiste);
		Assert.assertTrue(!localizacoes.isEmpty());
	}
	
	@Test
	public void getCidadeFromNomeQueNãoExisteResultadoExperadoENulo() throws Exception{
		String nomeCidadeQueNaoExiste = "Cidade do teste";
		Integer localizacaoId = localizacaoService.getCidadeIdFromName(nomeCidadeQueNaoExiste);
		Assert.assertTrue(null==localizacaoId);
	}
	
	@Test
	public void getCidadeFromNomeColoradoDoOesteQueTeraComoResultado1100064() throws Exception{
		String nomeCidadeQueExiste = "Colorado do Oeste";
		Integer localizacaoId = localizacaoService.getCidadeIdFromName(nomeCidadeQueExiste);
		Assert.assertTrue(null!=localizacaoId);
		Assert.assertTrue(localizacaoId==1100064);
	}
	
	@Test
	public void getTempoRespostaAoConsultarPorNomeDeCidadeMaisDeUmaVezResultadoEsperadoEQueASegundaConsultaSejaMaisRapida() throws Exception{
		String nomeCidadeQueExiste = "Ariquemes";
		Long tempoPrimeiraConsulta = System.currentTimeMillis();
		Integer localizacaoId = localizacaoService.getCidadeIdFromName(nomeCidadeQueExiste);
		tempoPrimeiraConsulta= System.currentTimeMillis()-tempoPrimeiraConsulta;
		
		Long tempoSegundaConsulta = System.currentTimeMillis();
		Integer localizacaoId2 = localizacaoService.getCidadeIdFromName(nomeCidadeQueExiste);
		tempoSegundaConsulta= System.currentTimeMillis()-tempoSegundaConsulta;
		
		Assert.assertTrue(null!=localizacaoId);
		Assert.assertTrue(null!=localizacaoId2);
		Assert.assertTrue(tempoPrimeiraConsulta>tempoSegundaConsulta);
		
	}

}
