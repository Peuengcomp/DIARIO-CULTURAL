package com.pbl3.pbl.Controller.Buscar;

import com.pbl3.pbl.Model.Entidades.*;

import java.util.ArrayList;

/**
 * Interface para a realização de buscas em listas de objetos de tipo genérico T que herda de Midia.
 * A interface permite a busca por título, ano, categoria e elenco (para objetos de tipo MidiaAV).
 * Os métodos utilizam loops para iterar sobre as listas e retornar os resultados das buscas.
 */
public interface iBuscarMidia
{
    /**
     * Busca um objeto de tipo T, que herda de Midia, pelo título.
     * @param titulo O título do objeto que está sendo buscado.
     * @param lista A lista de objetos em que a busca será realizada.
     * @param <T> O tipo do objeto que herda de Midia.
     * @return O objeto de tipo T que corresponde ao título, ou null se não encontrado.
     */
    static <T extends Midia> T buscarTitulo(String titulo, ArrayList<T> lista)
    {
        for (T midia : lista)
        {
            if (midia.getTitulo().equals(titulo))
                return midia;
        }
        return null;
    }

    /**
     * Busca objetos de tipo T, que herda de Midia, pelo ano.
     * @param ano O ano pelo qual os objetos estão sendo buscados.
     * @param lista A lista de objetos em que a busca será realizada.
     * @param <T> O tipo do objeto que herda de Midia.
     * @return Uma lista de objetos de tipo T que correspondem ao ano, ou null se não encontrados.
     */
    static <T extends Midia> ArrayList<T> buscarAno(int ano, ArrayList<T> lista)
    {
        ArrayList<T> resultado = new ArrayList<>();
        for (T midia : lista)
        {
            if (midia.getAno() == ano)
                resultado.add(midia);
        }
        if (resultado.isEmpty())
            return null;
        return resultado;
    }

    /**
     * Busca objetos de tipo T, que herda de Midia, pela categoria.
     * @param id O ID da categoria pelo qual os objetos estão sendo buscados.
     * @param lista A lista de objetos em que a busca será realizada.
     * @param <T> O tipo do objeto que herda de Midia.
     * @return Uma lista de objetos de tipo T que correspondem à categoria, ou null se não encontrados.
     */
    static <T extends Midia> ArrayList<T> buscarCategoria(int id, ArrayList<T> lista)
    {
        ArrayList<T> resultado = new ArrayList<>();
        for (T midia : lista)
        {
            if (midia.getCategoria().getId() == id)
                resultado.add(midia);
        }
        if (resultado.isEmpty())
            return null;
        return resultado;
    }

    /**
     * Busca objetos de tipo T, que herda de MidiaAV, pelo nome no elenco.
     * @param nome O nome do membro do elenco que está sendo buscado.
     * @param lista A lista de objetos de tipo MidiaAV em que a busca será realizada.
     * @param <T> O tipo do objeto que herda de MidiaAV.
     * @return Uma lista de objetos de tipo T que possuem o nome no elenco, ou null se não encontrados.
     */
    static <T extends MidiaAV> ArrayList<T> BuscarNomes(String nome, ArrayList<T> lista)
    {
        ArrayList<T> resultado = new ArrayList<>();
        for (T midiaAV : lista)
        {
            for (String nomes : midiaAV.getElenco())
            {
                if (nome.equals(nomes))
                    resultado.add(midiaAV);
            }
        }
        if (resultado.isEmpty())
            return null;
        return resultado;
    }
}