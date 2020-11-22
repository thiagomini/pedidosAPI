package ufes.com.br.SimpleAPI.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ufes.com.br.SimpleAPI.collection.PedidoCollection;
import ufes.com.br.SimpleAPI.collection.ProdutoCollection;
import ufes.com.br.SimpleAPI.dto.create.ItemPedidoCreateDTO;
import ufes.com.br.SimpleAPI.error.ResourceNotFoundException;
import ufes.com.br.SimpleAPI.factory.PedidoFactory;
import ufes.com.br.SimpleAPI.model.ItemPedido;
import ufes.com.br.SimpleAPI.model.Pedido;
import ufes.com.br.SimpleAPI.model.Produto;

import java.util.ArrayList;
import java.util.Optional;
import java.util.TreeSet;

@RestController
public class PedidoController {

    @PostMapping("/pedidos")
    public Pedido createPedido(@RequestBody(required = true) ItemPedidoCreateDTO[] createDTOs) {
        ArrayList<ItemPedido> itensPedido = new ArrayList<>();

        for(ItemPedidoCreateDTO createDTO : createDTOs) {
            Optional<Produto> produtoEscolhido = ProdutoCollection.getProduto(createDTO.getIdProduto());
            if (produtoEscolhido.isEmpty()) {
                throw new ResourceNotFoundException("Produto de id" + createDTO.getIdProduto() + " não foi encontrado!");
            }

            if (!produtoEscolhido.get().estoqueDisponivel(createDTO.getQuantidade())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade insuficiente do produto " + createDTO.getIdProduto());
            }
            produtoEscolhido.get().retirarDoEstoque(createDTO.getQuantidade());
            itensPedido.add(new ItemPedido(produtoEscolhido.get(), createDTO.getQuantidade()));
        }

        return PedidoFactory.createPedido(itensPedido);
    }

    @GetMapping("/pedidos/{id}")
    public Pedido getPedido(@PathVariable long id) {
        Optional<Pedido> pedido = PedidoCollection.getPedido(id);
        if (pedido.isEmpty()) {
            throw new ResourceNotFoundException("Pedido não encontrado!");
        }
        return pedido.get();
    }

    @GetMapping("/pedidos")
    public TreeSet<Pedido> listPedidos() {
        return PedidoCollection.getPedidos();
    }

    @DeleteMapping("/pedidos/{id}")
    public Pedido deletePedido(@PathVariable long id) {
        Optional<Pedido> pedidoOptional = PedidoCollection.getPedido(id);
        if (pedidoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Pedido não encontrado!");
        }
        Pedido pedido = pedidoOptional.get();
        PedidoCollection.deletePedido(pedido);
        return pedido;
    }


}
