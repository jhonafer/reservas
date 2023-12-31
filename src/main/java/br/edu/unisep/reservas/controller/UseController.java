package br.edu.unisep.reservas.controller;

import br.edu.unisep.reservas.exception.ResourceNotFoundException;
import br.edu.unisep.reservas.model.User;
import br.edu.unisep.reservas.repository.UserRepository;
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
public class UseController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/usuarios")
    public List<User> getallUsers(){
        return repository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable(value = "id") Long userID)
        throws ResourceNotFoundException {
        User user = repository.findById(userID).orElseThrow(()->
                new ResourceNotFoundException("Usuario não encontrado: " + userID));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/usuarios/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String userEmail){
        User user = repository.findByEmail(userEmail);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/usuarios")
    public User createUser(@Validated @RequestBody User user){
        return repository.save(user);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value= "id") Long userId,
                                           @Validated @RequestBody User detalhes)
        throws ResourceNotFoundException{
        User user = repository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario não encontrado: " + userId));
        user.setEmail(detalhes.getEmail());
        user.setSobrenome(detalhes.getSobrenome());
        user.setNome(detalhes.getNome());
        user.setSenha(detalhes.getSenha());
        user.setAlterado_em(new Date());
        final User updateUser = repository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/usuarios/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Long userId
    ) throws Exception{
        User user = repository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario não encontrado: " + userId));
        repository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
