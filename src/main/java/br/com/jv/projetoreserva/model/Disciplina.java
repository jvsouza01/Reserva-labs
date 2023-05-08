package br.com.jv.projetoreserva.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	
	private static List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public static final Disciplina LOGICA_DE_PROGRAMACAO = new Disciplina("Lógica De Programação", "LPA");
	public static final Disciplina PROGRAMACAO_ORIENTADA_A_OBJETOS = new Disciplina("Programação Orientada a objetos", "POO");
	public static final Disciplina ARQUITETURA_DE_SOFTWARE = new Disciplina("Arquitetura De Software", "ARQS");
	public static final Disciplina RACIOCINIO_LOGICO = new Disciplina("Raciocícnio Lógico", "RL");
	public static final Disciplina CALCULO_1 = new Disciplina("Cálculo I", "CALCI");
	
	private String nome;
	private String sigla;
	
	public Disciplina() {}
	
	public Disciplina(String nome, String sigla) {
		this();
		this.nome = nome;
		this.sigla = sigla;
		disciplinas.add(this);
	}
	

	public static List<Disciplina> getAll() {
		return disciplinas;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

}
