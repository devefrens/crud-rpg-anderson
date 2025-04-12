package com.trabalho.escolaTi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "tbl_itemMagico")
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @Min(0)
    @Max(10)
    private int forca;

    @Min(0)
    @Max(10)
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public ItemMagico() {}

    public ItemMagico(String nome, TipoItem tipo, int forca, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
        validarItem();
    }

    @PrePersist
    @PreUpdate
    public void validarItem() {
        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("Item não pode ter força e defesa iguais a zero.");
        }

        if (forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("Força e Defesa devem ser no máximo 10.");
        }

        switch (tipo) {
            case ARMA -> this.defesa = 0;
            case ARMADURA -> this.forca = 0;
            case AMULETO -> {} // Amuleto pode ter ambos
            default -> throw new IllegalArgumentException("Tipo de item inválido.");
        }
    }

    public enum TipoItem {
        ARMA, ARMADURA, AMULETO
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getNome() { return nome; }

    public TipoItem getTipo() { return tipo; }

    public int getForca() { return forca; }

    public int getDefesa() { return defesa; }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setId(Long id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
        validarItem();
    }

    public void setForca(int forca) {
        this.forca = forca;
        //validarItem();
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
        //validarItem();
    }
}
