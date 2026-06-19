package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoBuilderTest {

    private Pedido pedidoCompleto;

    @BeforeEach
    void setUp() {
        // Garantindo a taxa de entrega configurada para o teste de matemática
        Configuracao.getInstance().setTaxaEntrega(10.0f);

        FormaPagamento pix = new PagamentoPix(); // Bridge
        Item lanche = new Queijo(new Bacon(new HamburguerCarne())); // Decorator
        Cliente cliente = new Cliente("Mateus"); // Observer

        PedidoBuilder builder = new PedidoBuilder();
        pedidoCompleto = builder
                .setTipoPedido("Delivery")
                .setFormaPagamento(pix)
                .adicionarItem(lanche)
                .adicionarCliente(cliente)
                .build();
    }

    // --- TESTES DE VALIDAÇÃO DO BUILDER (EXCEÇÕES COM ASSERTTROWS) ---

    @Test
    void deveLancarExcecaoAoConstruirPedidoSemPagamento() {
        PedidoBuilder builder = new PedidoBuilder();
        builder.setTipoPedido("Balcao")
                .adicionarItem(new HamburguerCarne());

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            builder.build();
        });

        assertEquals("Forma de pagamento inválida", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoAoConstruirPedidoSemItens() {
        PedidoBuilder builder = new PedidoBuilder();
        builder.setTipoPedido("Delivery")
                .setFormaPagamento(new PagamentoPix());

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            builder.build();
        });

        assertEquals("Pedido sem itens", excecao.getMessage());
    }

    // --- TESTES DO PEDIDO CONSTRUÍDO COM SUCESSO (INTEGRAÇÃO DESMEMBRADA) ---

    @Test
    void builderDeveRetornarUmaInstanciaValidaDePedido() {
        assertNotNull(pedidoCompleto);
    }

    @Test
    void pedidoConstruidoDeveIniciarNoEstadoRecebido() {
        // Valida o Pattern State
        assertEquals("Recebido", pedidoCompleto.getStatus());
    }

    @Test
    void pedidoConstruidoDeveCalcularTotalComDescontoPixETaxaDelivery() {
        // Valida a Integração com Strategy e Template Method
        // Cálculo: (25+5+3 = 33) - 10% pix (3.3) = 29.7 + 10.0 taxa = 39.7
        assertEquals(39.7f, pedidoCompleto.calcularTotalFinal(), 0.01f);
    }

    @Test
    void pedidoConstruidoDeveConterInstrucoesLogisticasCorretasNoResumo() {
        // Valida as instruções polimórficas do Delivery
        assertTrue(pedidoCompleto.gerarResumo().contains("Aguardar o motoboy"));
    }

    @Test
    void deveLancarExcecaoAoConstruirPedidoComTipoInvalido() {
        PedidoBuilder builder = new PedidoBuilder();
        builder.setFormaPagamento(new PagamentoPix())
                .adicionarItem(new HamburguerCarne())
                .setTipoPedido("Drone"); // Um tipo que não é Balcao nem Delivery

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            builder.build();
        });
        assertEquals("Tipo de pedido inválido", excecao.getMessage());
    }
}