package com.example.grupo_5_gameeducation.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
     * Cenario 3 da US1 - Joao Guilherme Volta Kinol.
     *
     * BDD:
     *   Dado que o aluno concluiu o curso "Qualidade de Software" com media 9,0
     *   E ja recebeu os 3 cursos referentes a essa conclusao
     *   Quando a conclusao do mesmo curso for registrada novamente
     *   Entao a liberacao nao deve ocorrer uma segunda vez
     *   E o aluno continua com 3 cursos liberados e 1 curso conquistado
     *
     * Execucao ao longo do ciclo (o Aluno.java e que muda, nao este teste):
     *   RED   -> Tests run: 3, Failures: 1, Errors: 0
     *            sem a guarda, a 2a conclusao bonifica de novo: getCursosLiberados() = 6,
     *            esperado 3 -> AssertionFailedError (RED valido).
     *   GREEN -> Tests run: 3, Failures: 0, Errors: 0
     *            a guarda com Set.add()==false ignora a conclusao repetida.
     *   BLUE  -> Tests run: 3, Failures: 0, Errors: 0
     *            guard clause + validacao de curso nulo nao mudaram o comportamento. Verde.
     */
    @Test
    @DisplayName("Cenario 3 (Joao): conclusao repetida do mesmo curso nao bonifica de novo")
    void naoDeveLiberarCursosDuasVezesParaOMesmoCurso() {
        // ARRANGE
        Aluno aluno = new Aluno("Joao Guilherme Volta Kinol", Plano.BASICO);
        Curso curso = new Curso("Qualidade de Software");

        // ACTION
        aluno.concluir(curso, 9.0);
        aluno.concluir(curso, 9.0); // deve ser ignorado

        // ASSERT
        assertEquals(3, aluno.getCursosLiberados());
        assertEquals(1, aluno.getCursosConquistados());
    }

    /**
     * Cobertura da guard clause do BLUE do Cenario 3 (Joao): concluir com curso nulo deve
     * lancar IllegalArgumentException, cobrindo o ramo de validacao de entrada no Jacoco.
     */
    @Test
    @DisplayName("Concluir com curso nulo lanca IllegalArgumentException")
    void deveLancarExcecaoQuandoCursoForNulo() {
        // ARRANGE
        Aluno aluno = new Aluno("Joao Guilherme Volta Kinol", Plano.BASICO);

        // ASSERT
        assertThrows(IllegalArgumentException.class, () -> aluno.concluir(null, 9.0));
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
