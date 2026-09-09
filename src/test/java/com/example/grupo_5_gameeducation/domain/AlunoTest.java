package com.example.grupo_5_gameeducation.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Testes de aceitacao da US1 - liberacao automatica de 3 novos cursos ao concluir um
 * curso com media acima de 7,0.
 *
 * Um teste por cenario BDD, um cenario por integrante. Cada teste foi escrito na etapa
 * RED, antes da implementacao, e nao foi alterado depois. Quem evolui ao longo do ciclo
 * TDD e o Aluno.java, onde as etapas RED, GREEN e BLUE de cada cenario estao registradas.
 * O que muda em cada teste e so o resultado da execucao, anotado no javadoc do metodo.
 */
class AlunoTest {

    /**
     * Cenario 1 da US1 - Bruno da Silveira Escanhoela.
     *
     * BDD:
     *   Dado que o aluno possui assinatura basica ativa
     *   E concluiu o curso "Fundamentos de Agile Testing"
     *   Quando a media final registrada for 8,5
     *   E a conclusao for validada pela plataforma
     *   Entao o sistema deve liberar 3 novos cursos para o aluno
     *   E o total de cursos conquistados deve ser 1
     *
     * Execucao ao longo do ciclo (o Aluno.java e que muda, nao este teste):
     *   RED   -> Tests run: 1, Failures: 0, Errors: 1
     *            java.lang.UnsupportedOperationException: regra de liberacao de cursos
     *            ainda nao implementada. Falha por falta de implementacao (RED valido).
     *   GREEN -> Tests run: 1, Failures: 0, Errors: 0
     *            getCursosLiberados() = 3 e getCursosConquistados() = 1. Esperado = Obtido.
     *   BLUE  -> Tests run: 1, Failures: 0, Errors: 0
     *            extrair as constantes de negocio nao mudou o comportamento. Segue verde.
     */
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
     * Cenario 2 da US1 - Gabriel Ferreira do Nascimento.
     *
     * BDD:
     *   Dado que o aluno possui assinatura basica ativa
     *   E concluiu o curso "Introducao a DevOps"
     *   Quando a media final registrada for menor ou igual a 7,0 (6,5 e 7,0)
     *   E a conclusao for validada pela plataforma
     *   Entao nenhum curso adicional deve ser liberado
     *   E o aluno permanece com 0 cursos liberados
     *
     * Execucao ao longo do ciclo (o Aluno.java e que muda, nao este teste):
     *   RED   -> Tests run: 2, Failures: 0, Errors: 2
     *            mesma UnsupportedOperationException do stub, para as medias 6,5 e 7,0.
     *   GREEN -> Tests run: 2, Failures: 0, Errors: 0
     *            nenhuma linha nova: a regra do Cenario 1 usa "media > 7,0", entao 6,5 e
     *            7,0 nao liberam bonus. getCursosLiberados() = 0, getCursosConquistados() = 1.
     *   BLUE  -> Tests run: 2, Failures: 0, Errors: 0
     *            extrair o metodo aprovado() deu nome a fronteira "maior que". Segue verde.
     */
    @ParameterizedTest
    @ValueSource(doubles = {6.5, 7.0})
    @DisplayName("Cenario 2 (Gabriel): media menor ou igual a 7,0 nao libera cursos")
    void naoDeveLiberarCursosQuandoMediaNaoSuperaSete(double media) {
        // ARRANGE
        Aluno aluno = new Aluno("Gabriel Ferreira do Nascimento", Plano.BASICO);
        Curso curso = new Curso("Introducao a DevOps");

        // ACTION
        aluno.concluir(curso, media);

        // ASSERT
        assertEquals(0, aluno.getCursosLiberados());
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
