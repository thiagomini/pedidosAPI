package ufes.com.br.SimpleAPI.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Pedido extends AbstractModel{

    protected double valor;
    protected static final double DESCONTO = 0.05;
    protected double valorDesconto;
    protected double valorAPagar;
    protected final ArrayList<ItemPedido> itens = new ArrayList<>();
    protected LocalDate data;


    public Pedido(Produto produto, double quantidade, LocalDate data) {
        this.data = data;
        this.addItem(produto, quantidade);
    }

    public Pedido(LocalDate data) {
        this.data = data;
    }

    public final void addItem(Produto produto, double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Informe uma quantidade válida!");
        }
        if (this.getItemPorNome(produto.getNome()).isPresent()) {
            throw new IllegalArgumentException("Produto já existe! Remova-o ou altere a quantidade");
        }
        itens.add(new ItemPedido(produto, quantidade));
        calcularValor();
    }

    protected Optional<ItemPedido> getItemPorNome(String nomeProduto) {
        Optional<ItemPedido> itemEncontrado = Optional.empty();
        for (ItemPedido item : itens) {
            if (item.getProduto().getNome().equalsIgnoreCase(nomeProduto)) {
                itemEncontrado = Optional.of(item);
            }
        }
        return itemEncontrado;
    }

    private void calcularValor() {
        valor = 0;
        for (ItemPedido item : itens) {
            valor += item.getValorItem();
        }
        aplicarDesconto();
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    private void aplicarDesconto() {
        this.valorDesconto = valor * DESCONTO;
        this.valorAPagar = valor - valorDesconto;
    }

    public void removerItem(String nomeProduto) {

        Optional<ItemPedido> produtoEncontrado = getItemPorNome(nomeProduto);
        if (!produtoEncontrado.isPresent()) {
            throw new IllegalArgumentException("Item " + nomeProduto + " não encontrado");
        }

        itens.remove(produtoEncontrado.get());
        calcularValor();
    }

    public LocalDate getData() {
        return data;
    }


    public double getValor() {
        return valor;
    }

    public double getDesconto() {
        return DESCONTO;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }


    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder retorno = new StringBuilder("--------------- Pedido --------------\n");
        retorno.append("Data: ").append(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(", ");
        retorno.append("Valor sem desconto: R$").append(df.format(getValor())).append("\n");
        retorno.append("Desconto: R$").append(df.format(valorDesconto)).append(" (").append(DESCONTO * 100).append("%)\n");
        retorno.append("Valor a pagar: R$").append(df.format(valorAPagar)).append("\n");
        retorno.append("Itens do pedido:\n");
        for (ItemPedido item : itens) {
            retorno.append("\t- ").append(item.toString()).append("\n");
        }

        return retorno.toString().trim();
    }
}
