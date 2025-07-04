package com.pbl3.pbl.Model.Entidades;

// Esta classe tem por finalidade representar uma midia, que é, neste problema uma superclasse de Midia AV e Livro

import java.io.Serializable;

/**
 * Representa uma mídia genérica, sendo superclasse de {@code MidiaAV} e {@code Livro}.
 *
 * <p>Contém informações básicas como título, ano e categoria.</p>
 */
public class Midia implements Serializable
{
    /** Título da mídia. */
    private String titulo;

    /** Ano de lançamento ou publicação da mídia. */
    private int ano;

    /** Categoria ou gênero da mídia. */
    private Categoria genero;

    /**
     * Construtor para inicializar os dados básicos da mídia.
     *
     * @param titulo título da mídia
     * @param ano ano de lançamento ou publicação
     * @param categoria categoria ou gênero associado
     */
    Midia(String titulo, int ano, Categoria categoria)
    {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = categoria;
    }

    /**
     * Retorna uma representação textual da mídia com título, ano e categoria.
     *
     * @return string formatada com os dados da mídia
     */
    public String toString()
    {
        return "Titulo: " + this.titulo + "\nAno: " + this.ano + "\nCategoria: " + this.genero.getGenero() + "\n";
    }

    /**
     * Retorna o título da mídia.
     *
     * @return título da mídia
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Retorna o ano de lançamento ou publicação da mídia.
     *
     * @return ano da mídia
     */
    public int getAno() {
        return this.ano;
    }

    /**
     * Retorna a categoria ou gênero da mídia.
     *
     * @return categoria da mídia
     */
    public Categoria getCategoria() {
        return this.genero;
    }

    /**
     * Define ou altera o título da mídia.
     *
     * @param titulo novo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Define ou altera o ano da mídia.
     *
     * @param ano novo ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Define ou altera a categoria da mídia.
     *
     * @param categoria nova categoria
     */
    public void setCategoria(Categoria categoria) {
        this.genero = categoria;
    }
}