package com.tde_spring_boot.api_tarefa.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tde_spring_boot.api_tarefa.configs.DateConfig;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class TarefaDto {

    @NotBlank
    @Size(max = 100)
    private String descricao;
    @NotNull
    @JsonDeserialize(using = DateConfig.class)
    private Date dataLimite;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }
}
