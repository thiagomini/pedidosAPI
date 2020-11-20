package ufes.com.br.SimpleAPI.collection;

import ufes.com.br.SimpleAPI.model.Produto;

import java.util.Optional;
import java.util.TreeSet;

public class ProdutoCollection {

    private static final TreeSet<Produto> PRODUTOS = new TreeSet<>();

    public static void addProduto(Produto produto) {
        PRODUTOS.add(produto);
    }

    public static TreeSet<Produto> getProdutos() {
        return PRODUTOS;
    }

    public static Optional<Produto> getProduto(long id) {
        return PRODUTOS
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

}
