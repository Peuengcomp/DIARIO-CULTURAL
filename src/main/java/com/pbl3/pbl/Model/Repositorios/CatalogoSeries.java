package com.pbl3.pbl.Model.Repositorios;

import com.pbl3.pbl.Model.Entidades.Serie;

import java.util.Comparator;
import java.util.ArrayList;

/**
 * Representa o catálogo de séries, implementando o padrão Singleton.
 * Esta classe é responsável por gerenciar a coleção de séries,
 * garantindo que exista apenas uma instância da classe e proporcionando
 * métodos para ordenar e adicionar séries ao catálogo.
 */
public class CatalogoSeries implements iCatalogo<Serie>
{
    /** Instância única do catálogo de séries (Singleton). */
    private static CatalogoSeries colecao_series;

    /** Lista de séries armazenadas no catálogo. */
    private ArrayList<Serie> series;

    /**
     * Construtor privado para garantir que a classe siga o padrão Singleton.
     * Inicializa a coleção de séries.
     */
    private CatalogoSeries() {
        CriarCatalogo();
    }

    /**
     * Cria a lista de séries. Este método é chamado no construtor para inicializar o catálogo.
     */
    private void CriarCatalogo() {
        this.series = new ArrayList<>();
    }

    /**
     * Retorna a instância única do catálogo de séries.
     *
     * @return A instância do catálogo de séries.
     */
    public static CatalogoSeries getCatalogo() {
        if (colecao_series == null) {
            colecao_series = new CatalogoSeries();
        }
        return colecao_series;
    }

    /**
     * Retorna a lista de séries no catálogo.
     *
     * @return Lista de séries.
     */
    public ArrayList<Serie> getSeries()
    {
        return this.series;
    }

    /**
     * Define a lista de séries no catálogo.
     *
     * @param series Lista de séries a ser definida.
     */
    public void setSeries(ArrayList<Serie> series)
    {
        this.series = series;
    }

    /**
     * Lista as séries no catálogo e as ordena conforme a avaliação.
     * Caso a lista de séries esteja vazia, uma mensagem é exibida.
     *
     * @param ordem Se verdadeiro, ordena as séries em ordem crescente de avaliação,
     *              caso contrário, ordena em ordem decrescente de avaliação.
     * @return <code>true</code> se a operação foi bem-sucedida, <code>false</code> se não houver séries.
     */
    @Override
    public boolean ListarOrdenar(boolean ordem)
    {
        if (series.isEmpty())
        {
            System.out.println("Nenhuma série cadastrada");
            return false;
        }
        else
        {
            if (ordem)
                series.sort(Comparator.comparingDouble(Serie::getAvaliacao));
            else
                series.sort(Comparator.comparingDouble(Serie::getAvaliacao).reversed());
        }
        return true;
    }

    /**
     * Adiciona uma série ao catálogo.
     *
     * @param objeto A série a ser adicionada ao catálogo.
     */
    @Override
    public void Adicionar(Serie objeto)
    {
        series.add(objeto);
    }
}
