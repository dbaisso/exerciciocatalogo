package br.com.examplo.catalogo.application.dto;

import br.com.examplo.catalogo.domain.Categoria;
import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoResponse {

    private UUID id;

    private String sku;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Categoria categoria;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ProdutoResponse(String sku, String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
}
