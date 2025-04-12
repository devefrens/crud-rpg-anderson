//package com.trabalho.escolaTi.controller;
//
//import com.trabalho.escolaTi.service.PersonagemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping
//public class ItemMagicoController {
//
//    @Autowired
//    private PersonagemService personagemService;
//
//}

// ==========================================================================

//package com.trabalho.escolaTi.controller;
//
//import com.trabalho.escolaTi.entities.ItemMagico;
//import com.trabalho.escolaTi.repository.ItemMagicoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/itens-magicos")
//public class ItemMagicoController {
//
//    @Autowired
//    private ItemMagicoRepository itemMagicoRepository;
//
//    @PostMapping
//    public ResponseEntity<?> criarItem(@RequestBody ItemMagico item) {
//        try {
//            item.validarItem();
//            ItemMagico salvo = itemMagicoRepository.save(item);
//            return ResponseEntity.ok(salvo);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ItemMagico>> listarTodos() {
//        return ResponseEntity.ok(itemMagicoRepository.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
//        return itemMagicoRepository.findById(id)
//                .map(item -> ResponseEntity.ok(item))
//                .orElse(ResponseEntity.notFound().build());
//    }
//}

package com.trabalho.escolaTi.controller;

import com.trabalho.escolaTi.entities.ItemMagico;
import com.trabalho.escolaTi.entities.Personagem;
import com.trabalho.escolaTi.repository.ItemMagicoRepository;
import com.trabalho.escolaTi.repository.PersonagemRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @PostMapping
    public ResponseEntity<?> criarItem(@RequestBody ItemMagico item) {
        try {
            item.validarItem();
            ItemMagico salvo = itemMagicoRepository.save(item);
            return ResponseEntity.ok(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Lista todos os itens mágicos")
    @GetMapping
    public ResponseEntity<List<ItemMagico>> listarTodos() {
        return ResponseEntity.ok(itemMagicoRepository.findAll());
    }

    @Operation(summary = "Busca um item mágico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return itemMagicoRepository.findById(id)
                .map(item -> ResponseEntity.ok(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo item mágico a um personagem")
    @PostMapping("/adicionar/{personagemId}/{itemId}")
    public ResponseEntity<?> adicionarItemPersonagem(@PathVariable Long personagemId, @PathVariable Long itemId) {
        Personagem personagem = personagemRepository.findById(personagemId).orElse(null);
        ItemMagico item = itemMagicoRepository.findById(itemId).orElse(null);

        if (personagem == null || item == null) {
            return ResponseEntity.badRequest().body("Personagem ou Item não encontrado.");
        }

        if (item.getTipo() == ItemMagico.TipoItem.AMULETO) {
            boolean jaTemAmuleto = personagem.getItensMagicos().stream()
                    .anyMatch(i -> i.getTipo() == ItemMagico.TipoItem.AMULETO);
            if (jaTemAmuleto) {
                return ResponseEntity.badRequest().body("Personagem já possui um amuleto.");
            }
        }

        personagem.getItensMagicos().add(item);
        personagemRepository.save(personagem);

        return ResponseEntity.ok("Item adicionado ao personagem.");
    }

    // Lista itens por personagem
    @Operation(summary = "Lista os novos itens mágicos")
    @GetMapping("/personagem/{personagemId}")
    public ResponseEntity<?> listarItensPorPersonagem(@PathVariable Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId).orElse(null);
        if (personagem == null) {
            return ResponseEntity.badRequest().body("Personagem não encontrado.");
        }
        return ResponseEntity.ok(personagem.getItensMagicos());
    }

    // Remove item mágico do personagem
    @Operation(summary = "Remove um item mágico do personagem")
    @DeleteMapping("/remover/{personagemId}/{itemId}")
    public ResponseEntity<?> removerItemDoPersonagem(@PathVariable Long personagemId, @PathVariable Long itemId) {
        Personagem personagem = personagemRepository.findById(personagemId).orElse(null);
        if (personagem == null) {
            return ResponseEntity.badRequest().body("Personagem não encontrado.");
        }

        boolean removido = personagem.getItensMagicos().removeIf(i -> i.getId().equals(itemId));
        if (!removido) {
            return ResponseEntity.badRequest().body("Item não está vinculado ao personagem.");
        }

        personagemRepository.save(personagem);
        return ResponseEntity.ok("Item removido do personagem.");
    }


    @GetMapping("/{personagemId}/amuleto")
    public ResponseEntity<?> buscarAmuleto(@PathVariable Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId).orElse(null);
        if (personagem == null) {
            return ResponseEntity.badRequest().body("Personagem não encontrado.");
        }

        return personagem.getItensMagicos().stream()
                .filter(item -> item.getTipo() == ItemMagico.TipoItem.AMULETO)
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok("Personagem não possui amuleto."));
    }

    @Operation(summary = "Atualiza um item mágico existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarItem(@PathVariable Long id, @RequestBody ItemMagico itemAtualizado) {
        return itemMagicoRepository.findById(id).map(item -> {
            item.setNome(itemAtualizado.getNome());
            item.setTipo(itemAtualizado.getTipo());
            itemMagicoRepository.save(item);
            return ResponseEntity.ok(item);
        }).orElse(ResponseEntity.notFound().build());
    }

}
