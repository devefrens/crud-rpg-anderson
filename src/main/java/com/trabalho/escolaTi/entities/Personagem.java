package com.trabalho.escolaTi.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String nomeAventureito;
    private String classe;
    private int level;
    private String itensMagicos;
    private int forca;
    private  int defesa;

    public Personagem() {}

    public Personagem(Long id, String nome, String nomeAventureito, String classe, int level, String itensMagicos, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureito = nomeAventureito; // nomeFantsia
        this.classe = classe;
        this.level = level;
        this.itensMagicos = itensMagicos;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeAventureito() {
        return nomeAventureito;
    }

    public String getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public String getItensMagicos() {
        return itensMagicos;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeAventureito(String nomeAventureito) {
        this.nomeAventureito = nomeAventureito;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setItensMagicos(String itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}
