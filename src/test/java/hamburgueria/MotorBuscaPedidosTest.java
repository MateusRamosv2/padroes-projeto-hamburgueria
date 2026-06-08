package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MotorBuscaPedidosTest {

    private Pedido pedidoCaroDelivery;
    private Pedido pedidoBaratoBalcao;

    @BeforeEach
    void setUp() {

        FormaPagamento cartao = new PagamentoCartao();

        pedidoCaroDelivery = new PedidoBuilder()
                .setTipoPedido("Delivery")
                .setFormaPagamento(cartao)
                .adicionarItem(new HamburguerCarne())
                .adicionarItem(new HamburguerCarne())
                .build();

        pedidoBaratoBalcao = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(cartao)
                .adicionarItem(new BatataFrita())
                .build();
    }

    @Test
    void deveEncontrarPedidoDeliveryCaro() {

        MotorBuscaPedidos motor = new MotorBuscaPedidos("TIPO Delivery E VALOR_MAIOR_QUE 40");

        assertTrue(motor.interpretar(pedidoCaroDelivery));
        assertFalse(motor.interpretar(pedidoBaratoBalcao));
    }

    @Test
    void deveEncontrarPedidosPorRegraAlternativa() {

        MotorBuscaPedidos motor = new MotorBuscaPedidos("TIPO Balcao OU VALOR_MAIOR_QUE 100");

        assertTrue(motor.interpretar(pedidoBaratoBalcao));
        assertFalse(motor.interpretar(pedidoCaroDelivery));
    }


}