package com.pbl3.pbl.Model.Repositorios;

/**
 * Interface que define o contrato para um catálogo de objetos.
 *
 * <p>Esta interface é genérica e pode ser implementada por classes que desejam
 * gerenciar um conjunto de objetos de um tipo específico. Ela fornece métodos
 * para listar os objetos de forma ordenada e para adicionar novos objetos ao catálogo.</p>
 *
 * @param <T> O tipo dos objetos armazenados no catálogo.
 */
public interface iCatalogo<T>
{
    /**
     * Lista os objetos armazenados no catálogo e os ordena conforme a avaliação.
     *
     * @param ordem Se verdadeiro, ordena os objetos em ordem crescente de avaliação,
     *              caso contrário, ordena em ordem decrescente de avaliação.
     * @return <code>true</code> se a operação foi bem-sucedida, <code>false</code> se o catálogo estiver vazio.
     */
    boolean ListarOrdenar(boolean ordem);

    /**
     * Adiciona um objeto ao catálogo.
     *
     * @param objeto O objeto a ser adicionado ao catálogo.
     */
    void Adicionar(T objeto);
}