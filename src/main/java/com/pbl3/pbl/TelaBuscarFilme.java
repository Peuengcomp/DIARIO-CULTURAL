package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Buscar.BuscarFilme;
import com.pbl3.pbl.Controller.Buscar.iBuscarMidia;
import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Entidades.Filme;
import com.pbl3.pbl.Model.Repositorios.CatalogoFilmes;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Tela para buscar filmes por diferentes critérios (título, categoria, ano, diretor, ator).
 */
public class TelaBuscarFilme
{
    private Scene anterior; // Cena anterior para voltar

    public TelaBuscarFilme(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Mostra a tela de busca de filmes.
     * @param stage palco onde a tela será exibida
     */
    public void mostrar(Stage stage)
    {
        stage.setTitle("Buscar Filme");

        // Layout base da tela: GridPane com espaçamento e alinhamento central
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Componentes de busca
        Label lblTipoBusca = new Label("Buscar por:");
        ComboBox<String> cbTipoBusca = new ComboBox<>();
        cbTipoBusca.getItems().addAll("Título", "Categoria", "Ano", "Diretor", "Ator");

        TextField tfBusca = new TextField();
        tfBusca.setPromptText("Digite sua busca");

        // ComboBox para categoria, inicialmente invisível
        RepositorioCategorias.getInstancia();
        ComboBox<String> cbCategoria = new ComboBox<>();
        cbCategoria.setVisible(false);
        for (Categoria cat : RepositorioCategorias.getCategorias()) {
            cbCategoria.getItems().add(cat.getId() + " - " + cat.getGenero());
        }

        Button btnBuscar = new Button("Buscar");
        Button btnVoltar = new Button("Voltar");

        // Área para exibir resultados, não editável e com quebra de linha automática
        TextArea taResultado = new TextArea();
        taResultado.setEditable(false);
        taResultado.setWrapText(true);
        taResultado.setPrefHeight(200);

        // Alterna a visibilidade do campo de texto e do combo de categoria conforme o tipo de busca
        cbTipoBusca.setOnAction(e -> {
            String tipo = cbTipoBusca.getValue();
            tfBusca.setVisible(!"Categoria".equals(tipo));
            cbCategoria.setVisible("Categoria".equals(tipo));
        });

        // Ação do botão Buscar, realiza a busca conforme o tipo selecionado
        btnBuscar.setOnAction(e ->
        {
            ArrayList<Filme> resultado = new ArrayList<>();
            String tipo = cbTipoBusca.getValue();

            if (tipo == null)
            {
                Validador.mostrarAlerta("Selecione um tipo de busca.");
                return;
            }

            try
            {
                switch (tipo)
                {
                    case "Título":
                        Filme f1 = iBuscarMidia.buscarTitulo(tfBusca.getText().toUpperCase().trim(), CatalogoFilmes.getCatalogo().getFilmes());
                        if (f1 != null) resultado.add(f1);
                        break;

                    case "Categoria":
                        String catStr = cbCategoria.getValue();
                        if (catStr == null) {
                            Validador.mostrarAlerta("Selecione uma categoria.");
                            return;
                        }
                        int idCat = Integer.parseInt(catStr.split(" - ")[0]);
                        resultado = iBuscarMidia.buscarCategoria(idCat, CatalogoFilmes.getCatalogo().getFilmes());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Ano":
                        int ano = Integer.parseInt(tfBusca.getText().trim());
                        resultado = iBuscarMidia.buscarAno(ano, CatalogoFilmes.getCatalogo().getFilmes());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Diretor":
                        resultado = BuscarFilme.buscarDiretor(tfBusca.getText().toUpperCase().trim(), CatalogoFilmes.getCatalogo().getFilmes());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Ator":
                        resultado = iBuscarMidia.BuscarNomes(tfBusca.getText().toUpperCase().trim(), CatalogoFilmes.getCatalogo().getFilmes());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;
                }
            }
            catch (Exception ex)
            {
                Validador.mostrarAlerta("Digite um ano válido");
                return;
            }

            // Exibe os resultados na área de texto
            taResultado.clear();
            if (resultado.isEmpty())
            {
                taResultado.setText("Nenhum filme encontrado!");
            }
            else
            {
                for (Filme f : resultado)
                {
                    taResultado.appendText(f.toString() + "\n\n");
                    taResultado.appendText("_____________________________________\n\n");
                }
            }
        });

        // Voltar para a cena anterior
        btnVoltar.setOnAction(e -> stage.setScene(anterior));

        // Montagem da tela
        grid.add(lblTipoBusca, 0, 0);
        grid.add(cbTipoBusca, 1, 0);
        grid.add(tfBusca, 0, 1, 2, 1);
        grid.add(cbCategoria, 0, 1, 2, 1);
        grid.add(btnBuscar, 0, 2);
        grid.add(btnVoltar, 1, 2);
        grid.add(taResultado, 0, 3, 2, 1);

        Scene cena = new Scene(grid, 500, 400);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}