package br.com.cadinho.dao.jdbc.dao;

import br.com.cadinho.domain.Produto;

import java.util.List;

public interface IProdutoDAO {

    Integer cadastrar(Produto produto) throws Exception;
    Produto consultar(String codigo) throws Exception;
    Integer excluir(Produto produto) throws Exception;
    Integer atualizar(Produto produto) throws Exception;
    List<Produto> buscarTodos() throws Exception;
}
