package com.pbl3.pbl;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validador
{
    public static int ValidarAno(String texto) throws IllegalArgumentException
    {
        try
        {
            int valor = Integer.parseInt(texto);
            return valor;
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Digite um ano válido.");
        }
    }

    public static LocalDate ValidarData(String texto) throws IllegalArgumentException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try
        {
             return LocalDate.parse(texto, formatter);
        }
        catch (DateTimeParseException e)
        {
            throw new IllegalArgumentException("Digite a data no formato indicado.");
        }
    }

    public static int ValidarDuracao(String texto) throws IllegalArgumentException
    {
        try
        {
            int valor = Integer.parseInt(texto);
            return valor;
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException(("Digite a duração em minutos"));
        }
    }

    public static int ValidarInteiro(String texto) throws IllegalArgumentException
    {
        try
        {
            int valor = Integer.parseInt(texto);
            if (valor < 1)
            {
                throw new IllegalArgumentException("Digite uma quantidade de episódios válida");
            }
            return valor;
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Digite uma quantidade de episódios válida");
        }
    }

    public static int ValidarEncerramento(String texto) throws IllegalArgumentException
    {
        try
        {
            int valor = Integer.parseInt(texto);
            return valor;
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Digite um ano de encerramento válido");
        }
    }

    public static boolean validarCampo(TextField tf, String msg)
    {
        if (tf.getText().trim().isEmpty())
        {
            Validador.mostrarAlerta(msg);
            return false;
        }
        return true;
    }

    public static void mostrarAlerta(String mensagem)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de validação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mostrarInfo(String info)
    {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informação");
        alerta.setHeaderText(null);
        alerta.setContentText(info);
        alerta.showAndWait();
    }
}

