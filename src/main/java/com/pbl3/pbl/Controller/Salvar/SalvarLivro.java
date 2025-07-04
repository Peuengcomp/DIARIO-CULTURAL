package com.pbl3.pbl.Controller.Salvar;

import com.pbl3.pbl.Controller.Carregar.InicializarLivro;
import com.pbl3.pbl.Model.Entidades.Livro;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe responsável por salvar os dados dos livros no arquivo.
 * Utiliza o padrão de serialização para persistir os dados dos livros no sistema de arquivos.
 */
public class SalvarLivro implements Serializable
{
    /**
     * Metodo estático responsável por salvar a lista de livros no arquivo.
     * Ele recupera o caminho do arquivo através da classe InicializarLivro e, em seguida,
     * grava a lista de livros do catálogo no arquivo utilizando serialização.
     */
    public static void Salvar()
    {
        ArrayList<Livro> livros = CatalogoLivros.getCatalogo().getLivros();
        String arquivo = InicializarLivro.getArquivo_livros();

        try(ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(arquivo)))
        {
            oss.writeObject(livros);
        }
        catch (IOException e)
        {
            System.err.println("Erro ao salvar livros: " + e.getMessage());
        }
    }
}
