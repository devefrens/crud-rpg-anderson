package com.trabalho.escolaTi.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level;
    private int forca;
    private int defesa;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    public enum Classe {
        GUERREIRO, MAGO, ARQUEIRO, LADINO, BARDO
    }

    public Personagem() {}

    public Personagem(String nome, String nomeAventureiro, Classe classe, int level, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
        validarDistribuicaoPontos();
    }

    @PrePersist
    @PreUpdate
    public void validarDistribuicaoPontos() {
        if (forca + defesa > 10) {
            throw new IllegalArgumentException("A soma de força e defesa base deve ser no máximo 10.");
        }
        if (forca < 0 || defesa < 0) {
            throw new IllegalArgumentException("Força e defesa base não podem ser negativas.");
        }
    }

    public int getForcaTotal() {
        int total = forca;
        for (ItemMagico item : itensMagicos) {
            total += item.getForca();
        }
        return total;
    }

    public int getDefesaTotal() {
        int total = defesa;
        for (ItemMagico item : itensMagicos) {
            total += item.getDefesa();
        }
        return total;
    }

    public void adicionarItemMagico(ItemMagico item) {
        if (item.getTipo() == ItemMagico.TipoItem.AMULETO) {
            for (ItemMagico existente : itensMagicos) {
                if (existente.getTipo() == ItemMagico.TipoItem.AMULETO) {
                    throw new IllegalArgumentException("Este personagem já possui um amuleto.");
                }
            }
        }
        itensMagicos.add(item);
    }

    public void removerItemMagico(Long itemId) {
        itensMagicos.removeIf(item -> item.getId().equals(itemId));
    }

    public ItemMagico getAmuleto() {
        for (ItemMagico item : itensMagicos) {
            if (item.getTipo() == ItemMagico.TipoItem.AMULETO) {
                return item;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setForca(int forca) {
        this.forca = forca;
        validarDistribuicaoPontos();
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
        validarDistribuicaoPontos();
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

}
