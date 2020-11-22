package ufes.com.br.SimpleAPI.factory;

import ufes.com.br.SimpleAPI.collection.PedidoCollection;
import ufes.com.br.SimpleAPI.model.ItemPedido;
import ufes.com.br.SimpleAPI.model.Pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class PedidoFactory {
    private static final AtomicLong counter = new AtomicLong();

    public static Pedido createPedido(ArrayList<ItemPedido> itensPedido) {
       Pedido pedido = new Pedido(LocalDate.now());
       itensPedido.forEach(itemPedido -> pedido.addItem(itemPedido.getProduto(), itemPedido.getQuantidade()));
       pedido.setId(counter.incrementAndGet());

       PedidoCollection.addPedido(pedido);

       return pedido;
    }
}
