package com.pbl3.pbl.Controller.Salvar;

import com.pbl3.pbl.Controller.Carregar.InicializarFilme;
import com.pbl3.pbl.Model.Entidades.Filme;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe responsável por salvar os dados dos filmes no arquivo.
 * Utiliza o padrão de serialização para persistir os dados dos filmes no sistema de arquivos.
 */
public class SalvarFilme implements Serializable
{
    /**
     * Metodo estático responsável por salvar a lista de filmes no arquivo.
     * Ele recupera o caminho do arquivo através da classe InicializarFilme e, em seguida,
     * grava a lista de filmes do catálogo no arquivo utilizando serialização.
     */
    public static void Salvar()
    {
        String arquivo = InicializarFilme.getArquivo_filmes();
        ArrayList<Filme> filmes = CatalogoFilmes.getCatalogo().getFilmes();

        try(ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(arquivo)))
        {
            oss.writeObject(filmes);

        } catch (IOException e)
        {
            System.err.println("Erro ao salvar filmes: " + e.getMessage());
        }
    }
}