package com.pbl3.pbl.Model.Entidades;

import java.util.ArrayList;

// Esta classe tem por finalidade descrever a superclasse Midia AV, que irá prover herança para filmes e séries.

// Os getters e setters foram implementados conforme a necessidade, apesar da pouca ação dos Setters;
// que podem ser utilizadas no futuro

/**
 * Representa uma mídia audiovisual, sendo superclasse de {@code Filme} e possivelmente outras.
 *
 * <p>Estende {@link Midia} com atributos específicos como título original, onde assistir e elenco.</p>
 */
public class MidiaAV extends Midia
{
    /** Título original da mídia (caso seja diferente do título traduzido). */
    private String titulo_original;

    /** Plataforma ou local onde a mídia está disponível. */
    private String onde_assistir;

    /** Lista de integrantes do elenco da mídia. */
    private ArrayList<String> elenco;

    /**
     * Construtor para inicializar uma mídia audiovisual com seus atributos.
     *
     * @param titulo título da mídia
     * @param ano ano de lançamento
     * @param categoria categoria da mídia
     * @param titulo_original título original da obra
     * @param onde_assistir local ou plataforma onde a mídia pode ser assistida
     * @param elenco lista dos integrantes do elenco
     */
    MidiaAV(String titulo, int ano, Categoria categoria,
            String titulo_original, String onde_assistir, ArrayList<String> elenco)
    {
        super(titulo, ano, categoria);
        this.titulo_original = titulo_original;
        this.onde_assistir = onde_assistir;
        this.elenco = elenco;
    }

    /**
     * Retorna uma representação textual da mídia audiovisual.
     *
     * @return string formatada com os dados da mídia
     */
    public String toString()
    {
        return super.toString() + "Título Original: " + this.titulo_original +
                "\nOnde Assistir: " + this.onde_assistir +
                "\nElenco: " + this.elenco + "\n";
    }

    /**
     * Retorna o título original da mídia.
     *
     * @return título original
     */
    public String getTitulo_original() {
        return this.titulo_original;
    }

    /**
     * Retorna o local ou plataforma onde a mídia está disponível.
     *
     * @return onde assistir
     */
    public String getOnde_assistir() {
        return this.onde_assistir;
    }

    /**
     * Retorna a lista do elenco da mídia.
     *
     * @return lista de nomes do elenco
     */
    public ArrayList<String> getElenco() {
        return this.elenco;
    }

    /**
     * Define ou altera a lista de integrantes do elenco.
     *
     * @param elenco nova lista de elenco
     */
    public void setElenco(ArrayList<String> elenco)
    {
        this.elenco = elenco;
    }
}
