package ufes.com.br.SimpleAPI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ufes.com.br.SimpleAPI.collection.ProdutoCollection;
import ufes.com.br.SimpleAPI.factory.ProdutoFactory;

@WebMvcTest
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void afterEach() {
        ProdutoCollection.getProdutos().clear();
    }

    @DisplayName("CT01 - Deve retornar uma lista vazia")
    @Test
    public void CT01() throws Exception {
        this.mockMvc.perform(get("/produtos")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @DisplayName("CT02 - Deve retornar um produto na lista")
    @Test
    public void CT02() throws Exception {
        ProdutoFactory.createProduto("Arroz", "saco", 20, 10, "10 kg de Arroz");
        this.mockMvc.perform(get("/produtos")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"nome\":\"Arroz\",\"tipoUnidade\":\"saco\",\"preco\":20.0,\"estoque\":10.0,\"descricao\":\"10 kg de Arroz\"}]"));

    }

}
