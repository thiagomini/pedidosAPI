package ufes.com.br.SimpleAPI.factory;

import ufes.com.br.SimpleAPI.collection.ProdutoCollection;
import ufes.com.br.SimpleAPI.model.Produto;

import java.util.concurrent.atomic.AtomicLong;

public class ProdutoFactory {
    private static final AtomicLong counter = new AtomicLong();

    public static Produto createProduto(String nome, String tipoUnidade, double preco, double estoque, String descricao) {
        Produto novoProduto = new Produto(counter.incrementAndGet(), nome, tipoUnidade, preco, estoque, descricao);
        ProdutoCollection.addProduto(novoProduto);
        return novoProduto;
    }

}
