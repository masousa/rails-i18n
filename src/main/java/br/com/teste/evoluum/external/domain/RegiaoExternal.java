package br.com.teste.evoluum.external.domain;

import java.io.Serializable;

public class RegiaoExternal  implements Serializable{
	
	private static final long serialVersionUID = 2669631954965258782L;
	private Integer id;
	private String sigla;
	private String nome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
