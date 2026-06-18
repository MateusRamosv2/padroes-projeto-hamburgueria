package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OuvidoriaTest {

    private Cliente clientePadrao;

    @BeforeEach
    void setUp() {
        // Criado apenas uma vez para todos os testes
        clientePadrao = new Cliente("Mateus");
    }

    // --- TESTES DE ELOGIO À COZINHA ---

    @Test
    void elogioACozinhaDeveSerInterceptadoPelaOuvidoria() {
        String resposta = clientePadrao.elogiarCozinha("Hambúrguer muito suculento");
        // Verifica apenas se o Mediator (Ouvidoria) envelopou a mensagem
        assertTrue(resposta.contains("Ouvidoria: Recebemos seu contato."));
    }

    @Test
    void elogioACozinhaDeveSerRespondidoPeloSetorCozinha() {
        String resposta = clientePadrao.elogiarCozinha("Hambúrguer muito suculento");
        // Verifica apenas a resposta do Colleague (Cozinha)
        assertTrue(resposta.contains("A Cozinha agradece o elogio"));
    }

    // --- TESTES DE RECLAMAÇÃO À ADMINISTRAÇÃO ---

    @Test
    void reclamacaoAAdministracaoDeveSerInterceptadaPelaOuvidoria() {
        String resposta = clientePadrao.reclamarAdministracao("Preços muito elevados");
        assertTrue(resposta.contains("Ouvidoria: Recebemos seu contato."));
    }

    @Test
    void reclamacaoAAdministracaoDeveSerRespondidaPeloSetorAdministracao() {
        String resposta = clientePadrao.reclamarAdministracao("Preços muito elevados");
        assertTrue(resposta.contains("A Administração lamenta o ocorrido"));
    }

    // --- TESTE DE RECLAMAÇÃO À COZINHA ---

    @Test
    void reclamacaoACozinhaDeveSerRespondidaPeloSetorCozinha() {
        String resposta = clientePadrao.reclamarCozinha("O hambúrguer passou do ponto");
        assertTrue(resposta.contains("A Cozinha revisará o preparo"));
    }

    // --- TESTES DE INTEGRAÇÃO (MEDIATOR + OBSERVER) ---

    @Test
    void clienteDeveConseguirReceberNotificacaoDeEstadoIndependentementeDaOuvidoria() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.addObservador(clientePadrao);

        pedido.avancarEstado(); // Em Preparo

        // Garante que o Observer do Cliente funciona
        assertEquals("Mateus, seu pedido está: Em Preparo", clientePadrao.getNotificacoes().getFirst());
    }

    @Test
    void clienteDeveConseguirUsarOuvidoriaMesmoEstandoAtreladoAumPedido() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.addObservador(clientePadrao);

        // Garante que o Mediator funciona em paralelo com o Observer
        String feedback = clientePadrao.elogiarAdministracao("Ambiente limpo");
        assertNotNull(feedback);
    }
}