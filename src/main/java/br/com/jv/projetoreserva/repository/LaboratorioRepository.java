package br.com.jv.projetoreserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jv.projetoreserva.model.Laboratorio;
//repo
@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio,Long>{

}
