package br.com.cadinho;

import br.com.cadinho.dao.jdbc.dao.ClienteDAO;
import br.com.cadinho.dao.jdbc.dao.IClienteDAO;
import br.com.cadinho.domain.Cliente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void cadastrarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Aline Cadinho");
        Integer qtd = dao.cadastrar(cliente);
        assertEquals(1, qtd);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer qtdDel = dao.excluir(clienteBD);
        assertNotNull(qtdDel);
    }

    @Test
    public void consultarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Aline Cadinho");
        dao.cadastrar(cliente);

        Cliente clienteConsultado = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteConsultado);
        assertEquals(cliente.getId(), clienteConsultado.getId());
        assertEquals(cliente.getCodigo(), clienteConsultado.getCodigo());
        assertEquals(cliente.getNome(), clienteConsultado.getNome());

        dao.excluir(cliente);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("01");
        cliente1.setNome("Aline Cadinho");
        dao.cadastrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("02");
        cliente2.setNome("João Silva");
        dao.cadastrar(cliente2);

        List<Cliente> clientes = dao.buscarTodos();

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertEquals(2, clientes.size());

        assertTrue(clientes.contains(cliente1));
        assertTrue(clientes.contains(cliente2));

        dao.excluir(cliente1);
        dao.excluir(cliente2);
    }

    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Aline Cadinho");
        dao.cadastrar(cliente);

        cliente.setNome("Aline Cadinho Atualizado");
        Integer qtd = dao.atualizar(cliente);
        assertEquals(1, qtd);

        Cliente clienteAtualizado = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteAtualizado);
        assertEquals("Aline Cadinho Atualizado", clienteAtualizado.getNome());

        dao.excluir(cliente);
    }

    @Test
    public void excluirTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Aline Cadinho");
        dao.cadastrar(cliente);

        Integer qtdDel = dao.excluir(cliente);
        assertEquals(1, qtdDel);

        Cliente clienteExcluido = dao.consultar(cliente.getCodigo());
        assertNull(clienteExcluido);
    }
}