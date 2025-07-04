package com.pbl3.pbl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Tela para escolher o tipo de busca (Livro, Filme ou Série).
 * Exibe botões que levam para as telas específicas de busca.
 */
public class TelaBuscar
{
    /**
     * Cena anterior para retornar ao voltar.
     */
    private Scene anterior;

    /**
     * Construtor da tela de busca.
     *
     * @param anterior Cena anterior que será exibida ao voltar
     */
    public TelaBuscar(Scene anterior)
    {
        this.anterior = anterior;
    }

    /**
     * Exibe a tela de busca no palco (stage) informado.
     * Configura os botões para abrir as telas de busca específicas.
     *
     * @param stage Palco onde a cena será exibida
     */
    public void mostrar(Stage stage)
    {
        Button btnLivro = new Button("Buscar Livro");
        Button btnFilme = new Button("Buscar Filme");
        Button btnSerie = new Button("Buscar Série");
        Button btnVoltar = new Button("Voltar");

        VBox layout = new VBox(15, btnLivro, btnFilme, btnSerie, btnVoltar);
        layout.setAlignment(Pos.CENTER);

        Scene cena = new Scene(layout, 300, 350);
        cena.getStylesheets().add(getClass().getResource("estilo0.css").toExternalForm());

        btnLivro.setOnAction(e ->
        {
            TelaBuscarLivro telabuscarlivro = new TelaBuscarLivro(cena);
            telabuscarlivro.mostrar(stage);
        });

        btnFilme.setOnAction(e ->
        {
            TelaBuscarFilme telabuscarfilme = new TelaBuscarFilme(cena);
            telabuscarfilme.mostrar(stage);
        });

        btnSerie.setOnAction(e ->
        {
            TelaBuscarSerie telabuscarserie = new TelaBuscarSerie(cena);
            telabuscarserie.mostrar(stage);
        });

        btnVoltar.setOnAction(e ->
        {
            stage.setScene(anterior);
        });

        stage.setScene(cena);
        stage.setTitle("Diário Cultural");
        stage.show();
    }
}