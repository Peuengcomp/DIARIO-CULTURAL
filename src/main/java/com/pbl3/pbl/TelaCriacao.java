package com.pbl3.pbl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Tela principal de criação de mídias.
 * Oferece opções para o usuário criar livros, filmes ou séries.
 */
public class TelaCriacao
{
    /**
     * Cena anterior da aplicação, usada para retornar ao menu anterior.
     */
    private Scene anterior;

    /**
     * Construtor da tela de criação.
     *
     * @param anterior Cena anterior a ser restaurada ao clicar em "Voltar".
     */
    public TelaCriacao(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a interface gráfica com botões para criação de livro, filme ou série.
     *
     * @param stage O palco (janela) da aplicação.
     */
    public void mostrar(Stage stage)
    {
        // Botões de ação
        Button btnCriarLivro = new Button("Criar Livro");
        Button btnCriarFilme = new Button("Criar Filme");
        Button btnCriarSerie = new Button("Criar Série");
        Button btnVoltar = new Button("Voltar");

        // Layout vertical com espaçamento e alinhamento central
        VBox layout = new VBox(15, btnCriarLivro, btnCriarFilme, btnCriarSerie, btnVoltar);
        layout.setAlignment(Pos.CENTER);

        // Cena da tela de criação
        Scene cena = new Scene(layout, 300, 350);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());

        /**
         * Ação para abrir a tela de criação de livros.
         */
        btnCriarLivro.setOnAction(e ->
        {
            TelaCriarLivro telanova = new TelaCriarLivro(cena);
            telanova.mostrar(stage);
        });

        /**
         * Ação para abrir a tela de criação de filmes.
         */
        btnCriarFilme.setOnAction(e ->
        {
            TelaCriarFilme telanova = new TelaCriarFilme(cena);
            telanova.mostrar(stage);
        });

        /**
         * Ação para abrir a tela de criação de séries.
         */
        btnCriarSerie.setOnAction(e ->
        {
            TelaCriarSerie telanova = new TelaCriarSerie(cena);
            telanova.mostrar(stage);
        });

        /**
         * Ação para voltar à cena anterior.
         */
        btnVoltar.setOnAction(e ->
        {
            stage.setScene(anterior);
        });

        // Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Diário Cultural");
        stage.show();
    }
}