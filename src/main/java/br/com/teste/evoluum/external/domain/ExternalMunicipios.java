package br.com.teste.evoluum.external.domain;

public class ExternalMunicipios {
	private Integer id;
	private String nome;
	private ExternalMicrorregiao microrregiao;
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
	public ExternalMicrorregiao getMicrorregiao() {
		return microrregiao;
	}
	public void setMicroregiao(ExternalMicrorregiao microrRegiao) {
		this.microrregiao = microrRegiao;
	}
	
}
