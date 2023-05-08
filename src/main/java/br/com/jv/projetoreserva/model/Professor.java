package br.com.jv.projetoreserva.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROFESSORES")
//@NamedQueries(
//		@NamedQuery(name="getDisciplinasById", query = "SELECT DISTINCT disciplinas FROM Professor p INNER JOIN professor_disciplinas ON professor_disciplinas.professor_id = :id"))
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ElementCollection
	private List<String> disciplinas;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private Boolean admin;

	public Professor() {

	}

	public Professor(String nome, List<String> disciplinas, String login, String senha, String email,
			Boolean admin) {
		super();
		this.nome = nome;
		this.disciplinas = disciplinas;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPrimeiroNome() {
		String[] nomeDividido = nome.split(" ");
		return nomeDividido[0];
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<String> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}