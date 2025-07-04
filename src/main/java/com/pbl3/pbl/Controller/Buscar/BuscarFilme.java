package com.pbl3.pbl.Controller.Buscar;

import com.pbl3.pbl.Model.Entidades.*;

import java.util.ArrayList;

/**
 * Esta classe tem como objetivo buscar um filme pelo seu diretor, conforme os requisitos do problema.
 * O processo de busca é feito por meio de um loop {@code for}, que percorre a lista de filmes e retorna
 * os filmes cujo diretor corresponde ao que foi digitado.
 */
public class BuscarFilme
{
    /**
     * Metodo que busca filmes em uma lista de filmes pelo nome do diretor.
     *
     * @param diretor O nome do diretor a ser buscado.
     * @param lista A lista de filmes onde a busca será realizada.
     * @return Uma lista contendo os filmes que possuem o diretor correspondente, ou {@code null} se nenhum
     *         filme for encontrado.
     */
    public static ArrayList<Filme> buscarDiretor(String diretor, ArrayList<Filme> lista)
    {
        ArrayList<Filme> resultado = new ArrayList<>();
        for (Filme f : lista)
        {
            for (String d : f.getDirecao())
                if (d.equals(diretor))
                    resultado.add(f);
        }
        if (resultado.isEmpty())
            return null;
        return resultado;
    }
}