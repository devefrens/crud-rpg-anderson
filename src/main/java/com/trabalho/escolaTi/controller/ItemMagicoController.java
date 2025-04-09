package com.trabalho.escolaTi.controller;

import com.trabalho.escolaTi.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ItemMagicoController {

    @Autowired
    private PersonagemService personagemService;

}
