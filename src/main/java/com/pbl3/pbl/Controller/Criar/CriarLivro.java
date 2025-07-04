package com.pbl3.pbl.Controller.Criar;

import com.pbl3.pbl.Controller.Salvar.Salvar;
import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;

import java.time.LocalDate;

/**
 * Esta classe tem por finalidade criar um livro e adicioná-lo ao catálogo.
 *
 * Essa abordagem foi utilizada em consideração ao padrão MVC, em que a função de criação e comunicação com repositórios
 * é delegada ao Controller.
 */
public class CriarLivro
{
    /**
     * Metodo responsável pela criação de um livro e adição ao catálogo de livros.
     *
     * @param titulo O título do livro.
     * @param ano O ano de publicação do livro.
     * @param categoria A categoria do livro, representada por uma instância de {@link Categoria}.
     * @param autor O nome do autor do livro.
     * @param editora O nome da editora do livro.
     * @param ISBN O ISBN do livro.
     * @param tem_exemplar Um valor booleano que indica se o livro tem exemplar disponível.
     * @param review A avaliação ou resenha do livro.
     * @param avaliacao A avaliação do livro, geralmente em uma escala de 1 a 5.
     * @param data A data de lançamento do livro.
     */
    public static void criarLivro(String titulo, int ano, Categoria categoria,
                                  String autor, String editora,
                                  String ISBN, boolean tem_exemplar, String review, int avaliacao, LocalDate data)
    {
        Livro livro = new Livro(titulo, ano, categoria, review, avaliacao, data, autor, editora, ISBN, tem_exemplar);
        CatalogoLivros.getCatalogo().Adicionar(livro);
        Salvar.SalvarArquivos();
    }
}