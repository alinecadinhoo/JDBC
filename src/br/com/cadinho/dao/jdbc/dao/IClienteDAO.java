package br.com.cadinho.dao.jdbc.dao;

import br.com.cadinho.domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO {

    public Integer cadastrar(Cliente cliente) throws Exception;

    Integer atualizar(Cliente cliente) throws Exception;

    Cliente consultar(String codigo) throws Exception;

    Integer excluir(Cliente clienteBD) throws Exception;

    List<Cliente> buscarTodos() throws Exception;
}
