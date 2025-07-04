package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Filtrar.iFiltrar;
import com.pbl3.pbl.Model.Entidades.*;
import com.pbl3.pbl.Model.Repositorios.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Classe responsável por exibir a tela de filtragem de mídias.
 * Permite ao usuário filtrar livros, filmes ou séries por ano de lançamento ou por categoria.
 */
public class TelaFiltrar
{
    /**
     * Cena anterior, usada para retornar à tela anterior ao clicar em "Voltar".
     */
    private Scene anterior;

    /**
     * Construtor da classe TelaFiltrar.
     *
     * @param anterior cena anterior à tela de filtragem
     */
    public TelaFiltrar(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a interface gráfica para filtragem de mídias.
     * Permite selecionar o tipo de mídia (Livro, Filme ou Série) e aplicar filtros por ano ou categoria.
     *
     * @param stage o palco onde a cena será apresentada
     */
    public void mostrar(Stage stage)
    {
        stage.setTitle("Filtrar Mídias");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        ComboBox<String> tipoMidia = new ComboBox<>();
        tipoMidia.getItems().addAll("Livros", "Filmes", "Séries");

        ToggleGroup filtro = new ToggleGroup();
        RadioButton rbAno = new RadioButton("Filtrar por ano");
        RadioButton rbCategoria = new RadioButton("Filtrar por categoria");
        rbAno.setToggleGroup(filtro);
        rbCategoria.setToggleGroup(filtro);
        rbAno.setSelected(true);

        TextField campoAno = new TextField();
        campoAno.setPromptText("Digite o ano");

        ComboBox<Categoria> comboCategoria = new ComboBox<>();
        comboCategoria.getItems().addAll(RepositorioCategorias.getCategorias());
        comboCategoria.setPromptText("Selecione a categoria");

        filtro.selectedToggleProperty().addListener((obs, oldToggle, newToggle) ->
        {
            if (rbAno.isSelected())
            {
                campoAno.setVisible(true);
                comboCategoria.setVisible(false);
            }
            else
            {
                campoAno.setVisible(false);
                comboCategoria.setVisible(true);
            }
        });

        Button btnFiltrar = new Button("Filtrar");
        TextArea taResultado = new TextArea();
        taResultado.setEditable(false);
        taResultado.setPrefHeight(250);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> stage.setScene(anterior));

        btnFiltrar.setOnAction(e ->
        {
            String tipo = tipoMidia.getValue();
            boolean porAno = rbAno.isSelected();

            taResultado.clear();

            if (tipo == null)
            {
                Validador.mostrarAlerta("Selecione um tipo de mídia.");
                return;
            }

            if (porAno && campoAno.getText().trim().isEmpty())
            {
                Validador.mostrarAlerta("Digite o ano.");
                return;
            }

            if (!porAno && comboCategoria.getValue() == null)
            {
                Validador.mostrarAlerta("Selecione uma categoria.");
                return;
            }

            int valor = -1;

            if (porAno)
            {
                try
                {
                    valor = Validador.ValidarAno(campoAno.getText().trim());
                }
                catch (NumberFormatException ex)
                {
                    Validador.mostrarAlerta("Ano inválido. Digite um número.");
                    return;
                }
            }
            else
            {
                Categoria cat = comboCategoria.getValue();
                if (cat != null)
                {
                    valor = cat.getId();
                }
            }

            if (tipo.equals("Livros"))
            {
                ArrayList<Livro> lista = CatalogoLivros.getCatalogo().getLivros();
                ArrayList<Livro> resultado = new ArrayList<>();
                if (porAno)
                {
                    resultado = iFiltrar.FiltrarPorAno(lista, valor);
                }
                else
                {
                    resultado = iFiltrar.FiltrarPorCategoria(lista, valor);
                }
                exibirResultados(resultado, taResultado);
            }
            else if (tipo.equals("Filmes"))
            {
                ArrayList<Filme> lista = CatalogoFilmes.getCatalogo().getFilmes();
                ArrayList<Filme> resultado = new ArrayList<>();
                if (porAno)
                {
                    resultado = iFiltrar.FiltrarPorAno(lista, valor);
                }
                else
                {
                    resultado = iFiltrar.FiltrarPorCategoria(lista, valor);
                }
                exibirResultados(resultado, taResultado);
            }
            else if (tipo.equals("Séries"))
            {
                ArrayList<Serie> lista = CatalogoSeries.getCatalogo().getSeries();
                ArrayList<Serie> resultado = new ArrayList<>();
                if (porAno)
                {
                    resultado = iFiltrar.FiltrarPorAno(lista, valor);
                }
                else
                {
                    resultado = iFiltrar.FiltrarPorCategoria(lista, valor);
                }
                exibirResultados(resultado, taResultado);
            }
        });

        comboCategoria.setVisible(false);

        layout.getChildren().addAll
                (
                        new Label("Tipo de Mídia:"), tipoMidia,
                        new Label("Filtro:"), rbAno, rbCategoria,
                        campoAno, comboCategoria,
                        btnFiltrar, taResultado, btnVoltar
                );

        Scene cena = new Scene(layout, 600, 450);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }

    /**
     * Exibe os resultados da filtragem no componente de texto fornecido.
     *
     * @param lista lista de mídias filtradas
     * @param ta componente {@link TextArea} onde os resultados serão mostrados
     * @param <T> tipo genérico que estende {@link Midia}
     */
    private <T extends Midia> void exibirResultados(ArrayList<T> lista, TextArea ta) {
        if (lista.isEmpty())
        {
            Validador.mostrarInfo("Nenhum resultado foi encontrado.");
        }
        else
        {
            for (Midia m : lista)
            {
                ta.appendText(m.toString() + "\n\n");
                ta.appendText("_____________________________________\n\n");
            }
        }
    }
}
