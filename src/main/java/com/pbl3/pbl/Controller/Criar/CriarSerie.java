package com.pbl3.pbl.Controller.Criar;

import com.pbl3.pbl.Controller.Salvar.Salvar;
import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;

import java.util.ArrayList;

/**
 * Esta classe tem por finalidade criar uma série e adicioná-la ao catálogo de séries.
 *
 * Essa abordagem foi utilizada em consideração ao padrão MVC, em que a função de criação e comunicação com repositórios
 * é delegada ao Controller.
 */
public class CriarSerie
{
    /**
     * Metodo responsável pela criação de uma série e adição ao catálogo de séries.
     *
     * @param titulo O título da série.
     * @param ano O ano de lançamento da série.
     * @param categoria A categoria da série, representada por uma instância de {@link Categoria}.
     * @param titulo_original O título original da série.
     * @param onde_assistir A plataforma ou local onde a série pode ser assistida.
     * @param elenco A lista de atores ou integrantes do elenco da série.
     * @param ano_de_encerramento O ano de encerramento da série.
     * @param temporadas A lista de temporadas da série, representada por uma lista de objetos {@link Temporada}.
     */
    public static void criarSerie(String titulo, int ano, Categoria categoria, String titulo_original,
                                  String onde_assistir, ArrayList<String> elenco, int ano_de_encerramento, ArrayList<Temporada> temporadas)
    {
        Serie serie = new Serie(titulo, ano, categoria, titulo_original, onde_assistir, elenco, ano_de_encerramento, temporadas);
        CatalogoSeries.getCatalogo().Adicionar(serie);
        Salvar.SalvarArquivos();
    }
}