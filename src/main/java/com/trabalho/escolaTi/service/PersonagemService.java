package com.trabalho.escolaTi.service;

import com.trabalho.escolaTi.entities.ItemMagico;
import com.trabalho.escolaTi.entities.ItemMagico.TipoItem;
import com.trabalho.escolaTi.entities.Personagem;
import com.trabalho.escolaTi.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    // POST
    public ResponseEntity<Personagem> createdPersonagem(Personagem personagem) {
        Personagem newPersonagem = personagemRepository.save(personagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersonagem);
    }

    // GETALL
    public ResponseEntity<List<Personagem>> getAllPersonagens() {
        List<Personagem> allPersonagens = personagemRepository.findAll();
        return ResponseEntity.ok(allPersonagens);
    }

    // GET-ID
    public ResponseEntity<Optional<Personagem>> getPersonagemById(Long id) {
        Optional<Personagem> getPersonagemById = personagemRepository.findById(id);
        return ResponseEntity.ok(getPersonagemById);
    }

    // DELETE
    public ResponseEntity<?> deleteProdutoById(Long id) {
        personagemRepository.deleteById(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }

    // Atributos totais
    public ResponseEntity<?> getAtributosTotais(Long id) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(id);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }

        Personagem personagem = personagemOpt.get();

        int forcaTotal = personagem.getForca();
        int defesaTotal = personagem.getDefesa();

        for (ItemMagico item : personagem.getItensMagicos()) {
            forcaTotal += item.getForca();
            defesaTotal += item.getDefesa();
        }

        Map<String, Integer> atributos = new HashMap<>();
        atributos.put("forcaTotal", forcaTotal);
        atributos.put("defesaTotal", defesaTotal);

        return ResponseEntity.ok(atributos);
    }

    // Adicionar item
    public ResponseEntity<?> adicionarItemAoPersonagem(Long id, ItemMagico item) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(id);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }

        Personagem personagem = personagemOpt.get();

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
                    .filter(i -> i.getTipo() == TipoItem.AMULETO).count();
            if (amuletos >= 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Personagem só pode ter 1 amuleto.");
            }
        }

        personagem.getItensMagicos().add(item);
        personagemRepository.save(personagem);
        return ResponseEntity.ok("Item adicionado com sucesso.");
    }

    // Remover item
    public ResponseEntity<?> removerItemDoPersonagem(Long id, Long itemId) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(id);
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

    // Buscar amuleto
    public ResponseEntity<?> getAmuletoDoPersonagem(Long id) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(id);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }

        Personagem personagem = personagemOpt.get();

        Optional<ItemMagico> amuleto = personagem.getItensMagicos().stream()
                .filter(i -> i.getTipo() == TipoItem.AMULETO)
                .findFirst();

        if (amuleto.isEmpty()) {
            return ResponseEntity.ok("Este personagem não possui um amuleto.");
        }

        return ResponseEntity.ok(amuleto.get());
    }

    // Atualizar nome do personagem
    public ResponseEntity<?> atualizarNomeAventureiro(Long id, String novoNome) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(id);

        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado.");
        }

        Personagem personagem = personagemOpt.get();
        personagem.setNome(novoNome);
        personagemRepository.save(personagem);

        return ResponseEntity.ok("Nome do personagem atualizado com sucesso.");
    }

}
