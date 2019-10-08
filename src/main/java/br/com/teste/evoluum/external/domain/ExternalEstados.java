package br.com.teste.evoluum.external.domain;

import java.io.Serializable;


public class ExternalEstados implements Serializable{
	
	private static final long serialVersionUID = -9215318016822424454L;
	private Integer id;
	private String sigla;
	private String nome;
	private RegiaoExternal regiao;
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
	public RegiaoExternal getRegiao() {
		return regiao;
	}
	public void setRegiao(RegiaoExternal regiao) {
		this.regiao = regiao;
	}
	
	

}
