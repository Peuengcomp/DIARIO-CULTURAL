package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Criar.CriarSerie;
import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Entidades.Temporada;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Tela para criação de séries.
 * Permite ao usuário preencher os dados da série, adicionar temporadas com avaliação,
 * validar as informações e salvar no repositório de séries.
 */
public class TelaCriarSerie
{
    /**
     * Cena anterior, usada para retornar ao menu anterior.
     */
    private Scene anterior;

    /**
     * Construtor da tela de criação de séries.
     *
     * @param anterior cena da qual o usuário veio
     */
    public TelaCriarSerie(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a tela de criação de séries no palco fornecido.
     *
     * @param stage o palco onde a tela será apresentada
     */
    public void mostrar(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Campos principais do formulário
        Label lblTitulo = new Label("Título:");
        TextField tfTitulo = new TextField();
        tfTitulo.setPromptText("Ex: Pantheon");

        Label lblAno = new Label("Ano:");
        TextField tfAno = new TextField();
        tfAno.setPromptText("Ex: 2022");

        ArrayList<Categoria> categorias = RepositorioCategorias.getCategorias();

        Label lblCategoria = new Label("Categoria:");
        ComboBox<Categoria> cbCategoria = new ComboBox<>();
        cbCategoria.setPromptText("Selecione uma categoria");
        cbCategoria.getItems().addAll(categorias);

        Label lblTituloOriginal = new Label("Título Original:");
        TextField tfTituloOriginal = new TextField();
        tfTituloOriginal.setPromptText("Ex: Pantheon");

        Label lblOndeAssistir = new Label("Onde Assistir:");
        TextField tfOndeAssistir = new TextField();
        tfOndeAssistir.setPromptText("Ex: Netflix");

        Label lblElenco = new Label("Elenco (separados por vírgula):");
        TextField tfElenco = new TextField();
        tfElenco.setPromptText("Ex: Katie Chang, Paul Dano, Daniel Dae Kim");

        Label lblEncerramento = new Label("Encerramento:");
        TextField tfEncerramento = new TextField();
        tfEncerramento.setPromptText("Ex: 2022");

        Label lblAddTemporada = new Label("Adicionar temporadas");
        Button btnAdicionarTemporada = new Button("+");
        btnAdicionarTemporada.getStyleClass().add("botao-nativo");

        Label lblTemporadas = new Label("Temporadas adicionadas:");
        TextArea taTemporadas = new TextArea();
        taTemporadas.setEditable(false);
        taTemporadas.setPrefRowCount(5);
        taTemporadas.setWrapText(true);

        ArrayList<Temporada> temporadas = new ArrayList<>();
        VBox BoxTemporadas = new VBox(10);
        BoxTemporadas.setPadding(new Insets(10));

        /**
         * Ação para abrir o popup de criação de temporada
         */
        btnAdicionarTemporada.setOnAction(e ->
        {
            Stage popup = new Stage();
            popup.setTitle("Nova Temporada");

            GridPane popupGrid = new GridPane();
            popupGrid.setPadding(new Insets(20));
            popupGrid.setVgap(10);
            popupGrid.setHgap(10);
            popupGrid.setAlignment(Pos.CENTER);

            TextField tfAnoTemp = new TextField();
            tfAnoTemp.setPromptText("Ex: 2022");

            TextField tfQtdEps = new TextField();
            tfQtdEps.setPromptText("Ex: 8");

            TextArea taReview = new TextArea();
            taReview.setPrefRowCount(3);
            taReview.setWrapText(true);

            Label lblAvaliacao = new Label("Avaliação:");
            ComboBox<Integer> cbAvaliacao = new ComboBox<>();
            cbAvaliacao.setPromptText("Avalie");

            for (int i = 1; i <= 5; i++)
            {
                cbAvaliacao.getItems().add(i);
            }

            TextField tfDataFinal = new TextField();
            tfDataFinal.setPromptText("Ex: 28/06/2025");

            Button btnAdicionar = new Button("Adicionar");
            Button btnCancelar = new Button("Cancelar");

            /**
             * Ação para validar e adicionar uma nova temporada
             */
            btnAdicionar.setOnAction(ev ->
            {
                int ano;
                int qtdEp;
                LocalDate data;

                try
                {
                    ano = Validador.ValidarAno(tfAnoTemp.getText().trim());
                }
                catch (IllegalArgumentException erro)
                {
                    Validador.mostrarAlerta(erro.getMessage());
                    return;
                }

                try
                {
                    qtdEp = Validador.ValidarInteiro(tfQtdEps.getText().trim());
                }
                catch (IllegalArgumentException erro)
                {
                    Validador.mostrarAlerta(erro.getMessage());
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
                    data = Validador.ValidarData(tfDataFinal.getText().trim());
                }
                catch (IllegalArgumentException erro)
                {
                    Validador.mostrarAlerta(erro.getMessage());
                    return;
                }


                Temporada temporada = new Temporada(temporadas.size()+1, ano, qtdEp, taReview.getText(), avaliacao, data);
                temporadas.add(temporada);

                String info = "Temporada " + temporadas.size() + " - Ano: " + tfAnoTemp.getText() + " - Avaliação: " + avaliacao;
                taTemporadas.appendText(info + "\n");

                popup.close();

            });

            btnCancelar.setOnAction(ev -> popup.close());

            // Layout do popup
            popupGrid.add(new Label("Ano da temporada:"), 0, 0);
            popupGrid.add(tfAnoTemp, 1, 0);
            popupGrid.add(new Label("Quantidade de Episódios:"), 0, 1);
            popupGrid.add(tfQtdEps, 1, 1);
            popupGrid.add(new Label("Review:"), 0, 2);
            popupGrid.add(taReview, 1, 2);
            popupGrid.add(new Label("Avaliação:"), 0, 3);
            popupGrid.add(cbAvaliacao, 1, 3);
            popupGrid.add(new Label("Data de Finalização:"), 0, 4);
            popupGrid.add(tfDataFinal, 1, 4);
            popupGrid.add(btnAdicionar, 0, 5);
            popupGrid.add(btnCancelar, 1, 5);

            Scene popupScene = new Scene(popupGrid, 600, 450);
            popup.setScene(popupScene);
            popupScene.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
            popup.show();
        });

        // Botões
        Button btnSalvar = new Button("Salvar");
        Button btnVoltar = new Button("Voltar");

        /**
         * Ação para voltar à tela anterior
         */
        btnVoltar.setOnAction(e -> stage.setScene(anterior));

        /**
         * Ação para salvar a série criada
         */
        btnSalvar.setOnAction(ev ->
        {
            int ano = 0;
            int id = 0;
            int ano_encerramento = 0;

            if (tfTitulo.getText().trim().isEmpty())
            {
                Validador.mostrarAlerta("O título é obrigatório.");
                tfTitulo.requestFocus();
                return;
            }

            try { ano = Validador.ValidarAno(tfAno.getText().trim()); }
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

            if (!Validador.validarCampo(tfElenco, "Indicar elenco é obrigatório"))
            {
                tfElenco.requestFocus();
                return;
            }

            try
            {
                ano_encerramento = Validador.ValidarEncerramento(tfEncerramento.getText().trim());
            }
            catch (IllegalArgumentException erro)
            {
                Validador.mostrarAlerta(erro.getMessage());
                tfEncerramento.requestFocus();
                return;
            }

            if (taTemporadas.getText().trim().isEmpty())
            {
                Validador.mostrarAlerta("Adicionar temporadas é obrigatório");
                taTemporadas.requestFocus();
                return;
            }

            ArrayList<String> elenco = new ArrayList<>(List.of(tfElenco.getText().toUpperCase().split(",")));
            elenco.replaceAll(String::trim);


            CriarSerie.criarSerie(tfTitulo.getText().trim().toUpperCase(), ano,
                    RepositorioCategorias.getCategoria(id), tfTituloOriginal.getText(), tfOndeAssistir.getText(),
                    elenco, ano_encerramento, temporadas);

            Validador.mostrarInfo("Série criada com sucesso!");
            stage.setScene(anterior);

        });

        // Posicionar elementos no grid
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
        grid.add(lblElenco, 0, 7);
        grid.add(tfElenco, 1, 7);
        grid.add(lblEncerramento, 0, 8);
        grid.add(tfEncerramento, 1, 8);
        grid.add(lblAddTemporada, 0, 9);
        grid.add(btnAdicionarTemporada, 1, 9);
        grid.add(BoxTemporadas, 0, 10, 2, 1);
        grid.add(lblTemporadas, 0, 11);
        grid.add(taTemporadas, 0, 12, 2, 1);
        grid.add(btnSalvar, 0, 13);
        grid.add(btnVoltar, 1, 13);

        Scene cena = new Scene(grid, 600, 650);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
    }
}
