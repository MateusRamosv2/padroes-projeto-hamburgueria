package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoFacadeTest {

    @Test
    void deveRetornarPendenciaDeEstoque() {
        // Criando o pedido usando o BUILDER (Harmonia!)
        Pedido pedido = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new HamburguerCarne())
                .build();

        Estoque.getInstancia().addPedidoPendente(pedido);

        // O Facade vai barrar
        assertFalse(pedido.podeSerPreparado());
    }

    @Test
    void deveRetornarPendenciaLogistica() {
        Pedido pedido = new PedidoBuilder()
                .setTipoPedido("Delivery")
                .setFormaPagamento(new PagamentoCartao())
                .adicionarItem(new Queijo(new HamburguerCarne())) // DECORATOR (Harmonia!)
                .build();

        Logistica.getInstancia().addPedidoPendente(pedido);

        // O Facade vai barrar
        assertFalse(pedido.podeSerPreparado());
    }

    @Test
    void deveAutorizarPreparoSemPendencias() {
        Pedido pedido = new PedidoBuilder()
                .setTipoPedido("Delivery")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new Bacon(new HamburguerCarne()))
                .build();

        // Como não adicionamos esse pedido em nenhuma lista de pendência (Estoque, Financeiro ou Logística),
        // a Fachada vai liberar o preparo!
        assertTrue(pedido.podeSerPreparado());
    }
}