package com.example.grupo_5_gameeducation.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * US1 - Liberacao automatica de 3 novos cursos ao concluir um curso com media acima de 7,0.
 *
 * Os ciclos TDD dos tres cenarios de aceitacao estao registrados neste arquivo como
 * evidencia do processo. Cada cenario traz as suas tres etapas - RED (stub que faz o teste
 * falhar), GREEN (implementacao mais simples que faz passar) e BLUE (refatoracao sem
 * mudanca de comportamento).
 *
 * Cenario 1 - Bruno da Silveira Escanhoela: extracao das constantes de negocio.
 * Cenario 2 - Gabriel Ferreira do Nascimento: extracao do metodo aprovado().
 * Cenario 3 - Joao Guilherme Volta Kinol: guard clause de conclusao repetida.
 *
 * COMO RODAR UMA ETAPA ESPECIFICA
 * Cada bloco traz o corpo completo da classe, entao a troca e direta: comente o bloco que
 * estiver ativo e descomente o que quiser testar. Exatamente UM bloco pode estar
 * descomentado por vez - dois blocos ativos declaram o mesmo metodo duas vezes e a classe
 * nao compila.
 *
 * Por padrao fica ativo o BLUE do Cenario 3, ultimo estado do ciclo e unico em que os sete
 * testes passam. Rodar uma etapa anterior faz falhar os testes dos cenarios que ainda nao
 * tinham sido implementados naquele momento - isso e o esperado, e a fotografia daquele
 * ponto do processo.
 *
 * Para desfazer a troca:
 *   git checkout -- src/main/java/com/example/grupo_5_gameeducation/domain/Aluno.java
 */
public class Aluno {

    // #################################################################################
    // CENARIO 1 (Bruno da Silveira Escanhoela)
    // BDD: media final acima de 7,0 (8,5) -> 3 novos cursos liberados e 1 curso
    //      conquistado.
    // #################################################################################

    // RED
    // Stub sem regra de negocio. Lanca UnsupportedOperationException para garantir que o
    // teste falhe de verdade - se retornasse 0, o cenario de media <= 7,0 passaria por
    // acidente e a etapa RED ficaria invalida.
    //
    // RESULTADO OBTIDO: AlunoTest -> 1 erro
    //   java.lang.UnsupportedOperationException: regra de liberacao de cursos ainda nao
    //   implementada (AlunoTest.java, linha do aluno.concluir)
    /*
    private final String nome;
    private final Plano plano;

    public Aluno(String nome, Plano plano) {
        this.nome = nome;
        this.plano = plano;
    }

    public void concluir(Curso curso, double media) {
        throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    }

    public String getNome() {
        return nome;
    }

    public Plano getPlano() {
        return plano;
    }

    public int getCursosLiberados() {
        throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    }

    public int getCursosConquistados() {
        throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    }

    */

    // GREEN
    // Implementacao mais simples que faz o teste passar. O Set de titulos garante que o
    // total de cursos conquistados nao conte o mesmo curso duas vezes.
    //
    // RESULTADO OBTIDO: getCursosLiberados() = 3 e getCursosConquistados() = 1
    //                   Esperado = Obtido -> teste passou
    /*
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
        if (media > 7.0) {
            cursosLiberados += 3;
        }
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
    */

    // BLUE
    // REFATORACAO APLICADA: extracao de constantes com nome de negocio
    //                       (MEDIA_MINIMA_APROVACAO e CURSOS_LIBERADOS_POR_APROVACAO).
    // MOTIVO (code smell): numeros magicos (7.0 e 3) espalhados pelo codigo. Se a regra de
    //                      negocio mudar, seria preciso cacar os valores no meio da logica.
    // STATUS: testes seguem verdes.
    /*
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
        if (media > MEDIA_MINIMA_APROVACAO) {
            cursosLiberados += CURSOS_LIBERADOS_POR_APROVACAO;
        }
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
    */

    // #################################################################################
    // CENARIO 2 (Gabriel Ferreira do Nascimento)
    // BDD: media final menor ou igual a 7,0 (6,5 e 7,0) -> nenhum curso adicional
    //      liberado, o aluno segue com 0 cursos liberados.
    // #################################################################################

    // RED
    // O teste naoDeveLiberarCursosQuandoMediaNaoSuperaSete foi escrito junto com o do
    // Cenario 1, contra o mesmo stub. Enquanto o concluir() lanca a excecao, ele falha.
    //
    // RESULTADO OBTIDO: AlunoTest -> erro
    //   java.lang.UnsupportedOperationException: regra de liberacao de cursos ainda nao
    //   implementada
    /*
    private final String nome;
    private final Plano plano;

    public Aluno(String nome, Plano plano) {
        this.nome = nome;
        this.plano = plano;
    }

    public void concluir(Curso curso, double media) {
        throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    }

    public String getNome() {
        return nome;
    }

    public Plano getPlano() {
        return plano;
    }

    public int getCursosLiberados() {
        throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    }

    public int getCursosConquistados() {
        throw new UnsupportedOperationException("regra de liberacao de cursos ainda nao implementada");
    }
    */

    // GREEN
    // Nenhuma linha nova foi necessaria. A regra escrita para o Cenario 1 ja cobre este
    // caso porque usa "maior que", nao "maior ou igual": as medias 6,5 e 7,0 nao entram
    // no if.
    //
    // RESULTADO OBTIDO: getCursosLiberados() = 0 e getCursosConquistados() = 1.
    //                   Esperado = Obtido -> teste passou.
    /*
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
        if (media > MEDIA_MINIMA_APROVACAO) {   // 6,5 e 7,0 ficam de fora
            cursosLiberados += CURSOS_LIBERADOS_POR_APROVACAO;
        }
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
    */

    // BLUE
    // REFATORACAO APLICADA: extracao do metodo aprovado(double media).
    // MOTIVO (code smell): a condicao "media > 7.0" nao tinha nome. O metodo nomeado
    //                      revela a intencao e evita a duvida entre "maior que" e "maior
    //                      ou igual", que e justamente a fronteira protegida por este
    //                      cenario.
    // STATUS: testes seguem verdes.
    /*
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
    */

    // #################################################################################
    // CENARIO 3 (Joao Guilherme Volta Kinol)
    // BDD: concluir de novo o mesmo curso (apos ja ter recebido os 3 cursos daquela
    //      conclusao) nao deve bonificar outra vez; o aluno segue com 3 cursos liberados
    //      e 1 curso conquistado.
    // #################################################################################

    // RED
    // O teste naoDeveLiberarCursosDuasVezesParaOMesmoCurso conclui o mesmo curso duas
    // vezes. Sem a guarda, a 2a conclusao tambem entra no if e bonifica de novo.
    //
    // RESULTADO OBTIDO: AlunoTest -> falha
    //   getCursosLiberados() = 6, esperado 3 -> org.opentest4j.AssertionFailedError
    /*
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
            cursosLiberados += CURSOS_LIBERADOS_POR_APROVACAO;   // roda de novo na 2a vez
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
    */

    // GREEN
    // Set.add() devolve false quando o titulo ja estava no conjunto; usamos esse retorno
    // como guarda para ignorar a conclusao repetida antes de bonificar.
    //
    // RESULTADO OBTIDO: getCursosLiberados() = 3 e getCursosConquistados() = 1
    //                   Esperado = Obtido -> teste passou.
    /*
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
        boolean primeiraConclusao = cursosConcluidos.add(curso.getTitulo());
        if (!primeiraConclusao) {
            return; // ja foi bonificado, ignora
        }
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
    */

    // BLUE  <<< BLOCO ATIVO - unica versao descomentada, e a que compila e roda >>>
    // REFATORACAO APLICADA: guard clause explicita + validacao de entrada de curso nulo.
    // MOTIVO (code smell): o metodo aceitava curso nulo em silencio e a intencao do return
    //                      antecipado da guarda de duplicidade nao estava explicita.
    // STATUS: testes seguem verdes.
    //
    // Ultimo estado do ciclo: reune as tres refatoracoes - as constantes de negocio
    // (Cenario 1), o metodo aprovado() (Cenario 2) e a guard clause com validacao de
    // entrada (Cenario 3). Os sete testes passam apenas aqui.

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
        if (curso == null) {
            throw new IllegalArgumentException("Curso obrigatorio para registrar conclusao");
        }
        boolean primeiraConclusao = cursosConcluidos.add(curso.getTitulo());
        if (!primeiraConclusao) {
            return; // ja foi bonificado, ignora
        }
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
