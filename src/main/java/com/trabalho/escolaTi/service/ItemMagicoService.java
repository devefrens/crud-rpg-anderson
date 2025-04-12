package com.trabalho.escolaTi.service;

import com.trabalho.escolaTi.entities.ItemMagico;
import com.trabalho.escolaTi.entities.ItemMagico.TipoItem;
import com.trabalho.escolaTi.entities.Personagem;
import com.trabalho.escolaTi.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemMagicoService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public ResponseEntity<?> adicionarItemAoPersonagem(Long personagemId, ItemMagico item) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(personagemId);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }

        Personagem personagem = personagemOpt.get();

        // Debugar essa bagaça hahahaa
        System.out.println("Força: " + item.getForca() + ", Defesa: " + item.getDefesa());

        // Regras de validação adicionais (além do @PrePersist)
        if (item.getForca() == 0 && item.getDefesa() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item deve ter pelo menos força ou defesa maior que 0.");
        }

        if (item.getForca() > 10 || item.getDefesa() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Força e Defesa do item não podem passar de 10.");
        }

        if (item.getTipo() == TipoItem.ARMA && item.getDefesa() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item do tipo Arma deve ter defesa igual a 0.");
        }

        if (item.getTipo() == TipoItem.ARMADURA && item.getForca() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item do tipo Armadura deve ter força igual a 0.");
        }

        if (item.getTipo() == TipoItem.AMULETO) {
            long amuletos = personagem.getItensMagicos().stream()
                    .filter(i -> i.getTipo() == TipoItem.AMULETO)
                    .count();
            if (amuletos >= 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Personagem só pode ter 1 amuleto.");
            }
        }

        item.setPersonagem(personagem);
        personagem.getItensMagicos().add(item);
        personagemRepository.save(personagem);

        return ResponseEntity.ok("Item adicionado com sucesso.");
    }

    public ResponseEntity<?> removerItemDoPersonagem(Long personagemId, Long itemId) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(personagemId);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }

        Personagem personagem = personagemOpt.get();
        boolean removed = personagem.getItensMagicos().removeIf(item -> item.getId().equals(itemId));

        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado com o personagem.");
        }

        personagemRepository.save(personagem);
        return ResponseEntity.ok("Item removido com sucesso.");
    }

    public ResponseEntity<?> getAmuletoDoPersonagem(Long personagemId) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(personagemId);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não foi encontrado.");
        }

        Personagem personagem = personagemOpt.get();

        return personagem.getItensMagicos().stream()
            .filter(i -> i.getTipo() == TipoItem.AMULETO)
            .findFirst()
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.ok("O personagem não possui um amuleto."));
    }
}
