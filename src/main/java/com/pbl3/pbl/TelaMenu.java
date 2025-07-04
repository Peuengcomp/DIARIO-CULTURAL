package com.pbl3.pbl;

import com.pbl3.pbl.Controller.Carregar.Carregar;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação "Diário Cultural".
 * Representa a tela de menu inicial, com opções para criar, buscar,
 * listar, filtrar mídias e sair do programa.
 *
 * Esta classe estende {@link javafx.application.Application}
 * e é o ponto de entrada da aplicação JavaFX.
 */
public class TelaMenu extends Application
{

    /**
     * Método principal (main), responsável por lançar a aplicação JavaFX.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Método sobrescrito da classe {@link Application} que inicializa
     * a interface gráfica da aplicação.
     *
     * @param stage o palco principal da aplicação JavaFX
     */
    @Override
    public void start(Stage stage)
    {
        // Inicializa repositórios e arquivos
        RepositorioCategorias.getInstancia();
        Carregar.CarregarArquivos();

        Label titulo = new Label("Diário Cultural");

        Button btnCriar = new Button("Criar Midia");
        Button btnBuscar = new Button("Buscar Midia");
        Button btnListar = new Button("Listar Midia");
        Button btnFiltrar = new Button("Filtrar Midia");
        Button btnSair = new Button("Sair");

        VBox layout = new VBox(15, titulo, btnCriar, btnBuscar, btnListar, btnFiltrar, btnSair);
        layout.setAlignment(Pos.CENTER);

        Scene cena = new Scene(layout, 300, 350);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());
        titulo.getStyleClass().add("label-titulo");

        stage.setScene(cena);
        stage.setTitle("Diário Cultural");
        stage.show();

        // Eventos dos botões
        btnCriar.setOnAction(e ->
        {
            TelaCriacao telanova = new TelaCriacao(cena);
            telanova.mostrar(stage);
        });

        btnBuscar.setOnAction(e ->
        {
            TelaBuscar telanova = new TelaBuscar(cena);
            telanova.mostrar(stage);
        });

        btnListar.setOnAction(e ->
        {
            TelaListar telanova = new TelaListar(cena);
            telanova.mostrar(stage);
        });

        btnFiltrar.setOnAction(e ->
        {
            TelaFiltrar telanova = new TelaFiltrar(cena);
            telanova.mostrar(stage);
        });

        btnSair.setOnAction(e ->
        {
            System.exit(0);
        });
    }
}
