package com.pbl3.pbl.Model.Entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Esta classe tem por finalidade representar um filme.

// Os getters e setters foram implementados conforme a necessidade, apesar da pouca ação dos Setters;
// que podem ser utilizadas no futuro

/**
 * Representa um filme, herda características de {@link MidiaAV}.
 *
 * <p>Inclui informações adicionais específicas de filmes como direção, roteiro,
 * duração, avaliação, data de visualização e uma review textual.</p>
 */
public class Filme extends MidiaAV
{
    /** Lista de diretores do filme. */
    private ArrayList<String> direcao;

    /** Lista de roteiristas do filme. */
    private ArrayList<String> roteiro;

    /** Duração do filme em minutos. */
    private int duracao;

    /** Comentário/review pessoal sobre o filme. */
    private String review;

    /** Avaliação do filme em forma de nota (ex: de 0 a 10). */
    private int avaliacao;

    /** Data em que o filme foi assistido. */
    private LocalDate data;

    /**
     * Construtor para criar um novo objeto Filme com todos os seus atributos.
     *
     * @param titulo título do filme
     * @param ano ano de lançamento
     * @param categoria categoria associada
     * @param review comentário/review sobre o filme
     * @param avaliacao avaliação numérica do filme
     * @param data data em que o filme foi assistido
     * @param titulo_original título original do filme
     * @param onde_assistir plataforma ou local onde o filme foi assistido
     * @param duracao duração do filme em minutos
     * @param elenco lista de integrantes do elenco
     * @param roteiro lista de roteiristas
     * @param direcao lista de diretores
     */
    public Filme(String titulo, int ano, Categoria categoria, String review, int avaliacao, LocalDate data,
                 String titulo_original, String onde_assistir, int duracao,
                 ArrayList<String> elenco, ArrayList<String> roteiro,
                 ArrayList<String> direcao)
    {
        super(titulo, ano, categoria, titulo_original, onde_assistir, elenco);
        this.direcao = direcao;
        this.roteiro = roteiro;
        this.duracao = duracao;
        this.review = review;
        this.avaliacao = avaliacao;
        this.data = data;
    }

    /**
     * Retorna uma representação textual do filme com todas as informações.
     *
     * @return string contendo dados do filme
     */
    public String toString()
    {
        return super.toString() + "Direção: " + this.direcao +
                "\nRoteiro: " + this.roteiro +
                "\nDuracao: " + this.duracao + "\nFoi visto em:" + getDataFormatada() +
                "\nAvaliacao: " + this.avaliacao +
                "\nReview: "+ this.review + "\n";
    }

    /**
     * Retorna a lista de diretores do filme.
     *
     * @return lista de diretores
     */
    public ArrayList<String> getDirecao() {
        return this.direcao;
    }

    /**
     * Retorna a lista de roteiristas do filme.
     *
     * @return lista de roteiristas
     */
    public ArrayList<String> getRoteiro() {
        return this.roteiro;
    }

    /**
     * Retorna a duração do filme em minutos.
     *
     * @return duração em minutos
     */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     * Retorna a avaliação atribuída ao filme.
     *
     * @return nota de avaliação
     */
    public int getAvaliacao()
    {
        return this.avaliacao;
    }

    /**
     * Retorna a review escrita sobre o filme.
     *
     * @return texto da review
     */
    public String getReview()
    {
        return this.review;
    }

    /**
     * Retorna a data em que o filme foi assistido.
     *
     * @return data da visualização
     */
    public LocalDate getData()
    {
        return this.data;
    }

    /**
     * Retorna a data em que o filme foi assistido.
     *
     * @return data formatada para visualização
     */

    public String getDataFormatada()
    {
        LocalDate dataAux = this.data;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAux.format(formatter);
        return dataFormatada;
    }
}
