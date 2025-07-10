package com.produtosdb.ai.service;

import com.produtosdb.ai.model.Produto;
import com.produtosdb.ai.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criarProduto(Produto produto) {
        return repository.save(produto);
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return repository.findById(id);
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public boolean removerProduto(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDataCriacao(produtoAtualizado.getDataCriacao());
                    produto.setQuantidadeDisponivel(produtoAtualizado.getQuantidadeDisponivel());
                    return repository.save(produto);
                })
                .orElse(null);
    }
}