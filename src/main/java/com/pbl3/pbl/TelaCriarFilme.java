package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Criar.CriarFilme;
import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Tela de interface gráfica para criação de filmes.
 * Permite ao usuário preencher os dados de um filme e salvá-lo no sistema.
 */
public class TelaCriarFilme
{
    /**
     * Cena anterior da aplicação. Usada para retornar após salvar ou cancelar.
     */
    private Scene anterior;

    /**
     * Construtor da tela de criação de filmes.
     *
     * @param anterior a cena anterior que será restaurada após a criação do filme.
     */
    public TelaCriarFilme(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a tela para criação de um novo filme.
     *
     * @param stage o palco (janela) onde a cena será mostrada.
     */
    public void mostrar(Stage stage)
    {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Campos de entrada
        Label lblTitulo = new Label("Título:");
        TextField tfTitulo = new TextField();
        tfTitulo.setPromptText("Ex: Scarface");

        Label lblAno = new Label("Ano:");
        TextField tfAno = new TextField();
        tfAno.setPromptText("Ex: 1983");

        ArrayList<Categoria> categorias = RepositorioCategorias.getCategorias();

        Label lblCategoria = new Label("Categoria:");
        ComboBox<Categoria> cbCategoria = new ComboBox<>();
        cbCategoria.setPromptText("Selecione uma categoria");
        cbCategoria.getItems().addAll(categorias);

        Label lblTituloOriginal = new Label("Título Original:");
        TextField tfTituloOriginal = new TextField();
        tfTituloOriginal.setPromptText("Ex: Scarface");

        Label lblOndeAssistir = new Label("Onde Assistir:");
        TextField tfOndeAssistir = new TextField();
        tfOndeAssistir.setPromptText("Ex: Netflix");

        Label lblDuracao = new Label("Duração (min):");
        TextField tfDuracao = new TextField();
        tfDuracao.setPromptText("Ex: 185");

        Label lblDiretores = new Label("Diretores (separados por vírgula):");
        TextField tfDiretores = new TextField();
        tfDiretores.setPromptText("Ex: Brian De Palma, Fulano, Ciclano");

        Label lblRoteiristas = new Label("Roteiristas (separados por vírgula):");
        TextField tfRoteiristas = new TextField();
        tfRoteiristas.setPromptText("Ex: Oliver Stone, Ethan Coen");

        Label lblElenco = new Label("Elenco (separados por vírgula):");
        TextField tfElenco = new TextField();
        tfElenco.setPromptText("Ex: Al Pacino, Michelle Pfeiffer");

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

        Label lblData = new Label("Data que assistiu (dd/mm/aaaa):");
        TextField tfData = new TextField();
        tfData.setPromptText("Ex: 24/06/2025");

        // Botões
        Button btnSalvar = new Button("Salvar");
        Button btnVoltar = new Button("Voltar");

        /**
         * Ação do botão "Voltar": retorna para a tela anterior.
         */
        btnVoltar.setOnAction(e -> stage.setScene(anterior));

        /**
         * Ação do botão "Salvar": valida os campos e cria o filme.
         */
        btnSalvar.setOnAction(e ->
        {
            int id;
            int ano;
            int duracao;
            LocalDate data;

            ArrayList<String> direcao = new ArrayList<>(List.of(tfDiretores.getText().toUpperCase().split(",")));
            ArrayList<String> roteiro = new ArrayList<>(List.of(tfRoteiristas.getText().toUpperCase().split(",")));
            ArrayList<String> elenco = new ArrayList<>(List.of(tfElenco.getText().toUpperCase().split(",")));

            // Remover espaços extras
            direcao.replaceAll(String::trim);
            roteiro.replaceAll(String::trim);
            elenco.replaceAll(String::trim);

            // Validações
            if (!Validador.validarCampo(tfTitulo, "O título é obrigatório"))
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

            if (!Validador.validarCampo(tfTituloOriginal, "Título Original é obrigatório"))
            {
                tfTituloOriginal.requestFocus();
                return;
            }

            if (!Validador.validarCampo(tfOndeAssistir, "Onde assistir é obrigatório"))
            {
                tfOndeAssistir.requestFocus();
                return;
            }

            try
            {
                duracao = Validador.ValidarDuracao(tfDuracao.getText().trim());
            }
            catch (IllegalArgumentException erro)
            {
                Validador.mostrarAlerta(erro.getMessage());
                tfDuracao.requestFocus();
                return;
            }

            if (!Validador.validarCampo(tfDiretores, "Indicar diretores é obrigatório"))
            {
                tfDiretores.requestFocus();
                return;
            }

            if (!Validador.validarCampo(tfRoteiristas, "Indicar roteiristas é obrigatório"))
            {
                tfRoteiristas.requestFocus();
                return;
            }

            if (!Validador.validarCampo(tfElenco, "Indicar elenco é obrigatório"))
            {
                tfElenco.requestFocus();
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
                tfData.requestFocus();
                return;
            }

            // Criação do filme

            CriarFilme.CriarFilme(
                    tfTitulo.getText().trim().toUpperCase(), ano,
                    RepositorioCategorias.getCategoria(id),
                    tfTituloOriginal.getText().toUpperCase(),
                    duracao,
                    tfOndeAssistir.getText().toUpperCase(),
                    elenco, roteiro, direcao,
                    taReview.getText(), avaliacao, data
            );

            Validador.mostrarInfo("Filme Criado com sucesso");
            stage.setScene(anterior);

        });

        // Posicionamento no grid
        grid.add(lblTitulo, 0, 0);
        grid.add(tfTitulo, 1, 0);

        grid.add(lblAno, 0, 1);
        grid.add(tfAno, 1, 1);

        grid.add(lblCategoria, 0, 4);
        grid.add(cbCategoria, 1, 4);

        grid.add(lblTituloOriginal, 0, 5);
        grid.add(tfTituloOriginal, 1, 5);

        grid.add(lblOndeAssistir, 0, 6);
        grid.add(tfOndeAssistir, 1, 6);

        grid.add(lblDuracao, 0, 7);
        grid.add(tfDuracao, 1, 7);

        grid.add(lblDiretores, 0, 8);
        grid.add(tfDiretores, 1, 8);

        grid.add(lblRoteiristas, 0, 9);
        grid.add(tfRoteiristas, 1, 9);

        grid.add(lblElenco, 0, 10);
        grid.add(tfElenco, 1, 10);

        grid.add(lblReview, 0, 11);
        grid.add(taReview, 1, 11);

        grid.add(lblAvaliacao, 0, 12);
        grid.add(cbAvaliacao, 1, 12);

        grid.add(lblData, 0, 13);
        grid.add(tfData, 1, 13);

        grid.add(btnSalvar, 0, 14);
        grid.add(btnVoltar, 1, 14);

        // Criar e exibir a cena
        Scene cena = new Scene(grid, 600, 650);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}