		package br.com.jv.projetoreserva.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jv.projetoreserva.model.Disciplina;
import br.com.jv.projetoreserva.model.Professor;
import br.com.jv.projetoreserva.service.LaboratorioService;
import br.com.jv.projetoreserva.service.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class PathController {

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private LaboratorioService laboratorioService;
	
	@Autowired
	private ProfessorService professorService;

	@GetMapping("/")
	public String home() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public ModelAndView inicio(HttpSession session) {
		ModelAndView mv = new ModelAndView("Index");
		mv.addObject("reservas", reservaService.getAprovadas());
		mv.addObject("user", session.getAttribute("user"));
		mv.addObject("authError", session.getAttribute("authError"));
		return mv;
	}

	@GetMapping("/reserva")
	public Object reserva(HttpSession session) {
		Professor professor = (Professor) session.getAttribute("user");
		if (Objects.isNull(session.getAttribute("user"))) {
			ModelAndView mv = new ModelAndView("Index");
			session.setAttribute("authError", "Entre para reservar um laboratório");
			return "redirect:/home";
		} else {
			ModelAndView mv = new ModelAndView("Reserva");
			mv.addObject("user", session.getAttribute("user"));
			mv.addObject("disciplinas", professorService.getDisciplinasByProfessor(professor.getId()));
			mv.addObject("laboratorios", laboratorioService.getAll());
			return mv;
		}
	}

	@GetMapping("/laboratorios")
	public Object cadastrarLaboratorio(HttpSession session) {
		ModelAndView mv = new ModelAndView("Laboratorios");
		mv.addObject("user", session.getAttribute("user"));
		mv.addObject("error", session.getAttribute("error"));
		return mv;
	}

	@GetMapping("/cadastroProfessor")
	public ModelAndView cadastrarProfessor(HttpSession session) {
		ModelAndView mv = new ModelAndView("CadastroProfessor");
		mv.addObject("user", session.getAttribute("user"));
		mv.addObject("error", session.getAttribute("error"));
		mv.addObject("disciplinas", Disciplina.getAll());
		return mv;
	}
}