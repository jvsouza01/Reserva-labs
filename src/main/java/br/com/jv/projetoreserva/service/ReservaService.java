package br.com.jv.projetoreserva.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jv.projetoreserva.model.Reserva;
import br.com.jv.projetoreserva.repository.ReservaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private EntityManager em;
	
	public Reserva criarReserva(Reserva reserva) {
		if(validarReserva(reserva)) {
			reserva.setAprovada(true);
		} else {
			reserva.setAprovada(false);
		}
		return reservaRepository.save(reserva);
	}
	
	public Boolean validarReserva(Reserva model) {
		for (Reserva reserva : getAprovadas()) {
			if(reserva.getLaboratorio().equals(model.getLaboratorio()) && reserva.getDataFim().equals(model.getDataFim())) {
				return false;
			} else if (reserva.getLaboratorio().equals(model.getLaboratorio()) && reserva.getDataInicio().equals(model.getDataInicio())) {
				return false;
			} else if(reserva.getLaboratorio().equals(model.getLaboratorio()) && reserva.getDataInicio().after(model.getDataInicio()) && reserva.getDataInicio().before(model.getDataFim())) {
				return false;
			} else if(reserva.getLaboratorio().equals(model.getLaboratorio()) && reserva.getDataFim().after(model.getDataInicio()) && reserva.getDataInicio().before(model.getDataFim())) {
				return false;
			}
		}
		return true;
	}
	
	public Date calcularFinal(Date dataReservada, Short aulas) {
		Date dataFim = new Date(dataReservada.getTime());
		if(aulas == 1) {
			dataFim.setHours(dataReservada.getHours()+1);
			dataFim.setMinutes(dataReservada.getMinutes()+15);
		} else if (aulas == 2) {
			dataFim.setHours(dataReservada.getHours()+2);
			dataFim.setMinutes(dataReservada.getMinutes()+40);
		}
		return dataFim;
	}
	

	public void deletar(Reserva reserva) {
		reservaRepository.delete(reserva);
	}
	
	public Reserva getById(Long id) {
		return reservaRepository.findById(id).orElseGet(null);
	}
	
	public List<Reserva> getAll() {
		return reservaRepository.findAll();
	}
	
	public List<Reserva> getAprovadas() {
		Query sql = em.createQuery("FROM Reserva r WHERE r.aprovada= 1");
		
		Stream<Reserva> result = sql.getResultStream();
		try {
			return result.toList();
		} catch (Exception e) {
			return null;
		}
	}
}