package br.com.examplo.catalogo.application;

import br.com.examplo.catalogo.application.dto.ProdutoRequest;
import br.com.examplo.catalogo.application.dto.ProdutoResponse;
import br.com.examplo.catalogo.domain.Produto;
import br.com.examplo.catalogo.domain.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Injeção via construtor (boa prática)
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutosPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoResponse criarProduto(@Valid ProdutoRequest request) {
        Produto produto = new Produto(request.getSku(), request.getNome(), request.getDescricao(),request.getPreco(),request.getCategoria());
        var response = produtoRepository.save(produto);
        
    }
}
