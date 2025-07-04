package com.pbl3.pbl;

import com.pbl3.pbl.Model.Entidades.Filme;
import com.pbl3.pbl.Model.Entidades.Livro;
import com.pbl3.pbl.Model.Entidades.Serie;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;
import com.pbl3.pbl.Model.Repositorios.CatalogoSeries;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe responsável por exibir a tela de listagem de mídias.
 * O usuário pode selecionar o tipo de mídia (Livro, Filme ou Série)
 * e listar os itens presentes no respectivo catálogo.
 */
public class TelaListar
{
    /**
     * Cena anterior, usada para retornar à tela anterior ao clicar em "Voltar".
     */
    private Scene anterior;

    /**
     * Construtor da classe TelaListar.
     *
     * @param anterior cena anterior à tela de listagem
     */
    public TelaListar(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Mostra a tela de listagem de mídias no palco fornecido.
     *
     * @param stage o palco onde a interface será exibida
     */
    public void mostrar(Stage stage)
    {
        stage.setTitle("Listar Mídias");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        ComboBox<String> tipoMidia = new ComboBox<>();
        tipoMidia.getItems().addAll("Livros", "Filmes", "Séries");

        ToggleGroup ordemGroup = new ToggleGroup();
        RadioButton crescente = new RadioButton("Crescente por Avaliação");
        RadioButton decrescente = new RadioButton("Decrescente por Avaliação");
        crescente.setToggleGroup(ordemGroup);
        decrescente.setToggleGroup(ordemGroup);
        crescente.setSelected(true);

        Button btnListar = new Button("Listar");
        TextArea taResultado = new TextArea();
        taResultado.setEditable(false);
        taResultado.setPrefHeight(300);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e ->
                {
                    stage.setScene(anterior);
                }
        );

        btnListar.setOnAction(e ->
        {
            String tipo = tipoMidia.getValue();

            if (tipo == null)
            {
                Validador.mostrarAlerta("Selecione uma mídia.");
                return;
            }

            boolean tem = false;
            int valor = 0;
            taResultado.clear();

            String CatalogoInfo = null;

            switch (tipo)
            {
                case "Livros":
                    tem = CatalogoLivros.getCatalogo().getLivros().isEmpty();
                    CatalogoInfo = "de livros";
                    valor = 1;
                    break;
                case "Filmes":
                    tem = CatalogoFilmes.getCatalogo().getFilmes().isEmpty();
                    CatalogoInfo = "de filmes";
                    valor = 2;
                    break;
                case "Séries":
                    tem = CatalogoSeries.getCatalogo().getSeries().isEmpty();
                    CatalogoInfo = "de séries";
                    valor = 3;
                    break;
            }

            if (tem)
            {
                taResultado.setText(("Catálogo " + CatalogoInfo + " vazio!").toUpperCase());
            }
            else
            {
                if (valor == 1)
                {
                    CatalogoLivros.getCatalogo().ListarOrdenar(crescente.isSelected());
                    for (Livro livro : CatalogoLivros.getCatalogo().getLivros())
                    {
                        taResultado.appendText(livro.toString() + "\n");
                        taResultado.appendText("_____________________________________\n\n");
                    }
                }
                else if (valor == 2)
                {
                    CatalogoFilmes.getCatalogo().ListarOrdenar(crescente.isSelected());
                    for (Filme filme : CatalogoFilmes.getCatalogo().getFilmes())
                    {
                        taResultado.appendText(filme.toString() + "\n\n");
                        taResultado.appendText("_____________________________________\n\n");
                    }
                }
                else if (valor == 3)
                {
                    CatalogoSeries.getCatalogo().ListarOrdenar(crescente.isSelected());
                    for (Serie serie : CatalogoSeries.getCatalogo().getSeries())
                    {
                        taResultado.appendText(serie.toString() + "\n\n");
                        taResultado.appendText("_____________________________________\n\n");
                    }
                }
            }
        });

        layout.getChildren().addAll(
                new Label("Tipo de Mídia:"), tipoMidia,
                new Label("Ordem:"), new HBox(10, crescente, decrescente),
                btnListar, taResultado, btnVoltar
        );

        Scene cena = new Scene(layout, 600, 400);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}