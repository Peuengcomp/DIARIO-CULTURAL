package com.pbl3.pbl.Testes.Integracao;

import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestesCatalogos
{
    private Livro livro1;
    private Livro livro2;
    private Filme filme1;
    private Filme filme2;
    private Serie serie1;
    private Serie serie2;

    @Before
    public void InicializarParaTestes()
    {
        // Instâncias para livros

        livro1 = new Livro("Grande Sertão: Veredas", 1956, null,
                null, 0, null,
                null, null, null, true);

        livro2 = new Livro("A Odisseia", -800, null,
                null, 0, null,
                null, null, null, true);


        // Instâncias para filmes

        filme1 = new Filme(
                "O Poderoso Chefão",
                1972,
                null,
                null,
                5,
                null,
                null,
                null,
                175,
                null,
                null,
                null
        );

        filme2 = new Filme(
                "O Senhor dos Anéis: A Sociedade do Anel",
                2001,
                null,
                null,
                5,
                null,
                null,
                null,
                178,
                null,
                null,
                null
        );

        // Instâncias para séries

        ArrayList<Temporada> temporadas1 = new ArrayList<>();

        temporadas1.add(new Temporada(
                1,
                2008,
                7,
                null,
                5,
                null
        ));

        temporadas1.add(new Temporada(
                2,
                2009,
                13,
                null,
                4,
                null
        ));

        serie1 = new Serie(
                "Breaking Bad",
                2008,
                null,
                null,
                null,
                null,
                2013,
                temporadas1
        );

        ArrayList<Temporada> temporadas2 = new ArrayList<>();

        temporadas2.add(new Temporada(
                1,
                1994,
                24,
                null,
                4,
                null
        ));

        temporadas2.add(new Temporada(
                2,
                1995,
                24,
                null,
                4,
                LocalDate.of(2021, 12, 12)
        ));

        serie2 = new Serie(
                "Friends",
                1994,
                null,
                null,
                null,
                null,
                2004,
                temporadas2
        );


    }
    @Test
    public void TestarCriarCatalogos()
    {
        CatalogoLivros catalogolivros = CatalogoLivros.getCatalogo();
        assertNotNull(catalogolivros);

        CatalogoFilmes catalogofilmes = CatalogoFilmes.getCatalogo();
        assertNotNull(catalogofilmes);

        CatalogoSeries catalogoseries = CatalogoSeries.getCatalogo();
        assertNotNull(catalogoseries);
    }

    @Test
    public void TesteAdicionarNoCatalogoLivros()
    {
        CatalogoLivros catalogolivros = CatalogoLivros.getCatalogo();
        assertNotNull(catalogolivros);

        catalogolivros.Adicionar(livro1);
        catalogolivros.Adicionar(livro2);

        assertEquals(livro1, catalogolivros.getLivros().get(0));
        assertEquals(livro2, catalogolivros.getLivros().get(1));

        assertEquals(2, catalogolivros.getLivros().size());
    }

    @Test
    public void TesteAdicionarNoCatalogoFilmes()
    {
        CatalogoFilmes catalogofilmes = CatalogoFilmes.getCatalogo();
        assertNotNull(catalogofilmes);

        catalogofilmes.Adicionar(filme1);
        catalogofilmes.Adicionar(filme2);

        assertEquals(filme1, catalogofilmes.getFilmes().get(0));
        assertEquals(filme2, catalogofilmes.getFilmes().get(1));

        assertEquals(2, catalogofilmes.getFilmes().size());
    }

    @Test
    public void TesteAdicionarNoCatalogoSeries()
    {
        CatalogoSeries catalogoseries = CatalogoSeries.getCatalogo();
        assertNotNull(catalogoseries);

        catalogoseries.Adicionar(serie1);
        catalogoseries.Adicionar(serie2);

        assertEquals(serie1, catalogoseries.getSeries().get(0));
        assertEquals(serie2, catalogoseries.getSeries().get(1));

        assertEquals(2, catalogoseries.getSeries().size());
    }
}
