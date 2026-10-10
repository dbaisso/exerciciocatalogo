//Implemente a entidade Produto no pacote domain e o enum Categoria. >>>ok
//O mapeamento precisa passar na validação do Hibernate contra a tabela criada no Passo 3. >>>task

package br.com.examplo.catalogo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto") // Defina o nome exato da sua tabela aqui
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY para auto-incremento (MySQL, Postgres, etc.)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    @Column(name = "descricao", length = 255)
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "O preço deve ser maior ou igual a zero")
    @Column(name = "preco", nullable = false, precision = 12, scale = 2) // Mapeamento para campos DECIMAL/NUMERIC
    private BigDecimal preco;

    @NotNull(message = "A categoria é obrigatória")
    @Enumerated(EnumType.STRING) // Salva o nome do Enum como String no banco (VARCHAR)
    @Column(name = "categoria", nullable = false, length = 50)
    private Categoria categoria;

    // Construtor padrão obrigatório pelo Hibernate
    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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

    // Equals e HashCode baseados no ID para o bom funcionamento do JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}