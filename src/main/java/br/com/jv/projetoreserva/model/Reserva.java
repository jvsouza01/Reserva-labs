package br.com.jv.projetoreserva.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="RESERVAS")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	Professor professor;
	
	@Column(nullable=false)
	String disciplina;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	Laboratorio laboratorio;

	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(nullable=false)
	Date dataInicio;

	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(nullable=false)
	Date dataFim;
	
	@Column(nullable=false)
	Short aulas;
	
	Boolean aprovada;
	
	public Reserva() {
	}
	
	public Reserva(Professor professor, String disciplina, Laboratorio laboratorio, Date dataInicio, Date dataFim, Short aulas) {
		this();
		this.professor = professor;
		this.disciplina = disciplina;
		this.laboratorio = laboratorio;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.aulas = aulas;
	}

	public Long getId() {
		return id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public String getDia() {
		return new SimpleDateFormat("dd/MM/yyyy").format(dataFim);
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Date data) {
		this.dataFim = data;
	}
	
	public String getDataFimFormatada() {
		return new SimpleDateFormat("HH:mm").format(dataFim);
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Date data) {
		this.dataInicio = data;
	}
	
	public String getDataInicioFormatada() {
		return new SimpleDateFormat("HH:mm").format(dataInicio);
	}

	public Boolean getAprovada() {
		return aprovada;
	}

	public void setAprovada(Boolean aprovada) {
		this.aprovada = aprovada;
	}
	
	public Short getAulas() {
		return aulas;
	}

	public void setAulas(Short aulas) {
		this.aulas = aulas;
	}
}
