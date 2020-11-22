package ufes.com.br.SimpleAPI.dto.create;

import ufes.com.br.SimpleAPI.model.Produto;

public class ItemPedidoCreateDTO {

    protected double valorUnitario;
    protected double quantidade;

    public ItemPedidoCreateDTO(double quantidade, long idProduto) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
    }

    protected long idProduto;

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }
}
