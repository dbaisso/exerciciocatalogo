//Implemente a entidade Produto no pacote domain e o enum Categoria. >>>ok
//O mapeamento precisa passar na validação do Hibernate contra a tabela criada no Passo 3. >>>task

package br.com.examplo.catalogo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "produto") // Defina o nome exato da sua tabela aqui
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Use IDENTITY para auto-incremento
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

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

    //O JPA começa a gerenciar esse campo de versão do banco de dados depois de aplicado essa notação @Version
    @Version
    @Column(name = "versao", nullable = false)
    private int version;

    @NotNull
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @NotNull
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @PrePersist // Usado para adicionar a data na hora de ocorrer a ação e não apenas quando acontece alteração no banco, mas sim no objeto
    protected void antesDeInserir() {
        LocalDateTime agora = LocalDateTime.now();

        this.criadoEm = agora;
        this.atualizadoEm = agora;
    }
    @PreUpdate // Mesma coisa do PrePersist, mas para o update, e assim deixa o primeiro registro de criação intacto e altera apenas novas atualizações
    protected void antesDeAtualizar() {
        this.atualizadoEm = LocalDateTime.now();
    }


    // Construtor padrão obrigatório pelo Hibernate,UseI protected no construtor sem argumentos
    protected Produto() {
    }

    public Produto(String sku, String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    // Getters e Setters
    public UUID getId() {

        return id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
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