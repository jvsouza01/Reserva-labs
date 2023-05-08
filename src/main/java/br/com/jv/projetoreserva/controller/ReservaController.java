package br.com.jv.projetoreserva.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.jv.projetoreserva.model.Professor;
import br.com.jv.projetoreserva.model.Reserva;
import br.com.jv.projetoreserva.service.LaboratorioService;
import br.com.jv.projetoreserva.service.ReservaService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservaController")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private LaboratorioService laboratorioService;
	
	@PostMapping("/criar-reserva")
	public Object createReserva(@RequestParam("dataInicio") String data,
								@RequestParam("horario") String horario, 
								@RequestParam("disciplina") String disciplina,  
								@RequestParam("laboratorio") String laboratorio,
								@RequestParam("aulas") Short aulas,
															 HttpSession session) throws ParseException {
		
		if(horario.equals("11:15") && aulas == 2) {
			ModelAndView mv = new ModelAndView("Reserva");
			session.setAttribute("error", "Horário não permite ter 2 aulas seguidas.");
			mv.addObject("error", session.getAttribute("error"));
			return mv;
		}
		
		Date dataReserva = new Date(Integer.valueOf(data.substring(0,4))-1900, Integer.valueOf(data.substring(5,7)) - 1, Integer.valueOf(data.substring(8,10)),
							   Integer.valueOf(horario.substring(0,2)), Integer.valueOf(horario.substring(3,5)));
		
		
		reservaService.criarReserva(new Reserva((Professor) session.getAttribute("user"), disciplina, laboratorioService.getByDescricao(laboratorio), dataReserva, reservaService.calcularFinal(dataReserva, aulas),aulas));
		
		return "redirect:/home";
	}
	
	@GetMapping("/todasReservas")
	public List<Reserva> getAll() {
		return reservaService.getAll();
	}
	
}
