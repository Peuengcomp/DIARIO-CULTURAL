package com.pbl3.pbl.Model.Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Esta classe tem por finalidade representar uma temporada de uma série.

// Os getters e setters foram implementados conforme a necessidade, apesar da pouca ação dos Setters;
// que podem ser utilizadas no futuro

/**
 * Representa uma temporada de uma série.
 *
 * <p>Contém informações sobre a temporada, como ano, quantidade de episódios, avaliação e review.</p>
 */
public class Temporada implements Serializable
{
    /** Identificador único da temporada. */
    private int id_temporada;

    /** Ano de lançamento da temporada. */
    private int ano;

    /** Quantidade de episódios dessa temporada. */
    private int qtd_epsidios;

    /** Review sobre a temporada. */
    private String review;

    /** Avaliação da temporada. */
    private int avaliacao;

    /** Data de conclusão da temporada. */
    private LocalDate data;

    /**
     * Constrói uma temporada com os dados fornecidos.
     *
     * @param id_temporada Identificador da temporada.
     * @param ano Ano de lançamento da temporada.
     * @param qtd_epsidios Quantidade de episódios na temporada.
     * @param review Review sobre a temporada.
     * @param avaliacao Avaliação da temporada (de 0 a 10, por exemplo).
     * @param data Data em que a temporada foi finalizada.
     */
    public Temporada(int id_temporada, int ano, int qtd_epsidios, String review, int avaliacao, LocalDate data)
    {
        this.id_temporada = id_temporada;
        this.ano = ano;
        this.qtd_epsidios = qtd_epsidios;
        this.review = review;
        this.avaliacao = avaliacao;
        this.data = data;
    }

    /**
     * Retorna uma representação textual da temporada.
     *
     * @return String com detalhes sobre a temporada.
     */
    public String toString()
    {
        return "\nTemporada: " + this.id_temporada +
                "\nAno: " + this.ano +
                "\nQuantidade de episódios: " + this.qtd_epsidios +
                "\nFoi finalizada em: " + getDataFormatada() +
                "\nAvaliacao: " + this.avaliacao +
                "\nReview: "+ this.review + "\n";
    }

    /**
     * Retorna o identificador único da temporada.
     *
     * @return id da temporada
     */
    public int getId_temporada()
    {
        return this.id_temporada;
    }

    /**
     * Retorna o ano de lançamento da temporada.
     *
     * @return ano de lançamento
     */
    public int getAno() {
        return this.ano;
    }

    /**
     * Retorna a quantidade de episódios dessa temporada.
     *
     * @return quantidade de episódios
     */
    public int getQtd_epsidios() {
        return this.qtd_epsidios;
    }

    /**
     * Retorna a avaliação da temporada.
     *
     * @return avaliação
     */
    public int getAvaliacao()
    {
        return this.avaliacao;
    }

    /**
     * Retorna o review sobre a temporada.
     *
     * @return review da temporada
     */
    public String getReview()
    {
        return this.review;
    }

    /**
     * Retorna a data de conclusão da temporada.
     *
     * @return data de finalização
     */
    public LocalDate getData()
    {
        return this.data;
    }

    /**
     * Define o ano de lançamento da temporada.
     *
     * @param ano novo ano de lançamento
     */
    public void setAno(int ano) {
        this.ano = ano;
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