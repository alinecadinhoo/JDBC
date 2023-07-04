package br.com.cadinho;

import br.com.cadinho.dao.jdbc.dao.IProdutoDAO;
import br.com.cadinho.dao.jdbc.dao.ProdutoDAO;
import br.com.cadinho.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void cadastrarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição do Produto 1");
        produto.setValor(10.0);
        Integer qtd = dao.cadastrar(produto);
        assertEquals(1, qtd);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getDescricao(), produtoBD.getDescricao());
        assertEquals(produto.getValor(), produtoBD.getValor());

        Integer qtdDel = dao.excluir(produtoBD);
        assertNotNull(qtdDel);
    }

    @Test
    public void consultarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição do Produto 1");
        produto.setValor(10.0);
        dao.cadastrar(produto);

        Produto produtoConsultado = dao.consultar(produto.getCodigo());
        assertNotNull(produtoConsultado);
        assertEquals(produto.getId(), produtoConsultado.getId());
        assertEquals(produto.getCodigo(), produtoConsultado.getCodigo());
        assertEquals(produto.getNome(), produtoConsultado.getNome());
        assertEquals(produto.getDescricao(), produtoConsultado.getDescricao());
        assertEquals(produto.getValor(), produtoConsultado.getValor());

        dao.excluir(produto);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto1 = new Produto();
        produto1.setCodigo("01");
        produto1.setNome("Produto 1");
        produto1.setDescricao("Descrição do Produto 1");
        produto1.setValor(10.0);
        dao.cadastrar(produto1);

        Produto produto2 = new Produto();
        produto2.setCodigo("02");
        produto2.setNome("Produto 2");
        produto2.setDescricao("Descrição do Produto 2");
        produto2.setValor(20.0);
        dao.cadastrar(produto2);

        List<Produto> produtos = dao.buscarTodos();

        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
        assertEquals(2, produtos.size());

        assertTrue(produtos.contains(produto1));
        assertTrue(produtos.contains(produto2));

        dao.excluir(produto1);
        dao.excluir(produto2);
    }

    @Test
    public void atualizarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição do Produto 1");
        produto.setValor(10.0);
        dao.cadastrar(produto);

        produto.setNome("Produto 1 Atualizado");
        produto.setValor(15.0);
        Integer qtd = dao.atualizar(produto);
        assertEquals(1, qtd);

        Produto produtoAtualizado = dao.consultar(produto.getCodigo());
        assertNotNull(produtoAtualizado);
        assertEquals("Produto 1 Atualizado", produtoAtualizado.getNome());
        assertEquals(15.0, produtoAtualizado.getValor());

        dao.excluir(produto);
    }

    @Test
    public void excluirTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição do Produto 1");
        produto.setValor(10.0);
        dao.cadastrar(produto);

        Integer qtdDel = dao.excluir(produto);
        assertEquals(1, qtdDel);

        Produto produtoExcluido = dao.consultar(produto.getCodigo());
        assertNull(produtoExcluido);
    }

}
