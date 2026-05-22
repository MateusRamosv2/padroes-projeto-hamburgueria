package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonitorCozinhaTest {

    @Test
    void deveIterarIgnorandoPedidosCancelados() {
        MonitorCozinha monitor = new MonitorCozinha();

        Pedido p1 = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new HamburguerCarne())
                .build();
        monitor.receberPedido(p1);

        Pedido p2 = new PedidoBuilder()
                .setTipoPedido("Delivery")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new BatataFrita())
                .build();
        p2.cancelarPedido();
        monitor.receberPedido(p2);

        Pedido p3 = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new Refrigerante())
                .build();
        monitor.receberPedido(p3);

        int contagemCozinha = 0;
        for (Pedido pedidoVisivel : monitor) {
            contagemCozinha++;
            assertFalse(pedidoVisivel.isCancelado());
        }

        assertEquals(2, contagemCozinha);
    }

    @Test
    void deveIterarFiltrandoApenasPedidosDoBalcao() {
        MonitorCozinha monitor = new MonitorCozinha();


        monitor.receberPedido(new PedidoBuilder().setTipoPedido("Balcao").setFormaPagamento(new PagamentoPix()).adicionarItem(new HamburguerCarne()).build());
        monitor.receberPedido(new PedidoBuilder().setTipoPedido("Delivery").setFormaPagamento(new PagamentoPix()).adicionarItem(new BatataFrita()).build());
        monitor.receberPedido(new PedidoBuilder().setTipoPedido("Delivery").setFormaPagamento(new PagamentoPix()).adicionarItem(new Refrigerante()).build()); // <-- Alterado aqui!
        monitor.receberPedido(new PedidoBuilder().setTipoPedido("Balcao").setFormaPagamento(new PagamentoPix()).adicionarItem(new HamburguerCarne()).build());

        monitor.setFiltroAtivo("Balcao");

        int contagemBalcao = 0;
        for (Pedido pedidoVisivel : monitor) {
            contagemBalcao++;
            assertEquals("Balcao", pedidoVisivel.getTipoPedido());
        }


        assertEquals(2, contagemBalcao);
    }



}