package com.pbl3.pbl.Model.Repositorios;

import com.pbl3.pbl.Model.Entidades.Filme;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Representa o catálogo de filmes, implementando o padrão Singleton.
 * Esta classe é responsável por gerenciar a coleção de filmes,
 * garantindo que exista apenas uma instância da classe e proporcionando
 * métodos para ordenar e adicionar filmes ao catálogo.
 */
public class CatalogoFilmes implements iCatalogo<Filme>
{
    /** Instância única do catálogo de filmes (Singleton). */
    private static CatalogoFilmes colecao_filmes;

    /** Lista de filmes armazenados no catálogo. */
    private ArrayList<Filme> filmes;

    /**
     * Construtor privado para garantir que a classe siga o padrão Singleton.
     * Inicializa a coleção de filmes.
     */
    private CatalogoFilmes() {
        CriarCatalogo();
    }

    /**
     * Cria a lista de filmes. Este método é chamado no construtor para inicializar o catálogo.
     */
    private void CriarCatalogo() {
        this.filmes = new ArrayList<>();
    }

    /**
     * Retorna a instância única do catálogo de filmes.
     *
     * @return A instância do catálogo de filmes.
     */
    public static CatalogoFilmes getCatalogo() {
        if (colecao_filmes == null) {
            colecao_filmes = new CatalogoFilmes();
        }
        return colecao_filmes;
    }

    /**
     * Retorna a lista de filmes no catálogo.
     *
     * @return Lista de filmes.
     */
    public ArrayList<Filme> getFilmes()
    {
        return this.filmes;
    }

    /**
     * Define a lista de filmes no catálogo.
     *
     * @param filmes Lista de filmes a ser definida.
     */
    public void setFilmes(ArrayList<Filme> filmes)
    {
        this.filmes = filmes;
    }

    /**
     * Lista os filmes no catálogo e os ordena conforme a avaliação.
     * Caso a lista de filmes esteja vazia, uma mensagem é exibida.
     *
     * @param ordem Se verdadeiro, ordena os filmes em ordem crescente de avaliação,
     *              caso contrário, ordena em ordem decrescente de avaliação.
     * @return <code>true</code> se a operação foi bem-sucedida, <code>false</code> se não houver filmes.
     */
    @Override
    public boolean ListarOrdenar(boolean ordem)
    {
        if (filmes.isEmpty())
        {
            System.out.println("Nenhum filme cadastrado");
            return false;
        }
        else
        {
            if (ordem)
                filmes.sort(Comparator.comparingInt(Filme::getAvaliacao));
            else
                filmes.sort(Comparator.comparingInt(Filme::getAvaliacao).reversed());
        }
        return true;
    }

    /**
     * Adiciona um filme ao catálogo.
     *
     * @param objeto O filme a ser adicionado ao catálogo.
     */
    @Override
    public void Adicionar(Filme objeto)
    {
        filmes.add(objeto);
    }
}
