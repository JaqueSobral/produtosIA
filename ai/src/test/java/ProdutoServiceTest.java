import com.produtosdb.ai.model.Produto;
import com.produtosdb.ai.repository.ProdutoRepository;
import com.produtosdb.ai.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Produto getProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto XPTO");
        produto.setDataCriacao(LocalDate.now());
        produto.setQuantidadeDisponivel(10);
        return produto;
    }

    @Test
    void deveSalvarProduto() {
        Produto produto = getProduto();
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto salvo = produtoService.criarProduto(produto);

        assertNotNull(salvo);
        assertEquals(produto.getNome(), salvo.getNome());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void deveRetornarTodosProdutos() {
        List<Produto> produtos = Arrays.asList(getProduto());
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoService.listarProdutos();
        assertThat(result).hasSize(1);
    }

    @Test
    void deveBuscarProdutoPorId() {
        Produto produto = getProduto();
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> encontrado = produtoService.buscarProdutoPorId(1L);
        assertTrue(encontrado.isPresent());
        assertEquals(produto.getNome(), encontrado.get().getNome());
    }

    @Test
    void deveAtualizarProduto() {
        Produto produto = getProduto();
        Produto atualizado = getProduto();
        atualizado.setNome("Novo Nome");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(atualizado);

        Produto retorno = produtoService.atualizarProduto(1L, atualizado);

        assertNotNull(retorno);
        assertEquals(atualizado.getNome(), retorno.getNome());
    }

    @Test
    void deveRemoverProduto() {
        when(produtoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(1L);

        boolean removido = produtoService.removerProduto(1L);
        assertTrue(removido);
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    void removerProdutoDeveRetornarFalseSeNaoExistir() {
        when(produtoRepository.existsById(999L)).thenReturn(false);

        boolean removido = produtoService.removerProduto(999L);
        assertFalse(removido);
        verify(produtoRepository, never()).deleteById(999L);
    }
}