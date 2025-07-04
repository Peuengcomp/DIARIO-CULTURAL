package com.pbl3.pbl.Model.Entidades;

import java.util.ArrayList;

// Esta classe representa a entidade série.

// Os getters e setters foram implementados conforme a necessidade, apesar da pouca ação dos Setters;
// que podem ser utilizadas no futuro

/**
 * Representa uma série, herdando de {@link MidiaAV}.
 *
 * <p>Contém informações como ano de encerramento, temporadas e avaliação média.</p>
 */
public class Serie extends MidiaAV
{
    /** Ano em que a série foi encerrada (caso já tenha sido finalizada). */
    private int ano_de_encerramento;

    /** Lista de temporadas da série. */
    private ArrayList<Temporada> temporadas;

    /** Avaliação média da série com base nas temporadas. */
    private float avaliacao;

    /**
     * Construtor que inicializa os dados da série.
     *
     * @param titulo título da série
     * @param ano ano de início da série
     * @param categoria categoria da série
     * @param titulo_original título original
     * @param onde_assistir plataforma onde pode ser assistida
     * @param elenco lista com nomes do elenco
     * @param ano_de_encerramento ano de encerramento (se aplicável)
     * @param temporadas lista de temporadas da série
     */
    public Serie(String titulo, int ano, Categoria categoria, String titulo_original,
                 String onde_assistir, ArrayList<String> elenco, int ano_de_encerramento, ArrayList<Temporada> temporadas)
    {
        super(titulo, ano, categoria, titulo_original, onde_assistir, elenco);
        this.ano_de_encerramento = ano_de_encerramento;
        this.temporadas = temporadas;
        this.avaliacao = setAvaliacao();
    }

    /**
     * Retorna uma representação textual da série.
     *
     * @return string contendo os dados principais da série
     */
    public String toString()
    {
        return super.toString() + "Ano de encerramento: " + this.ano_de_encerramento +
                "\nAvaliação: " + this.avaliacao + "\nTemporadas:" + "\n" + getStringTemporadas();
    }

    /**
     * Retorna o ano em que a série foi encerrada.
     *
     * @return ano de encerramento
     */
    public int getAno_de_encerramento() {
        return this.ano_de_encerramento;
    }

    /**
     * Retorna a lista de temporadas da série.
     *
     * @return lista de temporadas
     */
    public ArrayList<Temporada> getTemporadas()
    {
        return this.temporadas;
    }

    /**
     * Retorna a avaliação média da série com base nas temporadas.
     *
     * @return avaliação média
     */
    public float getAvaliacao()
    {
        return this.avaliacao;
    }

    /**
     * Concatena e retorna a representação textual de todas as temporadas da série.
     *
     * @return string com a descrição das temporadas
     */
    public String getStringTemporadas()
    {
        String temporadas = "";
        for (Temporada temporada : this.temporadas)
        {
            temporadas += temporada.toString();
        }
        return temporadas;
    }

    /**
     * Calcula a média das avaliações de todas as temporadas da série.
     *
     * @return avaliação média calculada
     */
    public float setAvaliacao()
    {
        float soma = 0;
        for (Temporada temporada : temporadas)
        {
            soma += (float) temporada.getAvaliacao();
        }
        float media_avaliacao = soma / temporadas.size();
        return media_avaliacao;
    }
}
