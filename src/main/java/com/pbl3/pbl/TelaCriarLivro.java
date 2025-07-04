package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Criar.CriarLivro;
import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Tela para criação de livros.
 * Permite ao usuário inserir informações de um livro, validar os dados
 * e salvá-lo no catálogo usando a classe {@link CriarLivro}.
 */
public class TelaCriarLivro
{
    /**
     * Cena anterior à tela atual. Usada para retornar ao menu anterior.
     */
    private Scene anterior;

    /**
     * Construtor da tela de criação de livros.
     *
     * @param anterior a cena anterior da aplicação
     */
    public TelaCriarLivro(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Mostra a interface de criação de livro em um determinado palco.
     *
     * @param stage o palco onde a interface será exibida
     */
    public void mostrar(Stage stage)
    {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Campos
        Label lblTitulo = new Label("Título:");
        TextField tfTitulo = new TextField();
        tfTitulo.setPromptText("Ex: O Mundo de Sofia");

        Label lblAno = new Label("Ano:");
        TextField tfAno = new TextField();
        tfAno.setPromptText("Ex: 1991");

        ArrayList<Categoria> categorias = RepositorioCategorias.getCategorias();

        Label lblCategoria = new Label("Categoria:");
        ComboBox<Categoria> cbCategoria = new ComboBox<>();
        cbCategoria.setPromptText("Selecione uma categoria");
        cbCategoria.getItems().addAll(categorias);

        Label lblAutor = new Label("Autor:");
        TextField tfAutor = new TextField();
        tfAutor.setPromptText("Ex: Jostein Gaarder");

        Label lblEditora = new Label("Editora:");
        TextField tfEditora = new TextField();
        tfEditora.setPromptText("Ex: Companhia das Letras");

        Label lblISBN = new Label("ISBN:");
        TextField tfISBN = new TextField();
        tfISBN.setPromptText("Ex: 9788580865189");

        Label lblExemplar = new Label("Tem exemplar:");

        RadioButton rbSim = new RadioButton("Sim");
        RadioButton rbNao = new RadioButton("Não");

        ToggleGroup grupo = new ToggleGroup();
        rbSim.setToggleGroup(grupo);
        rbNao.setToggleGroup(grupo);

        Label lblReview = new Label("Review:");
        TextArea taReview = new TextArea();
        taReview.setPrefRowCount(3);

        Label lblAvaliacao = new Label("Avaliação:");
        ComboBox<Integer> cbAvaliacao = new ComboBox<>();
        cbAvaliacao.setPromptText("Avalie");

        for (int i = 1; i <= 5; i++)
        {
            cbAvaliacao.getItems().add(i);
        }

        Label lblData = new Label("Data que finalizou a leitura (dd/mm/aaaa):");
        TextField tfData = new TextField();
        tfData.setPromptText("Ex: 01/07/2025");

        // Botões
        Button btnSalvar = new Button("Salvar");
        Button btnVoltar = new Button("Voltar");

        /**
         * Ação para voltar à tela anterior.
         */
        btnVoltar.setOnAction(e ->
        {
            stage.setScene(anterior);
        });

        /**
         * Ação executada ao clicar no botão "Salvar".
         * Valida os dados, cria o livro e exibe confirmação.
         */
        btnSalvar.setOnAction(e ->
        {
            int id;
            int ano;

            boolean exemplar;
            LocalDate data;


            if (!Validador.validarCampo(tfTitulo, "O título é obrigatório."))
            {
                tfTitulo.requestFocus();
                return;
            }

            try
            {
                ano = Validador.ValidarAno(tfAno.getText().trim());
            }
            catch (IllegalArgumentException erro)
            {
                Validador.mostrarAlerta(erro.getMessage());
                tfAno.requestFocus();
                return;
            }

            Categoria categoriaSelecionada = cbCategoria.getValue();
            if (categoriaSelecionada == null)
            {
                Validador.mostrarAlerta("Selecione uma categoria.");
                cbCategoria.requestFocus();
                return;
            }
            else
            {
                id = categoriaSelecionada.getId();
            }

            if (!Validador.validarCampo(tfAutor, "Autor é obrigatório."))
            {
                tfAutor.requestFocus();
                return;
            }
            if (!Validador.validarCampo(tfEditora, "Editora é obrigatório."))
            {
                tfEditora.requestFocus();
                return;
            }

            if (!Validador.validarCampo(tfISBN, "ISBN é obrigatório."))
            {
                tfISBN.requestFocus();
                return;
            }

            if (rbSim.isSelected())
            {
                exemplar = true;
            }
            else if (rbNao.isSelected())
            {
                exemplar = false;
            }
            else
            {
                Validador.mostrarAlerta("Marque se você tem o exemplar");
                return;
            }

            if (taReview.getText().trim().isEmpty())
            {
                Validador.mostrarAlerta("Fazer review é obrigatório");
                taReview.requestFocus();
                return;
            }

            Integer avaliacao = cbAvaliacao.getValue();
            if (avaliacao == null)
            {
                Validador.mostrarAlerta("Faça uma avaliação.");
                cbAvaliacao.requestFocus();
                return;
            }

            try
            {
                data = Validador.ValidarData(tfData.getText().trim());
            }
            catch (IllegalArgumentException erro)
            {
                Validador.mostrarAlerta(erro.getMessage());
                return;
            }


            CriarLivro.criarLivro(tfTitulo.getText().trim().toUpperCase(), ano, RepositorioCategorias.getCategoria(id),
                    tfAutor.getText().toUpperCase(), tfEditora.getText().toUpperCase(), tfISBN.getText().toUpperCase(),
                    exemplar, taReview.getText(), avaliacao, data);

            Validador.mostrarInfo("Livro criado com sucesso!");
            stage.setScene(anterior);

        });

        // Posicionar no grid
        grid.add(lblTitulo, 0, 0);
        grid.add(tfTitulo, 1, 0);

        grid.add(lblAno, 0, 1);
        grid.add(tfAno, 1, 1);

        grid.add(lblCategoria, 0, 4);
        grid.add(cbCategoria, 1, 4);

        grid.add(lblAutor, 0, 5);
        grid.add(tfAutor, 1, 5);

        grid.add(lblEditora, 0, 6);
        grid.add(tfEditora, 1, 6);

        grid.add(lblISBN, 0, 7);
        grid.add(tfISBN, 1, 7);

        HBox grupoBotoes = new HBox(10, rbSim, rbNao);
        grid.add(lblExemplar, 0, 8);
        grid.add(grupoBotoes, 1, 8);

        grid.add(lblReview, 0, 12);
        grid.add(taReview, 1, 12);

        grid.add(lblAvaliacao, 0, 13);
        grid.add(cbAvaliacao, 1, 13);

        grid.add(lblData, 0, 14);
        grid.add(tfData, 1, 14);

        grid.add(btnSalvar, 0, 15);
        grid.add(btnVoltar, 1, 15);

        Scene cena = new Scene(grid, 600, 650);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}