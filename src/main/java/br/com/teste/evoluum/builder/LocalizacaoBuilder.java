package br.com.teste.evoluum.builder;

import br.com.teste.evoluum.dto.Localizacao;
import br.com.teste.evoluum.external.domain.ExternalEstados;
import br.com.teste.evoluum.external.domain.ExternalMunicipios;

public class LocalizacaoBuilder {
	private Localizacao localizacao;
	
	public LocalizacaoBuilder buildEstado(ExternalEstados estado) {
		if(null==localizacao) {
			localizacao = new Localizacao();
		}
		localizacao.setIdEstado(estado.getId());
		localizacao.setRegiaoNome(estado.getRegiao().getNome());
		localizacao.setSiglaEstado(estado.getSigla());
		return this;
	}
	
	public LocalizacaoBuilder buildCidade(ExternalMunicipios municipio) {
		ExternalEstados uf = municipio.getMicrorregiao().getMesorregiao().getUF();
		this.buildEstado(uf);
		localizacao.setNomeCidade(municipio.getNome());
		localizacao.setNomeMesoregiao(municipio.getMicrorregiao().getMesorregiao().getNome());
		localizacao.setNomeFormatado(String.format("%s/%s", municipio.getNome(),uf.getSigla()));
		
		return this;
	}
	
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	


}
