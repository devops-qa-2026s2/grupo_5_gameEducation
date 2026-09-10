package com.example.grupo_5_gameeducation.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * US1 - Liberacao automatica de 3 novos cursos ao concluir um curso com media acima de 7,0.
 *
 * Cada bloco comentado e uma versao completa da classe num ponto do ciclo TDD.
 * So um bloco pode estar ativo por vez, senao a classe nao compila.
 *
 * Cenario 1 - Bruno da Silveira Escanhoela: extracao das constantes de negocio.
 * Cenario 2 - Gabriel Ferreira do Nascimento: extracao do metodo aprovado().
 * Cenario 3 - Joao Guilherme Volta Kinol: guard clause de conclusao repetida.
 */
public class Aluno {

    // ================= CENARIO 1 (Bruno) =================
    // BDD: media 8,5 -> libera 3 cursos, 1 curso conquistado.

    // RED: deveLiberarTresCursosQuandoMediaAcimaDeSete falha, stub sem regra de negocio.
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

    // GREEN: implementacao mais simples que faz o teste passar.
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

    // BLUE: extracao das constantes de negocio, tira os numeros magicos 7.0 e 3 do meio do codigo.
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

    // ================= CENARIO 2 (Gabriel) =================
    // BDD: media <= 7,0 (6,5 e 7,0) -> nenhum curso liberado.

    // RED: naoDeveLiberarCursosQuandoMediaNaoSuperaSete falha, mesmo stub do Cenario 1.
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

    // GREEN: nenhuma linha nova, o "maior que" do Cenario 1 ja cobre 6,5 e 7,0.
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

    // BLUE: extracao do metodo aprovado(double media), nomeia a fronteira entre "maior que" e "maior ou igual".
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

    // ================= CENARIO 3 (Joao) =================
    // BDD: concluir de novo o mesmo curso nao bonifica outra vez.

    // RED: naoDeveLiberarCursosDuasVezesParaOMesmoCurso falha, sem guarda a 2a conclusao
    // soma de novo (liberados = 6, esperado 3).
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
            cursosLiberados += CURSOS_LIBERADOS_POR_APROVACAO; // roda de novo na 2a vez
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

    // GREEN: Set.add() devolve false se o titulo ja existia, usado como guarda antes de
    // bonificar.
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
            return;
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

    // BLUE: guard clause + validacao de curso nulo. Motivo: curso nulo quebrava em silencio.

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
