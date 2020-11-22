package ufes.com.br.SimpleAPI.model;

public class Produto extends AbstractModel {
    private long id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
    private String tipoUnidade;
    private double preco;
    private double estoque;
    private String descricao;

    public Produto(long id, String nome, String tipoUnidade, double preco, double estoque, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
    }

    public Produto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
