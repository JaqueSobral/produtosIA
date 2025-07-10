package com.produtosdb.ai.controller;

import com.produtosdb.ai.model.Produto;
import com.produtosdb.ai.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        if (produto.getNome() == null || produto.getDataCriacao() == null || produto.getQuantidadeDisponivel() == null) {
            return ResponseEntity.badRequest().body(
                    "{ \"message\": \"Os campos nome, dataCriacao e quantidadeDisponivel são obrigatórios.\" }"
            );
        }
        Produto novo = service.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        Optional<Produto> produto = service.buscarProdutoPorId(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{ \"message\": \"Produto não encontrado\" }");
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto(@PathVariable Long id) {
        boolean removido = service.removerProduto(id);
        if (removido) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{ \"message\": \"Produto não encontrado\" }");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        if (produto.getNome() == null || produto.getDataCriacao() == null || produto.getQuantidadeDisponivel() == null) {
            return ResponseEntity.badRequest().body(
                    "{ \"message\": \"Os campos nome, dataCriacao e quantidadeDisponivel são obrigatórios.\" }"
            );
        }
        Produto atualizado = service.atualizarProduto(id, produto);
        if (atualizado == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{ \"message\": \"Produto não encontrado\" }");
        return ResponseEntity.ok(atualizado);
    }
}