package com.pbl3.pbl.Model.Repositorios;

import com.pbl3.pbl.Model.Entidades.Categoria;

import java.util.ArrayList;

/**
 * Repositório de categorias usado para armazenar as categorias pré-definidas.
 * Essa classe implementa o padrão Singleton para garantir que haja uma única instância
 * do repositório de categorias na aplicação.
 */
public class RepositorioCategorias
{
    private static RepositorioCategorias instancia;
    private static ArrayList<Categoria> categorias;

    /**
     * Construtor privado para inicializar a lista de categorias predefinidas.
     */
    private RepositorioCategorias()
    {
        categorias = new ArrayList<Categoria>();
        categorias.add(new Categoria(1, "Ação"));
        categorias.add(new Categoria(2, "Aventura"));
        categorias.add(new Categoria(3, "Comédia"));
        categorias.add(new Categoria(4, "Drama"));
        categorias.add(new Categoria(5, "Ficção Científica"));
        categorias.add(new Categoria(6, "Terror"));
        categorias.add(new Categoria(7, "Romance"));
        categorias.add(new Categoria(8, "Suspense"));
        categorias.add(new Categoria(9, "Mistério"));
        categorias.add(new Categoria(10, "Fantasia"));
        categorias.add(new Categoria(11, "Animação"));
        categorias.add(new Categoria(12, "Documentário"));
        categorias.add(new Categoria(13, "Musical"));
        categorias.add(new Categoria(14, "Guerra"));
        categorias.add(new Categoria(15, "Biografia"));
        categorias.add(new Categoria(16, "Policial"));
        categorias.add(new Categoria(17, "Épico"));
        categorias.add(new Categoria(18, "Investigação"));
    }

    /**
     * Metodo responsável por garantir que exista apenas uma instância do repositório de categorias.
     *
     * @return A instância única do RepositorioCategorias.
     */
    public static RepositorioCategorias getInstancia()
    {
        if (instancia == null)
            instancia = new RepositorioCategorias();
        return instancia;
    }

    /**
     * Metodo que retorna a lista completa de categorias.
     *
     * @return Lista de categorias predefinidas.
     */
    public static ArrayList<Categoria> getCategorias()
    {
        return categorias;
    }

    /**
     * Metodo que retorna uma categoria com base no ID fornecido.
     *
     * @param id O ID da categoria.
     * @return A categoria correspondente ao ID ou null caso o ID seja inválido.
     */
    public static Categoria getCategoria(int id)
    {
        if (id < 1 || id > categorias.size())
            return null;
        return categorias.get(id-1);
    }
}