package br.com.jv.projetoreserva.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jv.projetoreserva.model.Laboratorio;
import br.com.jv.projetoreserva.model.Professor;
import br.com.jv.projetoreserva.repository.ProfessorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository repo;

	@Autowired
	private EntityManager em;

	public Professor getByLoginSenha(String login, String senha) {
		Query sql = em.createQuery("FROM Professor p WHERE p.login='" + login + "' AND p.senha='" + senha + "'");
		Stream<Professor> result = sql.getResultStream();
		try {
			return result.findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Professor getByLogin(String login) {
		Query sql = em.createQuery("FROM Professor p WHERE p.login='" + login + "'");
		Stream<Professor> result = sql.getResultStream();
		try {
			return result.findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<String> getDisciplinasByProfessor(Long id) {
		Query sql = em.createQuery("FROM Professor p WHERE p.id = " + id);
		Stream<Professor> result = sql.getResultStream();
		
		try {
			return result.findFirst().get().getDisciplinas();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Professor getByEmail(String email) {
		Query sql = em.createQuery("FROM Professor p WHERE p.email='" + email + "'");
		Stream<Professor> result = sql.getResultStream();
		try {
			return result.findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public Object cadastrar(String nome, String email, String login, String senha, List<String> disciplinas,
			Boolean admin) {
		
		if(!Objects.isNull(getByLogin(login))) {
			return "já existe usuário com esse login";
		}
		if(!Objects.isNull(getByEmail(email))) {
			return "já existe usuário com esse email";
		}
		
		Professor professor = new Professor(nome, disciplinas, login, senha, email, admin);
		return repo.save(professor);
	}

}
