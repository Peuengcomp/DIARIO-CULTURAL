package com.pbl3.pbl.Model.Entidades;

// Esta classe tem por finalidade representar uma categoria

// Os get foram implementadas para atender as necessidades do Repositorio de Categorias pré-definidas

import java.io.Serializable;

/**
 * Representa uma categoria com um id e uma descrição (gênero).
 *
 * <p>Utilizada no repositório de categorias pré-definidas.</p>
 *
 * <p>Implementa Serializable para permitir que objetos dessa classe
 * possam ser gravados em arquivos ou transmitidos.</p>
 */
public class Categoria implements Serializable
{
    /** Identificador único da categoria. */
    private int id;

    /** Nome ou gênero da categoria. */
    private String categoria;

    /**
     * Construtor que inicializa a categoria com o id e o nome fornecidos.
     *
     * @param id o identificador da categoria
     * @param categoria o nome ou gênero da categoria
     */
    public Categoria(int id, String categoria)
    {
        this.id = id;
        this.categoria = categoria;
    }

    /**
     * Retorna a representação textual da categoria no formato "id - categoria".
     *
     * @return string formatada com id e nome da categoria
     */
    public String toString()
    {
        return id + " - " + categoria;
    }

    /**
     * Retorna o identificador da categoria.
     *
     * @return o id da categoria
     */
    public int getId()
    {
        return id;
    }

    /**
     * Retorna o nome ou gênero da categoria.
     *
     * @return a categoria (nome ou gênero)
     */
    public String getGenero()
    {
        return categoria;
    }
}