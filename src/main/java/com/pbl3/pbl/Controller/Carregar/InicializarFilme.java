package com.pbl3.pbl.Controller.Carregar;

import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Esta classe é responsável pela inicialização e carregamento dos filmes a partir de um arquivo.
 * O arquivo contém uma lista de filmes que será lida e carregada no catálogo de filmes.
 */
public class InicializarFilme
{
    // Nome do arquivo onde os filmes são armazenados.
    private static final String arquivo_filmes = "arquivo_filmes";

    /**
     * Este metodo inicializa o processo de carregamento dos filmes, verificando se o arquivo existe.
     * Caso o arquivo exista, o metodo {@link #Carregar()} será chamado para carregar os filmes no catálogo.
     */
    public static void inicializar()
    {
        File arquivo = new File(arquivo_filmes);

        // Se o arquivo existir, o metodo Carregar é chamado.
        if (arquivo.exists())
        {
            Carregar();
        }
    }

    /**
     * Este metodo carrega os filmes do arquivo e os adiciona ao catálogo de filmes.
     *
     *  O arquivo é lido usando um ObjectInputStream, e a lista de filmes é restaurada e inserida no catálogo
     *  de filmes (CatalogoFilmes).
     **/
    public static void Carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo_filmes)))
        {
            // Lê a lista de filmes do arquivo
            ArrayList<Filme> filmes = (ArrayList<Filme>) ois.readObject();

            // Define os filmes carregados no catálogo
            CatalogoFilmes.getCatalogo().setFilmes(filmes);
        }
        catch (ClassNotFoundException | IOException e)
        {
            System.out.println("Erro ao carregar filmes: " + e.getMessage());
        }
    }

    /**
     * Retorna o nome do arquivo onde os filmes são armazenados.
     *
     * @return O nome do arquivo de filmes.
     */
    public static String getArquivo_filmes()
    {
        return arquivo_filmes;
    }
}
