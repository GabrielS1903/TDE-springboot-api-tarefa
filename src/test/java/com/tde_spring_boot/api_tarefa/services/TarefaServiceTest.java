package com.tde_spring_boot.api_tarefa.services;

import com.tde_spring_boot.api_tarefa.entities.TarefaEntity;
import com.tde_spring_boot.api_tarefa.repositories.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private TarefaEntity tarefaEntity;
    private UUID tarefaId;

    @BeforeEach
    public void setUp() {
        tarefaId = UUID.randomUUID();
        tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(tarefaId);
        tarefaEntity.setDescricao("Teste Tarefa");
        tarefaEntity.setDataLimite(new Date());
    }

    @Test
    public void testSalvar() {
        Mockito.when(tarefaRepository.save(any(TarefaEntity.class))).thenReturn(tarefaEntity);

        TarefaEntity tarefaSalva = (TarefaEntity) tarefaService.save(tarefaEntity);

        assertEquals(tarefaEntity.getId(), tarefaSalva.getId());
        assertEquals(tarefaEntity.getDescricao(), tarefaSalva.getDescricao());
        assertEquals(tarefaEntity.getDataLimite(), tarefaSalva.getDataLimite());
    }

    @Test
    public void testObterTodasTarefas() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TarefaEntity> page = new PageImpl<>(Collections.singletonList(tarefaEntity));
        Mockito.when(tarefaRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<TarefaEntity> resultado = tarefaService.getAllTarefas(pageable);

        assertEquals(1, resultado.getTotalElements());
        assertEquals(tarefaEntity.getId(), resultado.getContent().get(0).getId());
    }

    @Test
    public void testObterUmaTarefa() {
        Mockito.when(tarefaRepository.findById(eq(tarefaId))).thenReturn(Optional.of(tarefaEntity));

        Optional<TarefaEntity> resultado = tarefaService.getOneTarefa(tarefaId);

        assertTrue(resultado.isPresent());
        assertEquals(tarefaEntity.getId(), resultado.get().getId());
    }

    @Test
    public void testDeletarTarefa() {
        tarefaService.deleteTarefa(tarefaEntity);

        Mockito.verify(tarefaRepository, Mockito.times(1)).delete(tarefaEntity);
    }
}
