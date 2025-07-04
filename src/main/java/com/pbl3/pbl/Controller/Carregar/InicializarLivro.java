package com.pbl3.pbl.Controller.Carregar;

import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;

import java.io.*;
import java.util.ArrayList;

/**
 * Esta classe e responsavel pela inicializacao e carregamento dos livros a partir de um arquivo.
 * O arquivo contem uma lista de livros que sera lida e carregada no catalogo de livros.
 */
public class InicializarLivro
{
    // Nome do arquivo onde os livros sao armazenados.
    private static final String arquivo_livros = "arquivo_livros";

    /**
     * Este metodo inicializa o processo de carregamento dos livros, verificando se o arquivo existe.
     * Caso o arquivo exista, o metodo {@link #Carregar()} sera chamado para carregar os livros no catalogo.
     */
    public static void inicializar()
    {
        File arquivo = new File(arquivo_livros);

        // Se o arquivo existir, o metodo Carregar e chamado.
        if (arquivo.exists())
        {
            Carregar();
        }
    }

    /**
     * Este metodo carrega os livros do arquivo e os adiciona ao catalogo de livros.
     *
     * O arquivo é lido usando um ObjectInputStream, e a lista de livros é restaurada e inserida no catálogo
     * de livros (CatalogoLivros).
     */
    public static void Carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo_livros)))
        {
            // Le a lista de livros do arquivo
            ArrayList<Livro> livros = (ArrayList<Livro>) ois.readObject();

            // Define os livros carregados no catalogo
            CatalogoLivros.getCatalogo().setLivros(livros);
        }
        catch (ClassNotFoundException | IOException e)
        {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }

    /**
     * Retorna o nome do arquivo onde os livros sao armazenados.
     *
     * @return O nome do arquivo de livros.
     */
    public static String getArquivo_livros() {
        return arquivo_livros;
    }
}
