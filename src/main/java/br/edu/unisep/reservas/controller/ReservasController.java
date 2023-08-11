package br.edu.unisep.reservas.controller;

import br.edu.unisep.reservas.exception.ResourceNotFoundException;
import br.edu.unisep.reservas.model.Reservas;
import br.edu.unisep.reservas.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ReservasController {

    @Autowired
    private ReservasRepository repository;

    @GetMapping("/reservas")
    public List<Reservas> getAllReservas(){
        return repository.findAll();
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<Reservas> getReservasByID(@PathVariable(value = "id") Long reservasID)
            throws ResourceNotFoundException {
        Reservas reservas = repository.findById(reservasID).orElseThrow(()->
                new ResourceNotFoundException("Reserva não encontrada: " + reservasID));
        return ResponseEntity.ok().body(reservas);
    }

    @PostMapping("/reservas")
    public Reservas createReserva(@Validated @RequestBody Reservas reservas){
        return repository.save(reservas);
    }

    @PutMapping("/reservas/{id}")
    public ResponseEntity<Reservas> updateReservas(@PathVariable(value= "id") Long reservasId,
                                                   @Validated @RequestBody Reservas detalhes)
            throws ResourceNotFoundException{
        Reservas reservas = repository.findById(reservasId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reserva não encontrada: " + reservasId));
        reservas.setDataReserva(detalhes.getDataReserva());
        reservas.setDataDevolucao(detalhes.getDataDevolucao());
        reservas.setEquipamento(detalhes.getEquipamento());
        reservas.setStatus(detalhes.getStatus());
        reservas.setUser(detalhes.getUser());
        reservas.setAlteradoEm(new Date());

        final Reservas updatedReservas = repository.save(reservas);
        return ResponseEntity.ok(updatedReservas);
    }

    @DeleteMapping("/reservas/{id}")1
    public Map<String, Boolean> deleteReservas(
            @PathVariable(value = "id") Long reservasId
    ) throws ResourceNotFoundException{
        Reservas reservas = repository.findById(reservasId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reserva não encontrada: " + reservasId));
        repository.delete(reservas);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
