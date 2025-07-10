package com.produtosdb.ai.repository;

import com.produtosdb.ai.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}