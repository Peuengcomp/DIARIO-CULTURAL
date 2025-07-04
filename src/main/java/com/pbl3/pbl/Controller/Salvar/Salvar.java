package com.pbl3.pbl.Controller.Salvar;

/**
 * Classe responsável por gerenciar o salvamento de dados de livros, filmes e séries.
 * Utiliza os métodos de salvar específicos para cada tipo de mídia.
 */
public class Salvar
{
    /**
     * Metodo estático que chama os métodos de salvar para livros, filmes e séries.
     * Este metodo centraliza o processo de salvamento dos dados de todos os tipos de mídia.
     */
    public static void SalvarArquivos()
    {
        SalvarLivro.Salvar();
        SalvarFilme.Salvar();
        SalvarSerie.Salvar();
    }
}

