package br.com.teste.evoluum.external.domain;

public class ExternalMesorregiao {
	private Integer id;
	private String nome;
	private ExternalEstados UF;
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
	public ExternalEstados getUF() {
		return UF;
	}
	public void setUF(ExternalEstados uF) {
		UF = uF;
	}
	
	

}
