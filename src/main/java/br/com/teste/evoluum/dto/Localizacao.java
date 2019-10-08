package br.com.teste.evoluum.dto;

import java.io.Serializable;

public class Localizacao implements Serializable{
	
	private static final long serialVersionUID = -3640140570798465572L;
	public int idEstado;
	public String siglaEstado;
	public String regiaoNome;
	public String nomeCidade;
	public String nomeMesoregiao;
	public String nomeFormatado;
	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getSiglaEstado() {
		return siglaEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	public String getRegiaoNome() {
		return regiaoNome;
	}
	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public String getNomeMesoregiao() {
		return nomeMesoregiao;
	}
	public void setNomeMesoregiao(String nomeMesoregiao) {
		this.nomeMesoregiao = nomeMesoregiao;
	}
	public String getNomeFormatado() {
		return nomeFormatado;
	}
	public void setNomeFormatado(String nomeFormatado) {
		this.nomeFormatado = nomeFormatado;
	}
	
	

}
