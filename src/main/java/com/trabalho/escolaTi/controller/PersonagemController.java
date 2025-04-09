package com.trabalho.escolaTi.controller;

import com.trabalho.escolaTi.entities.Personagem;
import com.trabalho.escolaTi.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @Operation(summary = "Criar um novo produto")
    @PostMapping("/create-personagem")
    public ResponseEntity<Personagem> createdPersonagem(@RequestBody Personagem personagem) {
        return personagemService.createdPersonagem(personagem);
    }

    @GetMapping("/list-all-personagens")
    public ResponseEntity<List<Personagem>> getAllPersonagens() {
        return personagemService.getAllPersonagens();
    }
//
//    @GetMapping("/list-produto/{id}")
//    public ResponseEntity<Optional<Produto>> getProdutoById(@PathVariable Long id) {
//        return produtoService.getProdutoById(id);
//    }
//
//    @DeleteMapping("/delete-produto/{id}")
//    public ResponseEntity<?> deleteProdutoById(@PathVariable Long id) {
//        return produtoService.deleteProdutoById(id);
//    }





}
