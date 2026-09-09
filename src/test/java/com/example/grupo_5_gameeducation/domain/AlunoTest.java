package com.example.grupo_5_gameeducation.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teste de aceitacao do Cenario 1 da US1 - Bruno da Silveira Escanhoela.
 *
 * BDD:
 *   Dado que o aluno possui assinatura basica ativa
 *   E concluiu o curso "Fundamentos de Agile Testing"
 *   Quando a media final registrada for 8,5
 *   E a conclusao for validada pela plataforma
 *   Entao o sistema deve liberar 3 novos cursos para o aluno
 *   E o total de cursos conquistados deve ser 1
 *
 * Este teste foi escrito na etapa RED, antes da implementacao, e NAO foi alterado depois.
 * Quem evolui ao longo do ciclo TDD e o Aluno.java, onde as tres etapas estao registradas.
 * O que muda aqui e apenas o resultado da execucao:
 *
 *   RED   -> Tests run: 1, Failures: 0, Errors: 1
 *            java.lang.UnsupportedOperationException: regra de liberacao de cursos ainda
 *            nao implementada. O teste falha por falta de implementacao (RED valido).
 *
 *   GREEN -> Tests run: 1, Failures: 0, Errors: 0
 *            getCursosLiberados() = 3 e getCursosConquistados() = 1. Esperado = Obtido.
 *
 *   BLUE  -> Tests run: 1, Failures: 0, Errors: 0
 *            Sem alterar o teste, ele garante que extrair as constantes e o metodo
 *            aprovado() nao mudou o comportamento. Testes seguem verdes.
 */
class AlunoTest {

    @Test
    @DisplayName("Cenario 1 (Bruno): media acima de 7,0 libera 3 novos cursos")
    void deveLiberarTresCursosQuandoMediaAcimaDeSete() {
        // ARRANGE
        Aluno aluno = new Aluno("Bruno da Silveira Escanhoela", Plano.BASICO);
        Curso curso = new Curso("Fundamentos de Agile Testing");

        // ACTION
        aluno.concluir(curso, 8.5);

        // ASSERT
        assertEquals(3, aluno.getCursosLiberados());
        assertEquals(1, aluno.getCursosConquistados());
    }

    /**
     * Teste de cobertura, nao de aceitacao: nao nasce de nenhum "Entao" do BDD, existe
     * para cobrir os getters do Aluno e zerar o vermelho do Jacoco.
     */
    @Test
    @DisplayName("Aluno expoe o nome e o plano informados na criacao")
    void deveExporNomeEPlanoDoAluno() {
        // ARRANGE
        Aluno aluno = new Aluno("Bruno da Silveira Escanhoela", Plano.BASICO);

        // ASSERT
        assertEquals("Bruno da Silveira Escanhoela", aluno.getNome());
        assertEquals(Plano.BASICO, aluno.getPlano());
    }
}
