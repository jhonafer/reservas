package br.edu.unisep.reservas.repository;

import br.edu.unisep.reservas.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository  extends JpaRepository<Reservas, Long> {

}
