package ufes.com.br.SimpleAPI.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void CT001() {
        Produto produto = new Produto(1);
        Produto produto2 = new Produto(1);

        assertEquals(produto, produto2);
    }

}