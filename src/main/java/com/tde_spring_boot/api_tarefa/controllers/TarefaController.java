package com.tde_spring_boot.api_tarefa.controllers;

import com.tde_spring_boot.api_tarefa.dtos.TarefaDto;
import com.tde_spring_boot.api_tarefa.entities.TarefaEntity;
import com.tde_spring_boot.api_tarefa.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping("/tasks")
    public ResponseEntity<Object> saveTarefa(@RequestBody @Valid TarefaDto tarefaDto) {
        TarefaEntity tarefaEntity = new TarefaEntity();
        BeanUtils.copyProperties(tarefaDto, tarefaEntity);
        tarefaEntity.setDataCriacao(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.save(tarefaEntity));
    }

    @GetMapping("/tasks")
    public ResponseEntity<Page<TarefaEntity>> getAllTarefas(@PageableDefault(page = 0, size = 10, sort = "id",
                                                                             direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.getAllTarefas(pageable));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getOneTarefa(@PathVariable(value = "id") java.util.UUID id) {
        Optional<TarefaEntity> tarefaOptional = tarefaService.getOneTarefa(id);

        if (tarefaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefaOptional.get());
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> putTarefa(@PathVariable(value = "id") UUID id, @RequestBody @Valid TarefaDto tarefaDto) {
        Optional<TarefaEntity> tarefaOptional = tarefaService.getOneTarefa(id);

        if (tarefaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }

        TarefaEntity tarefaEntity = new TarefaEntity();
        BeanUtils.copyProperties(tarefaDto, tarefaEntity);
        tarefaEntity.setId(tarefaOptional.get().getId());
        tarefaEntity.setDataCriacao(tarefaOptional.get().getDataCriacao());

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.save(tarefaEntity));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTarefa(@PathVariable(value = "id") UUID id) {
        Optional<TarefaEntity> tarefaOptional = tarefaService.getOneTarefa(id);

        if (tarefaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }

        tarefaService.deleteTarefa(tarefaOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso!");
    }

    @PatchMapping("/tasks/{id}/finalizarTarefa")
    public ResponseEntity<Object> patchTarefa(@PathVariable(value = "id") UUID id) {
        Optional<TarefaEntity> tarefaOptional = tarefaService.getOneTarefa(id);

        if (tarefaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(tarefaOptional.get().getId());
        tarefaEntity.setDescricao(tarefaOptional.get().getDescricao());
        tarefaEntity.setDataCriacao(tarefaOptional.get().getDataCriacao());
        tarefaEntity.setDataLimite(tarefaOptional.get().getDataLimite());
        tarefaEntity.setFinalizada(Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.save(tarefaEntity));
    }
}
