package ufes.com.br.SimpleAPI.controller;

import org.springframework.web.bind.annotation.*;
import ufes.com.br.SimpleAPI.collection.ProdutoCollection;
import ufes.com.br.SimpleAPI.dto.create.ProdutoCreateDTO;
import ufes.com.br.SimpleAPI.error.ResourceNotFoundException;
import ufes.com.br.SimpleAPI.factory.ProdutoFactory;
import ufes.com.br.SimpleAPI.model.Produto;

import java.util.Optional;
import java.util.TreeSet;

@RestController
public class ProdutoController {

    @PostMapping("/produtos")
    public Produto createProduto(
            @RequestBody(required = true) ProdutoCreateDTO createDTO) {
        return ProdutoFactory.createProduto(
                createDTO.getNome(),
                createDTO.getTipoUnidade(),
                createDTO.getPreco(),
                createDTO.getEstoque(),
                createDTO.getDescricao()
        );
    }

    @GetMapping("/produtos")
    public TreeSet<Produto> listProdutos() {
        return ProdutoCollection.getProdutos();
    }

    @GetMapping("/produtos/{id}")
    public Produto getProduto(@PathVariable long id) {
        Optional<Produto> produtoOptional = ProdutoCollection.getProduto(id);
        if (!produtoOptional.isPresent()) {
            throw new ResourceNotFoundException("Produto não encontrado!");
        }

        return produtoOptional.get();
    }
}
