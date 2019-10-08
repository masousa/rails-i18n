package br.com.teste.evoluum.external.domain;

public class ExternalMicrorregiao {
	private Integer id;
	private String nome;
	private ExternalMesorregiao mesorregiao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ExternalMesorregiao getMesorregiao() {
		return mesorregiao;
	}
	public void setMesoregiao(ExternalMesorregiao mesorRegiao) {
		this.mesorregiao = mesorRegiao;
	}
	
	
}
