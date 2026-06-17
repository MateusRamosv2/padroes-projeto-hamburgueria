package hamburgueria;

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

    // --- DECORATOR DESMEMBRADO ---

    @Test
    void deveCalcularPrecoDoHamburguerDecorado() {
        Item lanche = new Queijo(new Bacon(new HamburguerCarne()));
        // Carne(25) + Bacon(5) + Queijo(3) = 33
        assertEquals(33.0f, lanche.getPreco());
    }

    @Test
    void deveGerarDescricaoDoHamburguerDecorado() {
        Item lanche = new Queijo(new Bacon(new HamburguerCarne()));
        assertEquals("Hambúrguer de Carne + Bacon + Queijo", lanche.getDescricao());
    }

    // --- FACTORY DESMEMBRADO ---

    @Test
    void deveCriarHamburguerPelaFabricaTradicional() {
        FabricaCombo fabrica = new FabricaComboTradicional();
        Item lanche = fabrica.criarHamburguer();
        assertEquals("Hambúrguer de Carne", lanche.getDescricao());
    }

    @Test
    void deveCriarBebidaPelaFabricaTradicional() {
        FabricaCombo fabrica = new FabricaComboTradicional();
        Item bebida = fabrica.criarBebida();
        assertEquals("Refrigerante Lata", bebida.getDescricao());
    }

    // --- TRANSIÇÕES DE ESTADO (STATE) DESMEMBRADAS ---

    @Test
    void pedidoNovoDeveIniciarComoRecebido() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        assertEquals("Recebido", pedido.getStatus());
    }

    @Test
    void pedidoRecebidoDeveAvancarParaEmPreparo() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado();
        assertEquals("Em Preparo", pedido.getStatus());
    }

    @Test
    void pedidoEmPreparoDeveAvancarParaPronto() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em preparo
        pedido.avancarEstado(); // Pronto
        assertEquals("Pronto", pedido.getStatus());
    }

    @Test
    void pedidoProntoDeveAvancarParaEntregue() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.avancarEstado(); // Em preparo
        pedido.avancarEstado(); // Pronto
        pedido.avancarEstado(); // Entregue
        assertEquals("Entregue", pedido.getStatus());
    }

    @Test
    void naoDeveAvancarEstadoAposCancelado() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.cancelarPedido();

        pedido.avancarEstado(); // Tentativa inválida de avanço

        // Deve continuar cancelado
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    void naoDeveAvancarEstadoAposDevolucao() {
        Pedido pedido = new PedidoBalcao(new PagamentoPix());
        pedido.devolverPedido();

        pedido.avancarEstado(); // Tentativa inválida de avanço

        // Continua em devolução
        assertEquals("Devolução", pedido.getStatus());
    }

    // --- CÁLCULO DE TOTAIS E PAGAMENTO ---

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

    // --- TESTE DE INTEGRAÇÃO DESMEMBRADO ---

    @Test
    void deveCalcularTotalFinalIntegrandoVariosPadroes() {
        Configuracao.getInstance().setTaxaEntrega(15.0f);
        FabricaCombo fabrica = new FabricaComboTradicional();

        Pedido pedido = new PedidoDelivery(new PagamentoCartao());
        pedido.adicionarItem(new Queijo(new Bacon(fabrica.criarHamburguer())));
        pedido.adicionarItem(fabrica.criarAcompanhamento()); // 10
        pedido.adicionarItem(fabrica.criarBebida()); // 8

        // Subtotal Itens: 33 + 10 + 8 = 51. Delivery: +15. Total = 66
        assertEquals(66.0f, pedido.calcularTotalFinal());
    }

    @Test
    void deveAtingirEstadoEntregueIntegrandoVariosPadroes() {
        FabricaCombo fabrica = new FabricaComboTradicional();

        Pedido pedido = new PedidoDelivery(new PagamentoCartao());
        pedido.adicionarItem(fabrica.criarHamburguer());

        pedido.avancarEstado(); // Prepara
        pedido.avancarEstado(); // Pronto
        pedido.avancarEstado(); // Entregue

        assertEquals("Entregue", pedido.getStatus());
    }

    // --- NOTIFICAÇÕES (OBSERVER) DESMEMBRADAS ---

    @Test
    void deveNotificarClienteAoAvancarParaEmPreparo() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        Cliente cliente = new Cliente("João");
        pedido.addObservador(cliente);

        pedido.avancarEstado();
        assertEquals("João, seu pedido está: Em Preparo", cliente.getNotificacoes().getLast());
    }

    @Test
    void deveNotificarClienteAoAvancarParaPronto() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        Cliente cliente = new Cliente("João");
        pedido.addObservador(cliente);

        pedido.avancarEstado(); // Em preparo
        pedido.avancarEstado(); // Pronto

        assertEquals("João, seu pedido está: Pronto", cliente.getNotificacoes().getLast());
    }

    @Test
    void deveNotificarPrimeiroClienteInscritoNaLista() {
        Pedido pedido = new PedidoDelivery(new PagamentoPix());
        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");
        pedido.addObservador(cliente1);
        pedido.addObservador(cliente2);

        pedido.avancarEstado();
        assertEquals("João, seu pedido está: Em Preparo", cliente1.getNotificacoes().getFirst());
    }

    @Test
    void deveNotificarSegundoClienteInscritoNaLista() {
        Pedido pedido = new PedidoDelivery(new PagamentoPix());
        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");
        pedido.addObservador(cliente1);
        pedido.addObservador(cliente2);

        pedido.avancarEstado();
        assertEquals("Maria, seu pedido está: Em Preparo", cliente2.getNotificacoes().getFirst());
    }

    @Test
    void naoDeveNotificarClienteNaoInscrito() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        Cliente cliente = new Cliente("João");

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

    // --- CADEIA DE RESPONSABILIDADE (CHAIN) DESMEMBRADA ---

    @Test
    void atendenteDeveTerGerenteComoSuperior() {
        FuncionarioFinanceiro financeiro = new FuncionarioFinanceiro(null);
        FuncionarioGerente gerente = new FuncionarioGerente(financeiro);
        FuncionarioAtendente atendente = new FuncionarioAtendente(gerente);

        assertEquals(gerente, atendente.getSuperior());
    }

    @Test
    void gerenteDeveTerFinanceiroComoSuperior() {
        FuncionarioFinanceiro financeiro = new FuncionarioFinanceiro(null);
        FuncionarioGerente gerente = new FuncionarioGerente(financeiro);
        FuncionarioAtendente atendente = new FuncionarioAtendente(gerente);

        assertEquals(financeiro, gerente.getSuperior());
    }

    @Test
    void financeiroNaoDeveTerSuperior() {
        FuncionarioFinanceiro financeiro = new FuncionarioFinanceiro(null);
        assertNull(financeiro.getSuperior());
    }

    @Test
    void deveResolverReclamacaoDeLancheFrioComAtendente() {
        FuncionarioGerente gerente = new FuncionarioGerente(new FuncionarioFinanceiro(null));
        FuncionarioAtendente atendente = new FuncionarioAtendente(gerente);

        Reclamacao reclamacao = new Reclamacao(TipoReclamacaoLancheFrio.getInstancia());
        assertEquals("Atendente", atendente.tratarReclamacao(reclamacao));
    }

    @Test
    void deveEscalarReclamacaoDeEstornoParaFinanceiro() {
        FuncionarioGerente gerente = new FuncionarioGerente(new FuncionarioFinanceiro(null));
        FuncionarioAtendente atendente = new FuncionarioAtendente(gerente);

        Reclamacao reclamacao = new Reclamacao(TipoReclamacaoEstorno.getInstancia());
        assertEquals("Financeiro", atendente.tratarReclamacao(reclamacao));
    }

    // --- RESUMO DO PEDIDO DESMEMBRADO ---

    @Test
    void resumoPedidoBalcaoDeveConterStatus() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.adicionarItem(new HamburguerCarne());

        assertTrue(pedido.gerarResumo().contains("Status: Recebido"));
    }

    @Test
    void resumoPedidoBalcaoDeveConterTotal() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.adicionarItem(new HamburguerCarne()); // 25

        assertTrue(pedido.gerarResumo().contains("Total a Pagar: R$ 25,00"));
    }

    @Test
    void resumoPedidoBalcaoDeveConterInstrucao() {
        Pedido pedido = new PedidoBalcao(new PagamentoCartao());
        pedido.adicionarItem(new HamburguerCarne());

        assertTrue(pedido.gerarResumo().contains("Retirar no balcão"));
    }

    @Test
    void resumoPedidoDeliveryDeveConterTotalComTaxa() {
        Configuracao.getInstance().setTaxaEntrega(10.0f);
        Pedido pedido = new PedidoDelivery(new PagamentoCartao());
        pedido.adicionarItem(new HamburguerCarne()); // 25 + 10 = 35

        assertTrue(pedido.gerarResumo().contains("Total a Pagar: R$ 35,00"));
    }

    @Test
    void resumoPedidoDeliveryDeveConterInstrucaoLogistica() {
        Pedido pedido = new PedidoDelivery(new PagamentoCartao());
        pedido.adicionarItem(new HamburguerCarne());

        assertTrue(pedido.gerarResumo().contains("Aguardar o motoboy"));
    }
}