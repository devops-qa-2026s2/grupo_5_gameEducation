package com.example.grupo_5_gameeducation.dto;

import com.example.grupo_5_gameeducation.domain.Plano;
import com.example.grupo_5_gameeducation.entity.AlunoEntity;

public record AlunoResponse(
        Long id,
        String nome,
        Plano plano,
        int cursosLiberados,
        int cursosConquistados) {

    public static AlunoResponse from(AlunoEntity entity) {
        return new AlunoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getPlano(),
                entity.getCursosLiberados(),
                entity.getCursosConquistados());
    }
}
