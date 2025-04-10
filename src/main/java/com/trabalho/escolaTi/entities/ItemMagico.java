//package com.trabalho.escolaTi.entities;
//
//import com.trabalho.escolaTi.service.PersonagemService;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Entity
//@Table(name = "tbl_itemMagico")
//public class ItemMagico {
//
//    @Autowired
//    private PersonagemService personagemService;
//
//    private Long id;
//    private String nome;
//    private String tipoDoItem;
//    private int forca;
//    private int defesa;
//
//    public ItemMagico() {}
//
//    public ItemMagico(PersonagemService personagemService, Long id, String nome, String tipoDoItem, int forca, int defesa) {
//        this.personagemService = personagemService;
//        this.id = id;
//        this.nome = nome;
//        this.tipoDoItem = tipoDoItem;
//        this.forca = forca;
//        this.defesa = defesa;
//    }
//
//    public PersonagemService getPersonagemService() {
//        return personagemService;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getTipoDoItem() {
//        return tipoDoItem;
//    }
//
//    public int getForca() {
//        return forca;
//    }
//
//    public int getDefesa() {
//        return defesa;
//    }
//
//    public void setPersonagemService(PersonagemService personagemService) {
//        this.personagemService = personagemService;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public void setTipoDoItem(String tipoDoItem) {
//        this.tipoDoItem = tipoDoItem;
//    }
//
//    public void setForca(int forca) {
//        this.forca = forca;
//    }
//
//    public void setDefesa(int defesa) {
//        this.defesa = defesa;
//    }
//}
