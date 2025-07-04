package com.pbl3.pbl.Controller.Buscar;

import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.*;

import java.util.ArrayList;

/**
 * Esta classe tem por finalidade buscar um livro pelo seu autor ou ISBN.
 * Utiliza-se um processo de iteração para realizar a busca.
 *
 * Na busca por autor, o código percorre a lista de livros e retorna os que possuem o autor
 * correspondente ao que foi digitado.
 */
public class BuscarLivro
{
    /**
     * Método que busca livros em um catálogo pelo autor.
     *
     * @param autor O nome do autor a ser buscado.
     * @return Uma lista contendo os livros que possuem o autor correspondente, ou {@code null} se nenhum
     *         livro for encontrado.
     */
    public static ArrayList<Livro> buscarAutor(String autor)
    {
        ArrayList<Livro> resultado = new ArrayList<>();
        for (Livro livro : CatalogoLivros.getCatalogo().getLivros())
        {
            if (livro.getAutor().equals(autor))
                resultado.add(livro);
        }
        if (resultado.isEmpty())
            return null;
        return resultado;
    }

    /**
     * Método que busca um livro no catálogo pelo ISBN.
     *
     * @param ISBN O ISBN do livro a ser buscado.
     * @return O livro correspondente ao ISBN, ou {@code null} se nenhum livro for encontrado.
     */
    public static Livro buscarISBN(String ISBN)
    {
        for (Livro livro : CatalogoLivros.getCatalogo().getLivros())
        {
            if (livro.getISBN().equals(ISBN))
                return livro;
        }
        return null;
    }
}