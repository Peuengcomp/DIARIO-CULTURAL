package com.pbl3.pbl.Model.Entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Esta classe tem por finalidade representar um livro

// Os getters e setters foram implementados conforme a necessidade, apesar da pouca ação dos Setters;
// que podem ser utilizadas no futuro

/**
 * Representa um livro, herda características de {@link Midia}.
 *
 * <p>Inclui informações específicas de livros como autor, editora, ISBN,
 * presença de exemplar físico, data de leitura, avaliação e review.</p>
 */
public class Livro extends Midia
{
    /** Nome do autor do livro. */
    private String autor;

    /** Nome da editora responsável pela publicação. */
    private String editora;

    /** Código ISBN do livro. */
    private String ISBN;

    /** Indica se há um exemplar físico disponível. */
    private boolean tem_exemplar;

    /** Comentário ou review sobre o livro. */
    private String review;

    /** Avaliação do livro em forma de nota. */
    private int avaliacao;

    /** Data em que o livro foi finalizado. */
    private LocalDate data;

    /**
     * Construtor para inicializar todos os atributos do livro.
     *
     * @param titulo título do livro
     * @param ano ano de publicação
     * @param categoria categoria do livro
     * @param review comentário pessoal sobre o livro
     * @param avaliacao nota de avaliação
     * @param data data de finalização da leitura
     * @param autor nome do autor
     * @param editora nome da editora
     * @param ISBN código ISBN
     * @param tem_exemplar indica se há exemplar físico
     */
    public Livro(String titulo, int ano, Categoria categoria, String review,
                 int avaliacao, LocalDate data, String autor, String editora,
                 String ISBN, boolean tem_exemplar)
    {
        super(titulo, ano, categoria);
        this.autor = autor;
        this.editora = editora;
        this.ISBN = ISBN;
        this.tem_exemplar = tem_exemplar;
        this.review = review;
        this.avaliacao = avaliacao;
        this.data = data;
    }

    /**
     * Retorna uma representação textual do livro com todos os dados.
     *
     * @return string contendo informações do livro
     */
    @Override
    public String toString()
    {
        return super.toString() + "autor: " + this.autor + "\neditora: " + this.editora +
                "\nISBN: " + this.ISBN + "\ntem_exemplar: " + exemplar() +
                "\nFoi finalizado em: " + getDataFormatada() +
                "\nAvaliacao: " + this.avaliacao +
                "\nReview: "+ this.review + "\n";
    }

    /**
     * Retorna o nome do autor do livro.
     *
     * @return nome do autor
     */
    public String getAutor()
    {
        return this.autor;
    }

    /**
     * Retorna o nome da editora do livro.
     *
     * @return nome da editora
     */
    public String getEditora()
    {
        return this.editora;
    }

    /**
     * Retorna o código ISBN do livro.
     *
     * @return ISBN do livro
     */
    public String getISBN()
    {
        return this.ISBN;
    }

    /**
     * Indica se o livro possui exemplar físico.
     *
     * @return true se houver exemplar, false caso contrário
     */
    public boolean getTem_exemplar()
    {
        return this.tem_exemplar;
    }

    /**
     * Retorna a review escrita sobre o livro.
     *
     * @return texto da review
     */
    public String getReview()
    {
        return this.review;
    }

    /**
     * Retorna a nota de avaliação atribuída ao livro.
     *
     * @return avaliação numérica
     */
    public int getAvaliacao()
    {
        return this.avaliacao;
    }

    /**
     * Retorna a data em que o livro foi finalizado.
     *
     * @return data da leitura
     */
    public LocalDate getData()
    {
        return this.data;
    }

    /**
     * Retorna "Sim" ou "Não" indicando se há exemplar físico do livro.
     *
     * @return "Sim" se houver exemplar, "Não" caso contrário
     */
    public String exemplar()
    {
        if (getTem_exemplar())
            return "Sim";
        else
            return "Não";
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