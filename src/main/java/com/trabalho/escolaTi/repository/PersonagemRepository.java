package com.trabalho.escolaTi.repository;

import com.trabalho.escolaTi.entities.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {}
