package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstadoPedidoTest {


    @Test
    void pedidoNovoDeveIniciarNoEstadoRecebido() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        assertEquals("Recebido", pedido.getStatus());
    }

    @Test
    void pedidoRecebidoDevePermitirAvancarParaEmPreparo() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado();
        assertEquals("Em Preparo", pedido.getStatus());
    }

    @Test
    void pedidoEmPreparoDevePermitirAvancarParaPronto() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Transiciona para Em Preparo
        pedido.avancarEstado(); // Transiciona para Pronto
        assertEquals("Pronto", pedido.getStatus());
    }

    @Test
    void pedidoProntoDevePermitirAvancarParaEntregue() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em Preparo
        pedido.avancarEstado(); // Pronto
        pedido.avancarEstado(); // Entregue
        assertEquals("Entregue", pedido.getStatus());
    }


    @Test
    void pedidoRecebidoDevePermitirCancelamentoDireto() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.cancelarPedido();
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    void pedidoEmPreparoDevePermitirCancelamento() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em Preparo
        pedido.cancelarPedido(); // Cancelado no meio do processo
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    void pedidoProntoDevePermitirCancelamentoAntesDaRetirada() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em Preparo
        pedido.avancarEstado(); // Pronto
        pedido.cancelarPedido(); // Cancelado na estufa
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    void naoDevePermitirAvancarEstadoAposPedidoSerEntregue() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em Preparo
        pedido.avancarEstado(); // Pronto
        pedido.avancarEstado(); // Entregue (Estado Terminal)

        pedido.avancarEstado(); // Tentativa ilegal de avanço
        assertEquals("Entregue", pedido.getStatus());
    }

    @Test
    void naoDevePermitirCancelarPedidoAposEstarEntregue() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em Preparo
        pedido.avancarEstado(); // Pronto
        pedido.avancarEstado(); // Entregue (Estado Terminal)

        pedido.cancelarPedido(); // Tentativa ilegal de cancelamento
        assertEquals("Entregue", pedido.getStatus());
    }

    @Test
    void naoDevePermitirAvancarEstadoAposPedidoSerCancelado() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.cancelarPedido(); // Cancelado (Estado Terminal)

        pedido.avancarEstado(); // Tentativa ilegal de produzir lanche cancelado
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    void naoDevePermitirCancelarUmPedidoQueJaEstaCancelado() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.cancelarPedido(); // Cancelado (Estado Terminal)

        pedido.cancelarPedido(); // Tentativa dupla de cancelamento
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    void naoDevePermitirAvancarEstadoAposPedidoSofrerDevolucao() {
        Pedido pedido = new PedidoBalcao(new PagamentoPix());
        pedido.devolverPedido(); // Devolução (Estado Terminal)

        pedido.avancarEstado(); // Tentativa ilegal de avançar um estorno
        assertEquals("Devolução", pedido.getStatus());
    }

    @Test
    void naoDevePermitirCancelarPedidoQueJaEstaEmDevolucao() {
        Pedido pedido = new PedidoBalcao(new PagamentoPix());
        pedido.devolverPedido(); // Devolução (Estado Terminal)

        pedido.cancelarPedido(); // Tentativa ilegal de cancelar algo devolvido
        assertEquals("Devolução", pedido.getStatus());
    }
}