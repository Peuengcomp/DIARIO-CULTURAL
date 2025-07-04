package com.pbl3.pbl.Model.Repositorios;

import com.pbl3.pbl.Model.Entidades.Livro;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Representa o catálogo de livros, implementando o padrão Singleton.
 * Esta classe é responsável por gerenciar a coleção de livros,
 * garantindo que exista apenas uma instância da classe e proporcionando
 * métodos para ordenar e adicionar livros ao catálogo.
 */
public class CatalogoLivros implements iCatalogo<Livro>
{
    /** Instância única do catálogo de livros (Singleton). */
    private static CatalogoLivros colecao_livros;

    /** Lista de livros armazenados no catálogo. */
    private ArrayList<Livro> livros;

    /**
     * Construtor privado para garantir que a classe siga o padrão Singleton.
     * Inicializa a coleção de livros.
     */
    private CatalogoLivros() {
        CriarCatalogo();
    }

    /**
     * Cria a lista de livros. Este metodo é chamado no construtor para inicializar o catálogo.
     */
    private void CriarCatalogo() {
        this.livros = new ArrayList<>();
    }

    /**
     * Retorna a instância única do catálogo de livros.
     *
     * @return A instância do catálogo de livros.
     */
    public static CatalogoLivros getCatalogo() {
        if (colecao_livros == null) {
            colecao_livros = new CatalogoLivros();
        }
        return colecao_livros;
    }

    /**
     * Retorna a lista de livros no catálogo.
     *
     * @return Lista de livros.
     */
    public ArrayList<Livro> getLivros()
    {
        return this.livros;
    }

    /**
     * Define a lista de livros no catálogo.
     *
     * @param livros Lista de livros a ser definida.
     */
    public void setLivros(ArrayList<Livro> livros)
    {
        this.livros = livros;
    }

    /**
     * Lista os livros no catálogo e os ordena conforme a avaliação.
     * Caso a lista de livros esteja vazia, uma mensagem é exibida.
     *
     * @param ordem Se verdadeiro, ordena os livros em ordem crescente de avaliação,
     *              caso contrário, ordena em ordem decrescente de avaliação.
     * @return <code>true</code> se a operação foi bem-sucedida, <code>false</code> se não houver livros.
     */
    @Override
    public boolean ListarOrdenar(boolean ordem)
    {
        if (livros.isEmpty())
        {
            System.out.println("Nenhum livro cadastrado");
            return false;
        }
        else
        {
            if (ordem)
                livros.sort(Comparator.comparingInt(Livro::getAvaliacao));
            else
                livros.sort(Comparator.comparingInt(Livro::getAvaliacao).reversed());

        }
        return true;
    }

    /**
     * Adiciona um livro ao catálogo.
     *
     * @param objeto O livro a ser adicionado ao catálogo.
     */
    @Override
    public void Adicionar(Livro objeto)
    {
        livros.add(objeto);
    }
}