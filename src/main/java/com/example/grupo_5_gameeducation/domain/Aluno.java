package com.example.grupo_5_gameeducation.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * US1 - Liberacao automatica de 3 novos cursos ao concluir um curso com media acima de 7,0.
 *
 * Ciclo TDD do Cenario 1 (Bruno da Silveira Escanhoela). As tres etapas estao registradas
 * neste arquivo como evidencia do processo: RED e GREEN ficam comentadas e a versao BLUE
 * (refatorada) e a que compila e roda.
 */
public class Aluno {

    // =================================================================================
    // TDD - 1o PASSO: RED (teste falhando)
    // Stub sem regra de negocio. Lanca UnsupportedOperationException para garantir que o
    // teste falhe de verdade - se retornasse 0, o cenario de media <= 7,0 passaria por
    // acidente e a etapa RED ficaria invalida.
    //
    // RESULTADO OBTIDO: AlunoTest -> 1 erro
    //   java.lang.UnsupportedOperationException: regra de liberacao de cursos ainda nao
    //   implementada (AlunoTest.java, linha do aluno.concluir)
    // =================================================================================
    //
    // private final String nome;
    // private final Plano plano;
    //
    // public Aluno(String nome, Plano plano) {
    //     this.nome = nome;
    //     this.plano = plano;
    // }
    //
    // public void concluir(Curso curso, double media) {
    //     throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    // }
    //
    // public int getCursosLiberados() {
    //     throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    // }
    //
    // public int getCursosConquistados() {
    //     throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    // }

    // =================================================================================
    // TDD - 2o PASSO: GREEN (teste passando)
    // Implementacao mais simples que faz o teste passar. O Set de titulos garante que o
    // total de cursos conquistados nao conte o mesmo curso duas vezes.
    //
    // RESULTADO OBTIDO: getCursosLiberados() = 3 e getCursosConquistados() = 1
    //                   Esperado = Obtido -> teste passou
    // =================================================================================
    //
    // private final String nome;
    // private final Plano plano;
    // private final Set<String> cursosConcluidos = new HashSet<>();
    // private int cursosLiberados;
    //
    // public Aluno(String nome, Plano plano) {
    //     this.nome = nome;
    //     this.plano = plano;
    // }
    //
    // public void concluir(Curso curso, double media) {
    //     cursosConcluidos.add(curso.getTitulo());
    //     if (media > 7.0) {
    //         cursosLiberados += 3;
    //     }
    // }
    //
    // public int getCursosLiberados() {
    //     return cursosLiberados;
    // }
    //
    // public int getCursosConquistados() {
    //     return cursosConcluidos.size();
    // }

    // =================================================================================
    // TDD - 3o PASSO: BLUE / REFACTOR (versao ativa)
    //
    // REFATORACAO APLICADA: extracao de constantes com nome de negocio e do metodo
    //                       aprovado(), que da nome a regra "media acima de 7,0".
    // MOTIVO (code smell): numeros magicos (7.0 e 3) espalhados pelo codigo. Se a regra de
    //                      negocio mudar, seria preciso cacar os valores no meio da logica.
    // STATUS: testes seguem verdes.
    // =================================================================================

    private static final double MEDIA_MINIMA_APROVACAO = 7.0;
    private static final int CURSOS_LIBERADOS_POR_APROVACAO = 3;

    private final String nome;
    private final Plano plano;
    private final Set<String> cursosConcluidos = new HashSet<>();
    private int cursosLiberados;

    public Aluno(String nome, Plano plano) {
        this.nome = nome;
        this.plano = plano;
    }

    public void concluir(Curso curso, double media) {
        cursosConcluidos.add(curso.getTitulo());
        if (aprovado(media)) {
            cursosLiberados += CURSOS_LIBERADOS_POR_APROVACAO;
        }
    }

    private boolean aprovado(double media) {
        return media > MEDIA_MINIMA_APROVACAO;
    }

    public String getNome() {
        return nome;
    }

    public Plano getPlano() {
        return plano;
    }

    public int getCursosLiberados() {
        return cursosLiberados;
    }

    public int getCursosConquistados() {
        return cursosConcluidos.size();
    }
}
