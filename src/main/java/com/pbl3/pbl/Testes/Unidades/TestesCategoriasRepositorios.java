package com.pbl3.pbl.Testes.Unidades;

import com.pbl3.pbl.Model.Entidades.Categoria;
import com.pbl3.pbl.Model.Repositorios.RepositorioCategorias;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestesCategoriasRepositorios
{
    @Test
    public void CriarCategoriaTeste()
    {
        // Este teste verifica se as categorias estão sendo criadas corretamente.
        Categoria categoria0 = new Categoria(1, "Ação");
        Categoria categoria1 = new Categoria(2, "Aventura");

        assertNotNull(categoria0);
        assertNotNull(categoria1);

        assertEquals(1, categoria0.getId());
        assertEquals(2, categoria1.getId());

        assertEquals("Ação", categoria0.getGenero());
        assertEquals("Aventura", categoria1.getGenero());
    }

    @Test
    public void VerificarRepositorioCategorias()
    {
        // Este teste verifica se a única instância (Padrão Singleton) do repositório de categorias é criada corretamente.
        assertNotNull(RepositorioCategorias.getInstancia());
        assertNotNull(RepositorioCategorias.getCategorias());

        // Este teste verifica se, ao criar o repositório, as categorias são criadas corretamente.
        for (Categoria categoria : RepositorioCategorias.getCategorias())
        {
            assertNotNull(categoria);
        }
    }

    @Test
    public void TestarRetornarCategoria()
    {
        // Este teste cria o reposotório e verifica se as categorias estão sendo retornadas corretamente.
        assert RepositorioCategorias.getInstancia() != null;
        assert RepositorioCategorias.getCategorias() != null;

        assert RepositorioCategorias.getCategoria(1).getGenero().equals("Ação");
        assert RepositorioCategorias.getCategoria(2).getGenero().equals("Aventura");
    }
}
