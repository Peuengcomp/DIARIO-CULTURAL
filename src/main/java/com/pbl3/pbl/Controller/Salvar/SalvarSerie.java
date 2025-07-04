package com.pbl3.pbl.Controller.Salvar;

import com.pbl3.pbl.Controller.Carregar.InicializarSerie;
import com.pbl3.pbl.Model.Entidades.Serie;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Classe responsável por salvar os dados das séries no arquivo.
 * Utiliza o padrão de serialização para persistir os dados das séries no sistema de arquivos.
 */
public class SalvarSerie
{
    /**
     * Método estático responsável por salvar a lista de séries no arquivo.
     * Ele recupera o caminho do arquivo através da classe InicializarSerie e, em seguida,
     * grava a lista de séries do catálogo no arquivo utilizando serialização.
     */
    public static void Salvar()
    {
        ArrayList<Serie> series = CatalogoSeries.getCatalogo().getSeries();
        String arquivo = InicializarSerie.getArquivo_series();

        try(ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(arquivo)))
        {
            oss.writeObject(series);

        } catch (IOException e)
        {
            System.err.println("Erro ao salvar series: " + e.getMessage());
        }
    }
}
