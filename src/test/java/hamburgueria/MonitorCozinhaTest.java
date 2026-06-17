package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MonitorCozinhaTest {

    private MonitorCozinha monitor;
    private Pedido pedidoBalcao;
    private Pedido pedidoDelivery;
    private Pedido pedidoCancelado;

    @BeforeEach
    void setUp() {
        monitor = new MonitorCozinha();

        pedidoBalcao = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new HamburguerCarne())
                .build();

        pedidoDelivery = new PedidoBuilder()
                .setTipoPedido("Delivery")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new Refrigerante())
                .build();

        pedidoCancelado = new PedidoBuilder()
                .setTipoPedido("Delivery")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(new BatataFrita())
                .build();
        pedidoCancelado.cancelarPedido();

        // Popula o monitor com um cenário base para todos os testes
        monitor.receberPedido(pedidoBalcao);
        monitor.receberPedido(pedidoCancelado);
        monitor.receberPedido(pedidoDelivery);
    }

    // --- TESTES DE IGNORAR CANCELADOS ---

    @Test
    void deveContarApenasPedidosNaoCanceladosNaCozinha() {
        // Dos 3 inseridos no setUp, 1 está cancelado. Devem sobrar 2.
        List<Pedido> visiveis = monitor.obterPedidosVisiveis();
        assertEquals(2, visiveis.size());
    }

    @Test
    void naoDeveConterOObjetoCanceladoNaListaDaCozinha() {
        List<Pedido> visiveis = monitor.obterPedidosVisiveis();
        assertFalse(visiveis.contains(pedidoCancelado));
    }

    // --- TESTES DE FILTRO DE TIPO ---

    @Test
    void deveContarApenasPedidosDoFiltroBalcao() {
        monitor.setFiltroAtivo("Balcao");
        List<Pedido> visiveis = monitor.obterPedidosVisiveis();

        // Apenas 1 pedido válido do Balcão foi inserido no setUp
        assertEquals(1, visiveis.size());
    }

    @Test
    void deveGarantirQueOItemFiltradoSejaRealmenteDoBalcao() {
        monitor.setFiltroAtivo("Balcao");
        List<Pedido> visiveis = monitor.obterPedidosVisiveis();

        assertEquals("Balcao", visiveis.get(0).getTipoPedido());
    }

    // --- TESTES DE LIMPEZA DE FILTRO ---

    @Test
    void deveMostrarTodosOsPedidosValidosAposLimparOFiltro() {
        monitor.setFiltroAtivo("Balcao"); // Aplica o filtro
        monitor.limparFiltro();           // Remove o filtro

        List<Pedido> visiveis = monitor.obterPedidosVisiveis();

        // Deve voltar a mostrar os 2 pedidos válidos (ignorando o cancelado)
        assertEquals(2, visiveis.size());
    }
}