package com.pbl3.pbl.Testes.TestesDeSerializaçao;

import com.pbl3.pbl.Controller.Carregar.*;
import com.pbl3.pbl.Controller.Salvar.*;
import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestesSerializacao
{

    private Filme filme1Original;
    private Filme filme2Original;
    private Livro livroOriginal1;
    private Livro livroOriginal2;
    private Serie serie1Original;
    private Serie serie2Original;


    private Filme filme1Desserializado;
    private Filme filme2Desserializado;
    private ArrayList<Livro> livrosDesserializados;

    @Before
    public void setUp() {
        // Inicializa o repositório de categorias
        RepositorioCategorias.getInstancia();
        Categoria categoriafilme1 = RepositorioCategorias.getCategoria(4);
        Categoria categoriafilme2 = RepositorioCategorias.getCategoria(6);

        // Elenco, direção e roteiro comuns para o exemplo
        ArrayList<String> elencofilme1 = new ArrayList<>();
        elencofilme1.add("Marlon Brando");
        elencofilme1.add("Al Pacino");

        ArrayList<String> direcao1 = new ArrayList<>();
        direcao1.add("Francis Ford Coppola");

        ArrayList<String> roteiro1 = new ArrayList<>();
        roteiro1.add("Francis Ford Coppola");
        roteiro1.add("Mario Puzo");

        ArrayList<String> elencofilme2 = new ArrayList<>();
        elencofilme2.add("Anthony Hopkins");
        elencofilme2.add("Jodie Foster");

        ArrayList<String> direcao2 = new ArrayList<>();
        direcao2.add("Jonathan Demme");

        ArrayList<String> roteiro2 = new ArrayList<>();
        roteiro2.add("Ted Tally");

        // Criação do primeiro filme
        filme1Original = new Filme("O Poderoso Chefão", 1972, categoriafilme1,
                "Filme empolgante sobre a máfia ítalo-americana", 5,
                LocalDate.of(2024, 2, 16), "The Godfather", "Prime", 175,
                elencofilme1, roteiro1, direcao1);

        // Criação do segundo filme
        filme2Original = new Filme("O Silêncio dos Inocentes", 1991, categoriafilme2,
                "Thriller psicológico com Hannibal Lecter", 4,
                LocalDate.of(2024, 3, 20), "The Silence of the Lambs", "Netflix", 118,
                elencofilme2, roteiro2, direcao2);

        // Adiciona ambos os filmes ao catálogo
        CatalogoFilmes catalogo = CatalogoFilmes.getCatalogo();
        catalogo.getFilmes().clear(); // limpa antes para evitar conflitos
        catalogo.getFilmes().add(filme1Original);
        catalogo.getFilmes().add(filme2Original);

        // Salva os filmes no arquivo
        SalvarFilme.Salvar();

        // Limpa o catálogo e recarrega os dados salvos
        catalogo.getFilmes().clear();
        InicializarFilme.inicializar();

        // Recupera os filmes desserializados
        filme1Desserializado = catalogo.getFilmes().get(0);
        filme2Desserializado = catalogo.getFilmes().get(1);


        // LIVROS

        Categoria categoria_livros = RepositorioCategorias.getCategoria(7); // Romance

        // Criação do primeiro livro
        livroOriginal1 = new Livro("Torto Arado", 2019, categoria_livros,
                "Um livro sobre tortos arados", 5, LocalDate.of(2025, 1, 15),
                "Itamar Vieira Júnior", "Leya", "9786580309313", false);

        // Criação do segundo livro
        livroOriginal2 = new Livro("Ensaio sobre a Cegueira", 1995, categoria_livros,
                "Uma poderosa reflexão social", 4, LocalDate.of(2023, 8, 10),
                "José Saramago", "Companhia das Letras", "9788535920427", true);

        // Adiciona os livros ao catálogo
        CatalogoLivros catalogolivros = CatalogoLivros.getCatalogo();
        catalogolivros.getLivros().clear();
        catalogolivros.getLivros().add(livroOriginal1);
        catalogolivros.getLivros().add(livroOriginal2);

        // Salva os livros no arquivo
        SalvarLivro.Salvar();

        // Limpa o catálogo para simular novo carregamento
        catalogolivros.getLivros().clear();

        // Recarrega os dados do arquivo
        InicializarLivro.inicializar();

        // Recupera os livros desserializados
        livrosDesserializados = CatalogoLivros.getCatalogo().getLivros();


        // SERIES

        Categoria categoria_serie1 = RepositorioCategorias.getCategoria(1);
        Categoria categoria_serie2 = RepositorioCategorias.getCategoria(4);

        ArrayList<Temporada> temporadas1 = new ArrayList<>();
        temporadas1.add(new Temporada(1, 2005, 22, "Início da jornada", 5, LocalDate.of(2023, 3, 17)));
        temporadas1.add(new Temporada(2, 2006, 22, "Expansão da história", 4, LocalDate.of(2023, 4, 17)));

        ArrayList<Temporada> temporadas2 = new ArrayList<>();
        temporadas2.add(new Temporada(1, 2010, 10, "Começo explosivo", 3, LocalDate.of(2023, 5, 17)));
        temporadas2.add(new Temporada(2, 2011, 10, "Clímax crescente", 5, LocalDate.of(2023, 6, 17)));

        ArrayList<String> elenco_serie1 = new ArrayList<>();
        elenco_serie1.add("Jensen Ackles");
        elenco_serie1.add("Jared Padalecki");

        ArrayList<String> elenco_serie2 = new ArrayList<>();
        elenco_serie2.add("Bryan Cranston");
        elenco_serie2.add("Aaron Paul");

        serie1Original = new Serie("Sobrenatural", 2005, categoria_serie1,
                "Supernatural", "Prime Video", elenco_serie1, 2020, temporadas1);

        serie2Original = new Serie("Breaking Bad", 2010, categoria_serie2,
                "Breaking Bad", "Netflix", elenco_serie2, 2013, temporadas2);

        CatalogoSeries.getCatalogo().getSeries().clear();
        CatalogoSeries.getCatalogo().Adicionar(serie1Original);
        CatalogoSeries.getCatalogo().Adicionar(serie2Original);

        SalvarSerie.Salvar();
        CatalogoSeries.getCatalogo().getSeries().clear();
        InicializarSerie.inicializar();
    }

    @Test
    public void TesteSerializacaoDeFilmes() {
        // Verifica se os filmes foram desserializados corretamente

        assertEquals(2, CatalogoFilmes.getCatalogo().getFilmes().size());

        assertNotNull(filme1Desserializado);
        assertNotNull(filme2Desserializado);

        // Valida o primeiro filme
        assertEquals(filme1Original.getTitulo(), filme1Desserializado.getTitulo());
        assertEquals(filme1Original.getAno(), filme1Desserializado.getAno());
        assertEquals(filme1Original.getCategoria().getGenero(), filme1Desserializado.getCategoria().getGenero());
        assertEquals(filme1Original.getReview(), filme1Desserializado.getReview());
        assertEquals(filme1Original.getAvaliacao(), filme1Desserializado.getAvaliacao());
        assertEquals(filme1Original.getData(), filme1Desserializado.getData());
        assertEquals(filme1Original.getTitulo_original(), filme1Desserializado.getTitulo_original());
        assertEquals(filme1Original.getOnde_assistir(), filme1Desserializado.getOnde_assistir());
        assertEquals(filme1Original.getDuracao(), filme1Desserializado.getDuracao());
        assertEquals(filme1Original.getElenco(), filme1Desserializado.getElenco());
        assertEquals(filme1Original.getDirecao(), filme1Desserializado.getDirecao());
        assertEquals(filme1Original.getRoteiro(), filme1Desserializado.getRoteiro());

        // Valida o segundo filme
        assertEquals(filme2Original.getTitulo(), filme2Desserializado.getTitulo());
        assertEquals(filme2Original.getAno(), filme2Desserializado.getAno());
        assertEquals(filme2Original.getCategoria().getGenero(), filme2Desserializado.getCategoria().getGenero());
        assertEquals(filme2Original.getReview(), filme2Desserializado.getReview());
        assertEquals(filme2Original.getAvaliacao(), filme2Desserializado.getAvaliacao());
        assertEquals(filme2Original.getData(), filme2Desserializado.getData());
        assertEquals(filme2Original.getTitulo_original(), filme2Desserializado.getTitulo_original());
        assertEquals(filme2Original.getOnde_assistir(), filme2Desserializado.getOnde_assistir());
        assertEquals(filme2Original.getDuracao(), filme2Desserializado.getDuracao());
        assertEquals(filme2Original.getElenco(), filme2Desserializado.getElenco());
        assertEquals(filme2Original.getDirecao(), filme2Desserializado.getDirecao());
        assertEquals(filme2Original.getRoteiro(), filme2Desserializado.getRoteiro());
    }

    @Test
    public void testarSerializacaoDeLivros()
    {
        assertEquals(2, CatalogoLivros.getCatalogo().getLivros().size());

        Livro l1 = livrosDesserializados.get(0);
        Livro l2 = livrosDesserializados.get(1);

        // Verificação do primeiro livro
        assertEquals(livroOriginal1.getTitulo(), l1.getTitulo());
        assertEquals(livroOriginal1.getAutor(), l1.getAutor());
        assertEquals(livroOriginal1.getEditora(), l1.getEditora());
        assertEquals(livroOriginal1.getISBN(), l1.getISBN());
        assertEquals(livroOriginal1.getData(), l1.getData());
        assertEquals(livroOriginal1.getReview(), l1.getReview());
        assertEquals(livroOriginal1.getAvaliacao(), l1.getAvaliacao());
        assertEquals(livroOriginal1.getTem_exemplar(), l1.getTem_exemplar());

        // Verificação do segundo livro
        assertEquals(livroOriginal2.getTitulo(), l2.getTitulo());
        assertEquals(livroOriginal2.getAutor(), l2.getAutor());
        assertEquals(livroOriginal2.getEditora(), l2.getEditora());
        assertEquals(livroOriginal2.getISBN(), l2.getISBN());
        assertEquals(livroOriginal2.getData(), l2.getData());
        assertEquals(livroOriginal2.getReview(), l2.getReview());
        assertEquals(livroOriginal2.getAvaliacao(), l2.getAvaliacao());
        assertEquals(livroOriginal2.getTem_exemplar(), l2.getTem_exemplar());
    }

    @Test
    public void testeSerializacaoDeSeries() {
        ArrayList<Serie> series = CatalogoSeries.getCatalogo().getSeries();
        assertEquals(2, series.size());

        Serie desserializada1 = series.get(0);
        Serie desserializada2 = series.get(1);

        // Verifica título e avaliação
        assertEquals(serie1Original.getTitulo(), desserializada1.getTitulo());
        assertEquals(serie1Original.getAvaliacao(), desserializada1.getAvaliacao(), 0.01);

        assertEquals(serie2Original.getTitulo(), desserializada2.getTitulo());
        assertEquals(serie2Original.getAvaliacao(), desserializada2.getAvaliacao(), 0.01);

        // Verifica temporadas série 1
        ArrayList<Temporada> temp1 = desserializada1.getTemporadas();
        assertEquals(2, temp1.size());
        assertEquals(2005, temp1.get(0).getAno());
        assertEquals("Início da jornada", temp1.get(0).getReview());

        // Verifica temporadas série 2
        ArrayList<Temporada> temp2 = desserializada2.getTemporadas();
        assertEquals(2, temp2.size());
        assertEquals(2011, temp2.get(1).getAno());
        assertEquals(5, temp2.get(1).getAvaliacao());
        assertEquals("Clímax crescente", temp2.get(1).getReview());

        // Verifica elencos
        assertEquals(serie1Original.getElenco(), desserializada1.getElenco());
        assertEquals(serie2Original.getElenco(), desserializada2.getElenco());
    }
}
