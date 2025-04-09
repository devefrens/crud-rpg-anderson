package com.trabalho.escolaTi.service;

import com.trabalho.escolaTi.entities.Personagem;
import com.trabalho.escolaTi.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    // POST
    public ResponseEntity<Personagem> createdPersonagem(Personagem personagem) {
        Personagem newPersonagem = personagemRepository.save(personagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(personagem);
    }

    // GETALL
    public ResponseEntity<List<Personagem>> getAllPersonagens() {
        List<Personagem> allPersonagens = personagemRepository.findAll();
        return  ResponseEntity.ok(allPersonagens);
    }

    // GET
    public ResponseEntity<Optional<Personagem>> getPersonagemById() {

    }

}
