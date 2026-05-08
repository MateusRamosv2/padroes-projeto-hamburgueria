package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoBuilderTest {

    @Test
    void deveRetornarExcecaoParaPedidoSemPagamento() {
        try {
            PedidoBuilder builder = new PedidoBuilder();
            builder.setTipoPedido("Balcao")
                    .adicionarItem(new HamburguerCarne())
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Forma de pagamento inválida", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaPedidoSemItens() {
        try {
            PedidoBuilder builder = new PedidoBuilder();
            builder.setTipoPedido("Delivery")
                    .setFormaPagamento(new PagamentoPix())
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Pedido sem itens", e.getMessage());
        }
    }

    @Test
    void deveConstruirPedidoDeliveryCompletoEmHarmonia() {

        FormaPagamento pix = new PagamentoPix(); // Bridge
        Item lanche = new Queijo(new Bacon(new HamburguerCarne())); // Decorator
        Cliente cliente = new Cliente("Mateus"); // Observer


        PedidoBuilder builder = new PedidoBuilder();
        Pedido pedido = builder
                .setTipoPedido("Delivery")
                .setFormaPagamento(pix)
                .adicionarItem(lanche)
                .adicionarCliente(cliente)
                .build();


        assertNotNull(pedido);
        assertEquals("Recebido", pedido.getStatus()); // State
        // Cálculo: (25+5+3 = 33) - 10% pix (3.3) = 29.7 + 10.0 taxa = 39.7
        assertEquals(39.7f, pedido.calcularTotalFinal(), 0.01f);

        assertTrue(pedido.gerarResumo().contains("Aguardar o motoboy"));
    }
}