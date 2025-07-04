package com.pbl3.pbl.Controller.Carregar;

import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Classe responsável por carregar a lista de séries do arquivo e adicioná-las ao catálogo de séries.
 *
 * A classe verifica se o arquivo de séries existe e, caso exista, carrega os dados serializados do arquivo,
 * restaurando o estado do catálogo de séries.
 */
public class InicializarSerie
{
    // Caminho do arquivo onde as séries estão armazenadas
    private static final String arquivo_series = "arquivo_series";

    /**
     * Verifica se o arquivo de séries existe. Caso exista, chama o método para carregar os dados.
     */
    public static void inicializar()
    {
        File arquivo = new File(arquivo_series);

        if (arquivo.exists())
        {
            Carregar();
        }
    }

    /**
     * Carrega o arquivo que contém a lista de séries serializadas e adiciona as séries no catálogo.
     *
     * O arquivo é lido usando um ObjectInputStream, e a lista de séries é restaurada e inserida no catálogo
     * de séries (CatalogoSeries).
     */
    public static void Carregar()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo_series)))
        {
            // Lê a lista de séries serializada do arquivo
            ArrayList<Serie> series = (ArrayList<Serie>) ois.readObject();
            // Adiciona as séries carregadas ao catálogo de séries
            CatalogoSeries.getCatalogo().setSeries(series);
        }
        catch (ClassNotFoundException | IOException e)
        {
            // Exibe uma mensagem caso ocorra um erro ao carregar o arquivo
            System.out.println("Erro ao carregar series: " + e.getMessage());
        }
    }

    /**
     * Obtém o caminho do arquivo onde as séries são armazenadas.
     *
     * @return O caminho do arquivo "arquivo_series".
     */
    public static String getArquivo_series()
    {
        return arquivo_series;
    }
}