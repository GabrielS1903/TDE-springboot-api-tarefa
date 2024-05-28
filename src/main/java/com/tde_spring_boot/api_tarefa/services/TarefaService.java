package com.tde_spring_boot.api_tarefa.services;

import com.tde_spring_boot.api_tarefa.entities.TarefaEntity;
import com.tde_spring_boot.api_tarefa.repositories.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Transactional
    public Object save(TarefaEntity tarefaEntity) {
        return tarefaRepository.save(tarefaEntity);
    }

    public Page<TarefaEntity> getAllTarefas(Pageable pageable) {
        return tarefaRepository.findAll(pageable);
    }

    public Optional<TarefaEntity> getOneTarefa(UUID id) {
        return tarefaRepository.findById(id);
    }

    @Transactional
    public void deleteTarefa(TarefaEntity tarefaEntity) {
        tarefaRepository.delete(tarefaEntity);
    }
}
