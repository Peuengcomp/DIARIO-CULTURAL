package com.pbl3.pbl.Controller.Filtrar;

import com.pbl3.pbl.Model.Entidades.Midia;

import java.util.ArrayList;
import java.util.stream.Collectors;

// Esta interface tem por finalidade filtrar uma lista de objetos de uma determinada classe.
// Usa-se o tipo genérico T que herda de Midia para realizar a filtragem.

public interface iFiltrar<T>
{
    static <T extends Midia> ArrayList<T> FiltrarPorAno(ArrayList<T> lista, int ano) {
        return lista.stream()
                .filter(objeto -> objeto.getAno() == ano)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    static <T extends Midia> ArrayList<T> FiltrarPorCategoria(ArrayList<T> lista, int idCategoria) {
        return lista.stream()
                .filter(objeto -> objeto.getCategoria().getId() == idCategoria)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
