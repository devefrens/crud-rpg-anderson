package com.trabalho.escolaTi.controller;

import com.trabalho.escolaTi.entities.ItemMagico;
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
    @GetMapping("/list-personagem/{id}")
    public ResponseEntity<Optional<Personagem>> getPersonagemById(@PathVariable Long id) {
        return personagemService.getPersonagemById(id);
    }

    @Operation(summary = "Deletar um personagem por ID")
    @DeleteMapping("/delete-personagem/{id}")
    public ResponseEntity<?> deleteProdutoById(@PathVariable Long id) {
        return personagemService.deleteProdutoById(id);
    }

    @Operation(summary = "Exibe a força total e defesa total do personagem (base + itens)")
    @GetMapping("/{id}/atributos-totais")
    public ResponseEntity<?> getAtributosTotais(@PathVariable Long id) {
        return personagemService.getAtributosTotais(id);
    }

    @Operation(summary = "Adiciona um item mágico a um personagem (com validações)")
    @PostMapping("/{id}/adicionar-item")
    public ResponseEntity<?> adicionarItemAoPersonagem(@PathVariable Long id, @RequestBody ItemMagico item) {
        return personagemService.adicionarItemAoPersonagem(id, item);
    }

    @Operation(summary = "Remove um item mágico do personagem")
    @DeleteMapping("/{id}/remover-item/{itemId}")
    public ResponseEntity<?> removerItemDoPersonagem(@PathVariable Long id, @PathVariable Long itemId) {
        return personagemService.removerItemDoPersonagem(id, itemId);
    }

    @Operation(summary = "Ver o amuleto do personagem (se tiver)")
    @GetMapping("/{id}/amuleto")
    public ResponseEntity<?> getAmuleto(@PathVariable Long id) {
        return personagemService.getAmuletoDoPersonagem(id);
    }

    // Atualizar o nome de guerreiro por ID
    @Operation(summary = "Atualiza o nome aventureiro do personagem")
    @PutMapping("/atualizar-nome/{id}")
    public ResponseEntity<?> atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String novoNome) {
        return personagemService.atualizarNomeAventureiro(id, novoNome);
    }


}
