package br.com.jv.projetoreserva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.jv.projetoreserva.model.Professor;
import br.com.jv.projetoreserva.service.ProfessorService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfessorController {

	@Autowired
	ProfessorService service;

	@PostMapping("/cadastrarProfessor")
	public String cadastrarProfessor(@RequestParam(name = "nome") String nome,
			@RequestParam(name = "email") String email, 
			@RequestParam(name = "login") String login,
			@RequestParam(name = "senha") String senha, 
			@RequestParam(name = "disciplina") List<String> disciplinas,
			@RequestParam(name = "admin", required = false) boolean admin,
			HttpSession session) {
		
		Object retorno = service.cadastrar(nome, email, login, senha, disciplinas, (admin == true) ? admin : false);
		
		if(!(retorno instanceof Professor)) {
			session.setAttribute("error", retorno);
			return "redirect:/cadastroProfessor";
		}
		
		session.removeAttribute("error");
		return "redirect:/cadastroProfessor";
	}

}
