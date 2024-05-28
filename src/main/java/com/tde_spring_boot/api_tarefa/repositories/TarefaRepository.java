package com.tde_spring_boot.api_tarefa.repositories;

import com.tde_spring_boot.api_tarefa.entities.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, UUID> {
}
