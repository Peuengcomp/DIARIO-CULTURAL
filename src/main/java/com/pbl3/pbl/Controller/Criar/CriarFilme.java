package com.pbl3.pbl.Controller.Criar;

import com.pbl3.pbl.Controller.Salvar.Salvar;
import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta classe tem como objetivo criar um filme e adicioná-lo ao catálogo.
 *
 * Essa abordagem foi utilizada em consideração ao padrão MVC, em que a função de criação e comunicação com repositórios
 * é delegada ao Controller.
 */
public class CriarFilme
{
    /**
     * Metodo responsável pela criação de um filme e adição ao catálogo de filmes.
     *
     * @param titulo O título do filme.
     * @param ano O ano de lançamento do filme.
     * @param categoria A categoria do filme, representada por uma instância de {@link Categoria}.
     * @param titulo_original O título original do filme.
     * @param duracao A duração do filme em minutos.
     * @param onde_assistir A plataforma ou local onde o filme pode ser assistido.
     * @param elenco A lista de atores ou integrantes do elenco do filme.
     * @param roteiro A lista de roteiristas do filme.
     * @param direcao A lista de diretores do filme.
     * @param review A resenha ou comentário sobre o filme.
     * @param avaliacao A avaliação do filme, geralmente em uma escala de 1 a 5.
     * @param data A data de lançamento do filme.
     */
    public static void CriarFilme(String titulo, int ano, Categoria categoria, String titulo_original, int duracao,
                                  String onde_assistir, ArrayList<String> elenco, ArrayList<String> roteiro, ArrayList<String> direcao,
                                  String review, int avaliacao, LocalDate data)
    {
        Filme filme = new Filme(titulo, ano, categoria,
                review, avaliacao, data,
                titulo_original, onde_assistir, duracao, elenco, roteiro, direcao);

        CatalogoFilmes.getCatalogo().Adicionar(filme);
        Salvar.SalvarArquivos();
    }
}