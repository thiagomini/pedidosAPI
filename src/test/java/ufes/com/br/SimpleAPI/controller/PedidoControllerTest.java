package ufes.com.br.SimpleAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ufes.com.br.SimpleAPI.collection.ProdutoCollection;
import ufes.com.br.SimpleAPI.factory.ProdutoFactory;
import ufes.com.br.SimpleAPI.model.Produto;

@WebMvcTest
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @BeforeAll
    static void limparListaProdutos() {
        ProdutoCollection.getProdutos().clear();
    }

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @DisplayName("CT01 - Deve retornar uma lista vazia")
    @Test
    void CT01() throws Exception {
        this.mockMvc.perform(get("/pedidos")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @DisplayName("CT02 - Deve retornar erro NOT FOUND")
    @Test
    void CT02() throws Exception {
        this.mockMvc.perform(get("/pedidos/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("CT03 - Deve cadastrar um pedido corretamente")
    @Test
    void CT03() throws Exception {
        Produto novoProduto = ProdutoFactory.createProduto("Leite","LT", 3.49, 10, "Leite");
        this.mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"idProduto\": "+novoProduto.getId()+", \"quantidade\": 5}]"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
