package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Buscar.iBuscarMidia;
import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Entidades.Serie;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Classe responsável pela interface de busca de séries.
 * Permite realizar buscas por título, categoria, ano ou ator.
 */
public class TelaBuscarSerie
{
    /**
     * Cena anterior para navegação reversa.
     */
    private Scene anterior;

    /**
     * Construtor da tela de busca de séries.
     *
     * @param anterior a cena anterior à qual se pode retornar.
     */
    public TelaBuscarSerie(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a tela de busca de séries no palco fornecido.
     *
     * @param stage palco principal da aplicação.
     */
    public void mostrar(Stage stage)
    {
        stage.setTitle("Buscar Serie");

        // Layout principal
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Componentes da interface
        Label lblTipoBusca = new Label("Buscar por:");
        ComboBox<String> cbTipoBusca = new ComboBox<>();
        cbTipoBusca.getItems().addAll("Título", "Categoria", "Ano", "Ator");

        TextField tfBusca = new TextField();
        tfBusca.setPromptText("Digite sua busca");

        ComboBox<String> cbCategoria = new ComboBox<>();
        cbCategoria.setVisible(false);
        for (Categoria cat : RepositorioCategorias.getCategorias())
        {
            cbCategoria.getItems().add(cat.getId() + " - " + cat.getGenero());
        }

        Button btnBuscar = new Button("Buscar");
        Button btnVoltar = new Button("Voltar");

        TextArea taResultado = new TextArea();
        taResultado.setEditable(false);
        taResultado.setWrapText(true);
        taResultado.setPrefHeight(200);

        // Alternar entre campo de texto e categoria
        cbTipoBusca.setOnAction(e -> {
            String tipo = cbTipoBusca.getValue();
            tfBusca.setVisible(!"Categoria".equals(tipo));
            cbCategoria.setVisible("Categoria".equals(tipo));
        });

        // Ação de busca
        btnBuscar.setOnAction(e -> {
            ArrayList<Serie> resultado = new ArrayList<>();
            String tipo = cbTipoBusca.getValue();

            if (tipo == null) {
                Validador.mostrarAlerta("Selecione um tipo de busca.");
                return;
            }

            try
            {
                switch (tipo)
                {
                    case "Título":
                        Serie s = iBuscarMidia.buscarTitulo(tfBusca.getText().toUpperCase().trim(), CatalogoSeries.getCatalogo().getSeries());
                        if (s != null) resultado.add(s);
                        break;

                    case "Categoria":
                        String catStr = cbCategoria.getValue();
                        if (catStr == null) {
                            Validador.mostrarAlerta("Selecione uma categoria.");
                            return;
                        }
                        int idCat = Integer.parseInt(catStr.split(" - ")[0]);
                        resultado = iBuscarMidia.buscarCategoria(idCat, CatalogoSeries.getCatalogo().getSeries());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Ano":
                        int ano = Integer.parseInt(tfBusca.getText().trim());
                        resultado = iBuscarMidia.buscarAno(ano, CatalogoSeries.getCatalogo().getSeries());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Ator":
                        resultado = iBuscarMidia.BuscarNomes(tfBusca.getText().toUpperCase().trim(), CatalogoSeries.getCatalogo().getSeries());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;
                }
            }
            catch (Exception ex)
            {
                Validador.mostrarAlerta("Digite um ano válido");
                return;
            }

            // Mostrar resultados
            taResultado.clear();
            if (resultado.isEmpty())
            {
                taResultado.setText("Nenhuma série encontrada!");
            }
            else
            {
                for (Serie serie : resultado)
                {
                    taResultado.appendText(serie.toString() + "\n\n");
                    taResultado.appendText("_____________________________________\n\n");
                }
            }
        });

        // Ação de voltar
        btnVoltar.setOnAction(e -> stage.setScene(anterior));

        // Adicionar componentes ao grid
        grid.add(lblTipoBusca, 0, 0);
        grid.add(cbTipoBusca, 1, 0);
        grid.add(tfBusca, 0, 1, 2, 1);
        grid.add(cbCategoria, 0, 1, 2, 1);
        grid.add(btnBuscar, 0, 2);
        grid.add(btnVoltar, 1, 2);
        grid.add(taResultado, 0, 3, 2, 1);

        // Cena e exibição
        Scene cena = new Scene(grid, 500, 400);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}