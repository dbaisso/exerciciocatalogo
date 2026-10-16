package br.com.examplo.catalogo.application.controller;

import br.com.examplo.catalogo.application.ProdutoService;
import br.com.examplo.catalogo.application.dto.ProdutoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<ProdutoRequest> salvar(@RequestBody @Valid ProdutoRequest request){
        var produtos = service.criarProduto(request);

        return ResponseEntity.ok(request);
    }

}