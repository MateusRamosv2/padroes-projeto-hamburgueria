package hamburgueria;

import hamburgueria.bridge.*;
import hamburgueria.core.*;
import hamburgueria.decorator.*;
import hamburgueria.factory.*;
import hamburgueria.singleton.*;
import hamburgueria.observer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HamburgueriaArquiteturaTest {

    @BeforeEach
    void setup() {

        Configuracao.getInstance().setTaxaEntrega(10.0f);
    }

    @Test
    void deveGarantirInstanciaUnicaSingleton() {
        Configuracao conf1 = Configuracao.getInstance();
        Configuracao conf2 = Configuracao.getInstance();
        assertSame(conf1, conf2);
    }

    @Test
    void deveDecorarHamburguerCorretamente() {
        // Carne(25) + Bacon(5) + Queijo(3) = 33
        Item lanche = new Queijo(new Bacon(new HamburguerCarne()));
        assertEquals(33.0f, lanche.getPreco());
        assertEquals("Hambúrguer de Carne + Bacon + Queijo", lanche.getDescricao());
    }

    @Test
    void deveCriarFamiliaComboTradicional() {
        FabricaCombo fabrica = new FabricaComboTradicional();
        Item lanche = fabrica.criarHamburguer();
        Item bebida = fabrica.criarBebida();

        assertEquals("Hambúrguer de Carne", lanche.getDescricao());
        assertEquals("Refrigerante Cola", bebida.getDescricao());
    }

    @Test
    void deveAvancarEstadosSemIfs() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        assertEquals("Recebido", pedido.getStatus());

        pedido.avancarEstado();
        assertEquals("Em Preparo", pedido.getStatus());

        pedido.avancarEstado();
        assertEquals("Pronto", pedido.getStatus());

        pedido.avancarEstado();
        assertEquals("Entregue", pedido.getStatus());
    }

    @Test
    void naoDeveAvancarEstadoAposCancelado() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.cancelarPedido();
        assertEquals("Cancelado", pedido.getStatus());

        pedido.avancarEstado(); // Tentativa de avanço
        assertEquals("Cancelado", pedido.getStatus()); // Continua cancelado
    }

    @Test
    void deveCalcularPedidoBalcaoComCartao() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.adicionarItem(new HamburguerCarne()); // 25
        pedido.adicionarItem(new Bacon(new HamburguerCarne())); // 30


        assertEquals(55.0f, pedido.calcularTotalFinal());
    }

    @Test
    void deveCalcularPedidoDeliveryComPix() {
        Pedido pedido = new PedidoDelivery(new PagamentoPix());
        pedido.adicionarItem(new HamburguerVegano()); // 28


        assertEquals(35.2f, pedido.calcularTotalFinal(), 0.01f);
    }

    @Test
    void deveIntegrarTodosOsPadroesSimultaneamente() {

        Configuracao.getInstance().setTaxaEntrega(15.0f);


        FabricaCombo fabrica = new FabricaComboTradicional();
        Item hamburguerBase = fabrica.criarHamburguer();


        Item hamburguerTurbinado = new Queijo(new Bacon(hamburguerBase));


        Pedido pedido = new PedidoDelivery(new PagamentoCartao());
        pedido.adicionarItem(hamburguerTurbinado);
        pedido.adicionarItem(fabrica.criarAcompanhamento()); // 10
        pedido.adicionarItem(fabrica.criarBebida()); // 8

        // Subtotal Itens: 33 + 10 + 8 = 51. Delivery: +15. Total = 66
        assertEquals(66.0f, pedido.calcularTotalFinal());


        pedido.avancarEstado(); // Prepara
        pedido.avancarEstado(); // Pronto
        pedido.avancarEstado(); // Entregue
        assertEquals("Entregue", pedido.getStatus());
    }



    @Test
    void deveNotificarClienteSobreStatusDoPedido() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        Cliente cliente = new Cliente("João");
        pedido.addObservador(cliente);

        pedido.avancarEstado(); // Em Preparo
        assertEquals("João, seu pedido está: Em Preparo", cliente.getNotificacoes().get(0));

        pedido.avancarEstado(); // Pronto
        assertEquals("João, seu pedido está: Pronto", cliente.getNotificacoes().get(1));

        pedido.avancarEstado(); // Entregue
        assertEquals("João, seu pedido está: Entregue", cliente.getNotificacoes().get(2));
    }

    @Test
    void deveNotificarVariosClientesSobreStatusDoPedido() {
        Pedido pedido = new PedidoDelivery(new PagamentoPix());
        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");
        pedido.addObservador(cliente1);
        pedido.addObservador(cliente2);

        pedido.avancarEstado(); // Em Preparo
        assertEquals("João, seu pedido está: Em Preparo", cliente1.getNotificacoes().getFirst());
        assertEquals("Maria, seu pedido está: Em Preparo", cliente2.getNotificacoes().getFirst());
    }

    @Test
    void naoDeveNotificarClienteNaoInscrito() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        Cliente cliente = new Cliente("João");
        // Cliente NÃO adicionado como observador

        pedido.avancarEstado();
        assertTrue(cliente.getNotificacoes().isEmpty());
    }

    @Test
    void deveNotificarCancelamentoDoPedido() {
        Pedido pedido = new PedidoDelivery(new PagamentoCartao());
        Cliente cliente = new Cliente("João");
        pedido.addObservador(cliente);

        pedido.cancelarPedido();
        assertEquals("João, seu pedido está: Cancelado", cliente.getNotificacoes().getFirst());
    }

    @Test
    void deveNotificarDevolucaoDoPedido() {
        Pedido pedido = new PedidoBalcao(new PagamentoPix());
        Cliente cliente = new Cliente("João");
        pedido.addObservador(cliente);

        pedido.devolverPedido();
        assertEquals("João, confirmamos a devolução do seu pedido.", cliente.getNotificacoes().getFirst());
    }

    @Test
    void deveMudarEstadoAposDevolucao() {
        Pedido pedido = new PedidoBalcao(new PagamentoPix());
        pedido.devolverPedido();
        assertEquals("Devolução", pedido.getStatus());

        pedido.avancarEstado(); // Tentativa de avanço
        assertEquals("Devolução", pedido.getStatus()); // Continua em devolução
    }
}