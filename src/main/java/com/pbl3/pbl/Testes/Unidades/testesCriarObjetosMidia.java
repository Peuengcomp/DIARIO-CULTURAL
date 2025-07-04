package com.pbl3.pbl.Testes.Unidades;

import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class testesCriarObjetosMidia
{

    // Defina-se antes as variáveis que serão utilizadas nos testes.
    private LocalDate data_livro;
    private LocalDate data_filme;
    private LocalDate data_serie;
    private Categoria categoria_livro;
    private Categoria categoria_filme;
    private Categoria categoria_serie;

    @Before
    public void InicializacaoParaTestes()
    {
        RepositorioCategorias.getInstancia();
        assertNotNull(RepositorioCategorias.getInstancia());

        data_livro = LocalDate.of(2025, 1, 15);
        data_filme = LocalDate.of(2024, 2, 16);
        data_serie = LocalDate.of(2023, 3, 17);

        assertNotNull(data_livro);
        assertNotNull(data_filme);
        assertNotNull(data_serie);

        categoria_livro = RepositorioCategorias.getCategoria(7);
        categoria_filme = RepositorioCategorias.getCategoria(4);
        categoria_serie = RepositorioCategorias.getCategoria(1);

        assertNotNull(categoria_livro);
        assertNotNull(categoria_filme);
        assertNotNull(categoria_serie);

        assertEquals("Romance", categoria_livro.getGenero());
        assertEquals("Drama", categoria_filme.getGenero());
        assertEquals("Ação", categoria_serie.getGenero());
    }

    @Test
    public void CriarObjetoLivro()
    {
        // Este teste cria um objeto Livro e verifica se ele foi criado corretamente.

        Livro livro = new Livro("Torto Arado", 2019, categoria_livro, "Um livro sobre tortos arados",
                5, data_livro, "Itamar Vieira Júnior", "Leya", "9786580309313", false);

        assertNotNull(livro);

        // Os blocos de assert abaixo verificam os métodos e atributos e os get

        assertEquals("Torto Arado", livro.getTitulo());
        assertEquals(2019, livro.getAno());
        assertEquals("Romance", livro.getCategoria().getGenero());
        assertEquals("Um livro sobre tortos arados", livro.getReview());
        assertEquals(5, livro.getAvaliacao());
        assertEquals(livro.getData(), data_livro);
        assertEquals("Itamar Vieira Júnior", livro.getAutor());
        assertEquals("Leya", livro.getEditora());
        assertEquals("9786580309313", livro.getISBN());
        assertEquals(false, livro.getTem_exemplar());
        assertEquals("Não", livro.exemplar());
    }

    @Test
    public void CriarObjetoFilme()
    {
        // este teste cria um objeto Filme e verifica se ele foi criado corretamente.

        ArrayList<String> elenco = new ArrayList<String>();
        elenco.add("Marlon Brando");
        elenco.add("Al Pacino");

        ArrayList<String> direcao = new ArrayList<>();
        direcao.add("Francis Ford Coppola");

        ArrayList<String> roteiro = new ArrayList<>();
        roteiro.add("Francis Ford Coppola");
        roteiro.add("Mario Puzo");

        Filme filme = new Filme("O Poderoso Chefão", 1972, categoria_filme,
                "Filme empolgante sobre a máfia ítalo-americana", 4, data_filme, "The Godfather",
                "Prime", 175, elenco, roteiro, direcao);

        assertNotNull(filme);

        assertEquals("O Poderoso Chefão", filme.getTitulo());
        assertEquals(1972, filme.getAno());
        assertEquals("Drama", filme.getCategoria().getGenero());
        assertEquals("Filme empolgante sobre a máfia ítalo-americana", filme.getReview());
        assertEquals(4, filme.getAvaliacao());
        assertEquals(data_filme, filme.getData());
        assertEquals("The Godfather", filme.getTitulo_original());
        assertEquals("Prime", filme.getOnde_assistir());
        assertEquals(175, filme.getDuracao());

        for (String nome : filme.getElenco())
        {
            assertTrue(elenco.contains(nome));
        }

        for (String nome : filme.getDirecao())
        {
            assertTrue(direcao.contains(nome));
        }

        for (String nome : filme.getRoteiro())
        {
            assertTrue(roteiro.contains(nome));
        }
    }

    @Test
    public void CriarObjetoTemporada()
    {
        // Este teste cria um objeto Temporada e verifica se ele foi criado corretamente.

        Temporada temporada = new Temporada(1, 2022, 22,
                "Primeira Temporada", 5, data_serie);

        assertNotNull(temporada);

        assertEquals(1, temporada.getId_temporada());
        assertEquals(2022, temporada.getAno());
        assertEquals(22, temporada.getQtd_epsidios());
        assertEquals("Primeira Temporada", temporada.getReview());
        assertEquals(5, temporada.getAvaliacao());
        assertEquals(data_serie, temporada.getData());
    }

    @Test
    public void CriarObjetoSerie()
    {
        // Este teste cria um objeto Serie e verifica se ele foi criado corretamente.

        // Como a avaliação da série é dada em função das temporadas, duas temporadas são criadas.

        Temporada temporada1 = new Temporada(1, 2005, 22,
                "Primeira Temporada", 5, data_serie);

        Temporada temporada2 = new Temporada(2, 2006, 22,
                "Segunda Temporada", 4, data_serie);

        ArrayList<Temporada> temporadas = new ArrayList<>();
        temporadas.add(temporada1);
        temporadas.add(temporada2);

        ArrayList<String> elenco = new ArrayList<>();
        elenco.add("Jared Padalecki");
        elenco.add("Jensen Ackles");


        Serie serie = new Serie("Sobrenatural", 2005, categoria_serie,
                "Supernatural", "Prime", elenco, 2020, temporadas);

        assertNotNull(serie);

        assertEquals("Sobrenatural", serie.getTitulo());
        assertEquals(2005, serie.getAno());
        assertEquals("Ação", categoria_serie.getGenero());
        assertEquals("Supernatural", serie.getTitulo_original());
        assertEquals("Prime", serie.getOnde_assistir());

        for (String nome : serie.getElenco())
        {
            assertTrue(elenco.contains(nome));
        }

        assertEquals(2020, serie.getAno_de_encerramento());

        for (Temporada temporada : temporadas)
        {
            assertTrue(serie.getTemporadas().contains(temporada));
        }

        assertEquals(4.5, serie.getAvaliacao(), 0.001);
    }
}
