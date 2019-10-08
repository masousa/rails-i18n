package br.com.teste.evoluum.external.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaEstados {
	
	private List<ExternalEstados> estados;
	
	public ListaEstados() {
		this.estados = new ArrayList<ExternalEstados>();
	}
	
	public List<ExternalEstados> getEstados() {
		return estados;
	}
	
	public void setEstados(List<ExternalEstados> estados) {
		this.estados = estados;
	}

}
