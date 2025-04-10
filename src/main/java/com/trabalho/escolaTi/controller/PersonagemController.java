package com.trabalho.escolaTi.controller;

import com.trabalho.escolaTi.entities.Personagem;
import com.trabalho.escolaTi.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @Operation(summary = "Criar um novo personagem")
    @PostMapping("/create-personagem")
    public ResponseEntity<Personagem> createdPersonagem(@RequestBody Personagem personagem) {
        return personagemService.createdPersonagem(personagem);
    }

    @Operation(summary = "Lista um novo personagem")
    @GetMapping("/list-all-personagens")
    public ResponseEntity<List<Personagem>> getAllPersonagens() {
        return personagemService.getAllPersonagens();
    }

    @Operation(summary = "Listar um novo personagem por ID")
    @GetMapping("/list-produto/{id}")
    public ResponseEntity<Optional<Personagem>> getPersonagemById(@PathVariable Long id) {
        return personagemService.getPersonagemById(id);
    }

    @Operation(summary = "Deletar um personagem por ID")
    @DeleteMapping("/delete-produto/{id}")
    public ResponseEntity<?> deleteProdutoById(@PathVariable Long id) {
        return personagemService.deleteProdutoById(id);
    }


}
