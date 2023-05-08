package br.com.jv.projetoreserva.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="LABORATORIOS")
public class Laboratorio {	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable=false)
	String descricao;
	
	Boolean ocupado;
	
	@Column(nullable=false)
	Integer numMaquinas;
	
	@Column(nullable=false)
	String localizacao;
	
	 public Laboratorio() {}
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Integer getNumMaquinas() {
		return numMaquinas;
	}

	public void setNumMaquinas(Integer numMaquinas) {
		this.numMaquinas = numMaquinas;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	  public Laboratorio(String descricao, int numMaquinas, String localizacao) {
	        this.descricao = descricao;
	        this.numMaquinas = numMaquinas;
	        this.localizacao = localizacao;
	    }
}