package ufes.com.br.SimpleAPI.collection;

import ufes.com.br.SimpleAPI.model.Pedido;
import java.util.Optional;
import java.util.TreeSet;

public class PedidoCollection {


    private static final TreeSet<Pedido> PEDIDOS = new TreeSet<>();

    public static void addPedido(Pedido pedido) {
        PEDIDOS.add(pedido);
    }

    public static TreeSet<Pedido> getPedidos() {
        return PEDIDOS;
    }

    public static Optional<Pedido> getPedido(long id) {
        return PEDIDOS
                .stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst();
    }

    public static void deletePedido(Pedido pedido) {
        PEDIDOS.remove(pedido);
    }

    public static void clear() {
        PEDIDOS.clear();
    }
}
