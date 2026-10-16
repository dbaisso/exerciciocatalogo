package br.com.examplo.catalogo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query("SELECT p FROM Produto p WHERE p.preco BETWEEN :min AND :max")
    List<Produto> buscarProdutosPorFaixaDePreco(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
