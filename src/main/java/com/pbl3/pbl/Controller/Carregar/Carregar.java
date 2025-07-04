package com.pbl3.pbl.Controller.Carregar;

/**
 * Classe responsável por carregar todos os arquivos necessários ao iniciar a aplicação.
 *
 * A função de carregar arquivos é centralizada neste controlador, onde os arquivos de livros,
 * filmes e séries são carregados e adicionados aos respectivos catálogos.
 */
public class Carregar
{
    /**
     * Carrega os arquivos de livros, filmes e séries.
     *
     * Este metodo invoca os métodos de inicialização de livros, filmes e séries, garantindo
     * que os dados dessas entidades sejam carregados a partir dos arquivos respectivos,
     * quando esses arquivos existem.
     */
    public static void CarregarArquivos()
    {
        InicializarLivro.inicializar();
        InicializarFilme.inicializar();
        InicializarSerie.inicializar();
    }
}