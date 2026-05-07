package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OuvidoriaTest {

    @Test
    void deveElogiarCozinhaViaOuvidoria() {
        Cliente cliente = new Cliente("Mateus");
        String resposta = cliente.elogiarCozinha("Hambúrguer muito suculento");

        assertTrue(resposta.contains("Ouvidoria: Recebemos seu contato."));
        assertTrue(resposta.contains("A Cozinha agradece o elogio"));
    }

    @Test
    void deveReclamarAdministracaoViaOuvidoria() {
        Cliente cliente = new Cliente("Mateus");
        String resposta = cliente.reclamarAdministracao("Preços muito elevados");

        assertTrue(resposta.contains("Ouvidoria: Recebemos seu contato."));
        assertTrue(resposta.contains("A Administração lamenta o ocorrido"));
    }

    @Test
    void deveManterFuncionamentoDosOutrosPadroes() {
        // Teste de fumaça: Garantir que adicionar Mediator não quebrou o State/Observer
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        Cliente cliente = new Cliente("Mateus");
        pedido.addObservador(cliente);

        pedido.avancarEstado(); // Em Preparo
        assertEquals("Mateus, seu pedido está: Em Preparo", cliente.getNotificacoes().get(0));

        // E o cliente ainda consegue usar a ouvidoria normalmente
        String feedback = cliente.elogiarAdministracao("Ambiente limpo");
        assertNotNull(feedback);
    }

    @Test
    void deveReclamarCozinhaViaOuvidoria() {
        Cliente cliente = new Cliente("Mateus");
        String resposta = cliente.reclamarCozinha("O hambúrguer passou do ponto");

        assertTrue(resposta.contains("Ouvidoria: Recebemos seu contato."));
        assertTrue(resposta.contains("A Cozinha revisará o preparo"));
    }

}