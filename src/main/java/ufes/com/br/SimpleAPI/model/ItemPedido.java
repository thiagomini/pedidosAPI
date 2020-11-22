package ufes.com.br.SimpleAPI.model;

import java.text.DecimalFormat;

public class ItemPedido {
    protected double valorUnitario;
    protected double quantidade;
    protected double valorItem;
    protected Produto produto;

    public void setQuantidade(double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("quantidade adquirida deve ser positiva!");
        }
        this.quantidade = quantidade;
    }

    public ItemPedido(Produto produto, double quantidadeAdquirida) {
        this.produto = produto;
        setQuantidade(quantidadeAdquirida);
        this.valorUnitario = produto.getPreco();
        calculaValorItem();
    }

    private void calculaValorItem() {
        this.valorItem = valorUnitario * quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getValorItem() {
        calculaValorItem();
        return valorItem;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return produto.getNome()
                + ", valor unitário: R$" + df.format(valorUnitario)
                + ", quantidade no pedido: " + quantidade
                + ", valor total: R$" + df.format(getValorItem());
    }
}
