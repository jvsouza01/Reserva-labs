package br.com.jv.projetoreserva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.jv.projetoreserva.model.Professor;
import br.com.jv.projetoreserva.service.ProfessorService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	ProfessorService professorService;
	
	@Autowired
	HttpSession session;
	
	@PostMapping("/login")
	public String login(Professor professor) {
		Professor cadastrado = professorService.getByLoginSenha(professor.getLogin(), professor.getSenha());
		if(cadastrado != null) {
			session.setAttribute("user", cadastrado);
		}else {
			session.setAttribute("authError", "Credenciais inválidas");
		}
		return "redirect:/home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	
}
