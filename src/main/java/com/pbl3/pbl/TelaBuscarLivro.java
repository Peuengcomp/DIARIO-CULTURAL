package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Buscar.BuscarLivro;
import com.pbl3.pbl.Controller.Buscar.iBuscarMidia;
import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Entidades.Livro;
import com.pbl3.pbl.Model.Repositorios.CatalogoLivros;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Classe responsável pela interface de busca de livros.
 * Permite realizar buscas por título, categoria, ano, autor ou ISBN.
 */
public class TelaBuscarLivro
{
    /**
     * Cena anterior à tela atual, usada para voltar ao menu anterior.
     */
    private Scene anterior;

    /**
     * Construtor da tela de busca de livros.
     *
     * @param anterior Cena anterior para navegação reversa.
     */
    public TelaBuscarLivro(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a tela de busca de livros no palco fornecido.
     *
     * @param stage O palco principal da aplicação.
     */
    public void mostrar(Stage stage)
    {
        stage.setTitle("Buscar Livro");

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Componentes
        Label lblTipoBusca = new Label("Buscar por:");
        ComboBox<String> cbTipoBusca = new ComboBox<>();
        cbTipoBusca.getItems().addAll("Título", "Categoria", "Ano", "Autor", "ISBN");

        TextField tfBusca = new TextField();
        tfBusca.setPromptText("Digite sua busca");

        // Carregamento de categorias
        RepositorioCategorias.getInstancia();
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

        /**
         * Ação ao clicar em "Buscar".
         * Realiza busca de livros conforme o tipo de critério selecionado.
         */
        btnBuscar.setOnAction(e ->
        {
            ArrayList<Livro> resultado = new ArrayList<>();
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
                        Livro livro = iBuscarMidia.buscarTitulo(tfBusca.getText().toUpperCase().trim(), CatalogoLivros.getCatalogo().getLivros());
                        if (livro != null) resultado.add(livro);
                        break;

                    case "Categoria":
                        String catStr = cbCategoria.getValue();
                        if (catStr == null) {
                            Validador.mostrarAlerta("Selecione uma categoria.");
                            return;
                        }
                        int idCat = Integer.parseInt(catStr.split(" - ")[0]);
                        resultado = iBuscarMidia.buscarCategoria(idCat, CatalogoLivros.getCatalogo().getLivros());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Ano":
                        int ano = Integer.parseInt(tfBusca.getText().trim());
                        resultado = iBuscarMidia.buscarAno(ano, CatalogoLivros.getCatalogo().getLivros());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "Autor":
                        resultado = BuscarLivro.buscarAutor(tfBusca.getText().toUpperCase().trim());
                        if (resultado == null) resultado = new ArrayList<>();
                        break;

                    case "ISBN":
                        Livro l = BuscarLivro.buscarISBN(tfBusca.getText().toUpperCase().trim());
                        if (l != null) resultado.add(l);
                        break;
                }
            }
            catch (Exception ex)
            {
                Validador.mostrarAlerta("Digite um ano válido");
                return;
            }

            // Exibir resultados
            taResultado.clear();
            if (resultado.isEmpty())
            {
                taResultado.setText("Nenhum livro encontrado!");
            }
            else
            {
                for (Livro l : resultado)
                {
                    taResultado.appendText(l.toString() + "\n\n");
                    taResultado.appendText("_____________________________________\n\n");
                }
            }
        });

        /**
         * Alterna entre TextField e ComboBox dependendo do tipo de busca selecionado.
         */
        cbTipoBusca.setOnAction(e -> {
            String tipo = cbTipoBusca.getValue();
            if ("Categoria".equals(tipo)) {
                tfBusca.setVisible(false);
                cbCategoria.setVisible(true);
            } else {
                tfBusca.setVisible(true);
                cbCategoria.setVisible(false);
            }
        });

        btnVoltar.setOnAction(e -> stage.setScene(anterior));

        // Adição ao layout
        grid.add(lblTipoBusca, 0, 0);
        grid.add(cbTipoBusca, 1, 0);
        grid.add(tfBusca, 0, 1, 2, 1);
        grid.add(cbCategoria, 0, 1, 2, 1);
        grid.add(btnBuscar, 0, 2);
        grid.add(btnVoltar, 1, 2);
        grid.add(taResultado, 0, 3, 2, 1);

        // Criar e exibir a cena
        Scene cena = new Scene(grid, 500, 400);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}
