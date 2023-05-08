package br.com.jv.projetoreserva.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jv.projetoreserva.model.Laboratorio;
import br.com.jv.projetoreserva.repository.LaboratorioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class LaboratorioService {

	@Autowired
	private EntityManager em;

	@Autowired
	private LaboratorioRepository repo;

	public Laboratorio getByDescricao(String descricao) {

		Query sql = em.createQuery("FROM Laboratorio p WHERE p.descricao = '" + descricao + "'");

		Stream<Laboratorio> result = sql.getResultStream();
		try {
			return result.findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Laboratorio> getAll() {
		return repo.findAll();
	}

	public Object cadastrar(String descricao, int numMaquinas, String localizacao) {
		if(!Objects.isNull(getByDescricao(descricao))) {
			return "Já existe laboratório com essa descrição";
		}
		Laboratorio laboratorio = new Laboratorio(descricao, numMaquinas, localizacao);
		return repo.save(laboratorio);
	}

	public List<Laboratorio> listarTodos() {
		return repo.findAll();
	}

	public Laboratorio buscarPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Laboratorio nao encontrado"));
	}

	public Laboratorio atualizar(Long id, Laboratorio laboratorioAtualizado) {
		Laboratorio laboratorio = buscarPorId(id);
		laboratorio.setDescricao(laboratorioAtualizado.getDescricao());
		laboratorio.setLocalizacao(laboratorioAtualizado.getLocalizacao());
		laboratorio.setNumMaquinas(laboratorioAtualizado.getNumMaquinas());
		return repo.save(laboratorio);

	}

	public void excluir(Long id) {
		Laboratorio laboratorio = buscarPorId(id);
		repo.delete(laboratorio);
	}

}