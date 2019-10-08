package br.com.teste.evoluum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.teste.evoluum.builder.LocalizacaoBuilder;
import br.com.teste.evoluum.dto.Localizacao;
import br.com.teste.evoluum.external.EstadosRestTemplate;
import br.com.teste.evoluum.external.domain.ExternalEstados;
import br.com.teste.evoluum.external.domain.ExternalMunicipios;

@Service
public class LocalizacaoService {

	@Autowired
	private EstadosRestTemplate estadosRestTemplate;
	/***
	 * Este método tem por objetivo realizar uma consulta a uma fonte externa e construir uma
	 * lista de {@link Localizacao} contendo os estados consultados por esta fonte externa.
	 * @return
	 */
	public List<Localizacao> getEstados(){
		List<Localizacao> localizacoes = new ArrayList<Localizacao>();
		estadosRestTemplate.getEstados().forEach(estadoExterno ->{
			LocalizacaoBuilder lBuilder = new LocalizacaoBuilder(); 
			localizacoes.add(lBuilder.buildEstado(estadoExterno).getLocalizacao());
		});
		return localizacoes;
	}
	
	/***
	 * A partir de um código de um estado obtido este método tem como objetivo consultar uma fonte
	 * externa de municipios daquele estado e transformar o resultado obtido em uma lista de {@link Localizacao}
	 * contendo as informações necessárias
	 * @param uf
	 * @return
	 */
	public List<Localizacao> getMunicipios(int uf) {
		List<Localizacao> localizacoes = new ArrayList<Localizacao>();
		estadosRestTemplate.getCidades(uf).forEach(cidadeExterno ->{
			LocalizacaoBuilder lBuilder = new LocalizacaoBuilder(); 
			localizacoes.add(lBuilder.buildCidade(cidadeExterno).getLocalizacao());
		});
		return localizacoes;
	}
	
	/***
	 * Este método a partir de um nome de uma cidade acessa uma fonte externa de estados e cidades
	 * para obter o identificador desta cidade. Caso o identificador não seja encontrado o valor nulo 
	 * é retornado.
	 * @param name
	 * @return
	 */
	@Cacheable("cidades")
	public Integer getCidadeIdFromName(String name) {
		for (ExternalEstados estadoExterno : estadosRestTemplate.getEstados()) {
			int estadoId = estadoExterno.getId();
			for (ExternalMunicipios cidadeExterno : estadosRestTemplate.getCidades(estadoId)) {
				if(cidadeExterno.getNome().trim().equalsIgnoreCase(name.trim())) {
					return cidadeExterno.getId();
				}
			}
		}
		return null;
		
	}
}
