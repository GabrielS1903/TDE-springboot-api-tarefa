package com.tde_spring_boot.api_tarefa.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tde_spring_boot.api_tarefa.configs.DateConfig;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_TAREFA")
public class TarefaEntity implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataCriacao;
    @JsonDeserialize(using = DateConfig.class)
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataLimite;
    @Column(nullable = false)
    private boolean finalizada = false;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
