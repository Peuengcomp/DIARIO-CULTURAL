package com.pbl3.pbl.Testes.Integracao;

import com.pbl3.pbl.Controller.Buscar.BuscarFilme;
import com.pbl3.pbl.Controller.Buscar.BuscarLivro;
import com.pbl3.pbl.Controller.Buscar.iBuscarMidia;
import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;
import com.pbl3.pbl.Model.Entidades.Filme;
import com.pbl3.pbl.Model.Entidades.Livro;
import com.pbl3.pbl.Model.Entidades.Serie;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class TestesBuscar
{
    private Livro livro1;
    private Livro livro2;
    private Filme filme1;
    private Filme filme2;
    private Serie serie1;
    private Serie serie2;
    private CatalogoLivros catalogoLivros;
    private CatalogoFilmes catalogoFilmes;
    private CatalogoSeries catalogoSeries;

    @Before
    public void InicializarParaTestes()
    {
        // instâncias dos catálogos

        catalogoLivros = CatalogoLivros.getCatalogo();
        catalogoFilmes = CatalogoFilmes.getCatalogo();
        catalogoSeries = CatalogoSeries.getCatalogo();

        // Instâncias para livros

        Categoria categoria_livro1 = new Categoria(7, "Romance");
        Categoria categoria_livro2 = new Categoria(17, "Épico");

        livro1 = new Livro("Grande Sertão: Veredas", 1956, categoria_livro1,
                "Uma obra-prima da literatura brasileira, rica em linguagem e profundidade filosófica.", 5, LocalDate.of(2023, 3, 12),
                "João Guimarães Rosa", "Editora Nova Fronteira", "978-85-209-2162-6", true);

        livro2 = new Livro("A Odisseia", -800, categoria_livro2,
                "Uma das maiores epopeias da antiguidade, repleta de aventuras e simbolismo.", 5, LocalDate.of(2022, 9, 5),
                "Homero", "Editora 34", "978-85-7326-567-9", true);


        // Instâncias para filmes

        Categoria categoria_filme1 = new Categoria(4, "Drama");
        Categoria categoria_filme2 = new Categoria(10, "Fantasia");

        ArrayList<String> elenco1 = new ArrayList<>();
        elenco1.add("Marlon Brando");
        elenco1.add("Al Pacino");
        elenco1.add("James Caan");

        ArrayList<String> roteiro1 = new ArrayList<>();
        roteiro1.add("Mario Puzo");
        roteiro1.add("Francis Ford Coppola");

        ArrayList<String> direcao1 = new ArrayList<>();
        direcao1.add("Francis Ford Coppola");

        filme1 = new Filme(
                "O Poderoso Chefão",
                1972,
                categoria_filme1,
                "Um clássico do cinema, com atuações memoráveis e direção impecável.",
                5,
                LocalDate.of(2022, 10, 1),
                "The Godfather",
                "Amazon Prime Video",
                175,
                elenco1,
                roteiro1,
                direcao1
        );

        ArrayList<String> elenco2 = new ArrayList<>();
        elenco2.add("Elijah Wood");
        elenco2.add("Ian McKellen");
        elenco2.add("Viggo Mortensen");

        ArrayList<String> roteiro2 = new ArrayList<>();
        roteiro2.add("Fran Walsh");
        roteiro2.add("Philippa Boyens");
        roteiro2.add("Peter Jackson");

        ArrayList<String> direcao2 = new ArrayList<>();
        direcao2.add("Peter Jackson");

        filme2 = new Filme(
                "O Senhor dos Anéis: A Sociedade do Anel",
                2001,
                categoria_filme2,
                "Início de uma jornada épica com efeitos visuais marcantes e narrativa envolvente.",
                4,
                LocalDate.of(2023, 1, 15),
                "The Lord of the Rings: The Fellowship of the Ring",
                "HBO Max",
                178,
                elenco2,
                roteiro2,
                direcao2
        );

        // Instâncias para séries

        Categoria categoria_serie1 = new Categoria(4, "Drama");
        Categoria categoria_serie2 = new Categoria(3, "Comédia");

        ArrayList<String> elencoserie1 = new ArrayList<>();
        elencoserie1.add("Bryan Cranston");
        elencoserie1.add("Aaron Paul");
        elencoserie1.add("Anna Gunn");

        ArrayList<Temporada> temporadas1 = new ArrayList<>();

        temporadas1.add(new Temporada(
                1,
                2008,
                7,
                "Começo promissor e envolvente da transformação de Walter White.",
                5,
                LocalDate.of(2022, 3, 15)
        ));

        temporadas1.add(new Temporada(
                2,
                2009,
                13,
                "A trama se aprofunda, personagens crescem e conflitos aumentam.",
                5,
                LocalDate.of(2022, 4, 10)
        ));

        serie1 = new Serie(
                "Breaking Bad",
                2008,
                categoria_serie1,
                "Breaking Bad",
                "Netflix",
                elencoserie1,
                2013,
                temporadas1
        );


        ArrayList<String> elencoserie2 = new ArrayList<>();
        elencoserie2.add("Jennifer Aniston");
        elencoserie2.add("Courteney Cox");
        elencoserie2.add("Lisa Kudrow");
        elencoserie2.add("Matt LeBlanc");
        elencoserie2.add("Matthew Perry");
        elencoserie2.add("David Schwimmer");

        ArrayList<Temporada> temporadas2 = new ArrayList<>();

        temporadas2.add(new Temporada(
                1,
                1994,
                24,
                "Um clássico do humor leve, com início carismático e divertido.",
                4,
                LocalDate.of(2021, 11, 5)
        ));

        temporadas2.add(new Temporada(
                2,
                1995,
                24,
                "Continua no mesmo ritmo, aprofundando relações entre os personagens.",
                4,
                LocalDate.of(2021, 12, 12)
        ));

        serie2 = new Serie(
                "Friends",
                1994,
                categoria_serie2,
                "Friends",
                "HBO Max",
                elencoserie2,
                2004,
                temporadas2
        );

        // Adicionando

        catalogoLivros.Adicionar(livro1);
        catalogoLivros.Adicionar(livro2);
        catalogoFilmes.Adicionar(filme1);
        catalogoFilmes.Adicionar(filme2);
        catalogoSeries.Adicionar(serie1);
        catalogoSeries.Adicionar(serie2);

    }

    @Test
    public void BuscarLivro()
    {
        // Busca por título
        Livro livro = iBuscarMidia.buscarTitulo("Grande Sertão: Veredas", catalogoLivros.getLivros());
        assertNotNull(livro);
        assertEquals(livro1.getTitulo(), livro.getTitulo());

        // Buscar por Gênero
        ArrayList<Livro> livros1 = iBuscarMidia.buscarCategoria(7, catalogoLivros.getLivros());
        assertNotNull(livros1);

        for (Livro l : livros1)
        {
            assertEquals(7, l.getCategoria().getId());
        }

        // Buscar por ano

        ArrayList<Livro> livros2 = iBuscarMidia.buscarAno(-800, catalogoLivros.getLivros());
        assertNotNull(livros2);

        for (Livro l : livros2)
        {
            assertEquals(-800, l.getAno());
        }

        // Buscar por autor

        ArrayList<Livro> livros3 = BuscarLivro.buscarAutor("Homero");

        assertNotNull(livros3);

        for (Livro l : livros3)
        {
            assertEquals("Homero", l.getAutor());
        }

        // Buscar por ISBN
        Livro livroISBN = BuscarLivro.buscarISBN("978-85-209-2162-6");
        assertNotNull(livroISBN);
        assertEquals(livro1.getISBN(), livroISBN.getISBN());
    }

    @Test
    public void BuscarFilme()
    {
        // Buscar por título
        Filme filme = iBuscarMidia.buscarTitulo("O Poderoso Chefão", catalogoFilmes.getFilmes());
        assertNotNull(filme);
        assertEquals(filme1.getTitulo(), filme.getTitulo());

        // Buscar por Gênero
        ArrayList<Filme> filmes1 = iBuscarMidia.buscarCategoria(4, catalogoFilmes.getFilmes());
        assertNotNull(filmes1);

        for (Filme f : filmes1)
        {
            assertEquals(4, f.getCategoria().getId());
        }

        // Buscar por ano
        ArrayList<Filme> filmes2 = iBuscarMidia.buscarAno(2001, catalogoFilmes.getFilmes());
        assertNotNull(filmes2);

        for (Filme f : filmes2)
        {
            assertEquals(2001, f.getAno());
        }

        // Buscar por diretor
        ArrayList<Filme> filmes3 = BuscarFilme.buscarDiretor("Francis Ford Coppola", catalogoFilmes.getFilmes());
        assertNotNull(filmes3);

        for (Filme f : filmes3)
        {
            ArrayList<String> diretores = f.getDirecao();
            assertTrue(diretores.contains("Francis Ford Coppola"));
        }

        // Buscar por elenco
        ArrayList<Filme> filmes4 = iBuscarMidia.BuscarNomes("Ian McKellen", catalogoFilmes.getFilmes());
        assertNotNull(filmes4);

        for (Filme f : filmes4)
        {
            ArrayList<String> elenco = f.getElenco();
            assertTrue(elenco.contains("Ian McKellen"));
        }
    }
    @Test
    public void BuscarSerie()
    {
        // Buscar por título

        Serie serie = iBuscarMidia.buscarTitulo("Friends", catalogoSeries.getSeries());
        assertNotNull(serie);
        assertEquals(serie2.getTitulo(), serie.getTitulo());

        // Buscar por Categoria

        ArrayList<Serie> series1 = iBuscarMidia.buscarCategoria(3, catalogoSeries.getSeries());
        assertNotNull(series1);

        for (Serie s : series1)
        {
            assertEquals(3, s.getCategoria().getId());
        }

        // Buscar por ano

        ArrayList<Serie> series2 = iBuscarMidia.buscarAno(1994, catalogoSeries.getSeries());
        assertNotNull(series2);

        for (Serie s : series2)
        {
            assertEquals(1994, s.getAno());
        }

        // Buscar por elenco

        ArrayList<Serie> series3 = iBuscarMidia.BuscarNomes("Lisa Kudrow", catalogoSeries.getSeries());
        assertNotNull(series3);

        for (Serie s : series3)
        {
            ArrayList<String> elenco = s.getElenco();
            assertTrue(elenco.contains("Lisa Kudrow"));
        }
    }
}
