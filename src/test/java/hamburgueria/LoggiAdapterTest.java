package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoggiAdapterTest {

    private IntegracaoLogistica logisticaTerceirizada;
    private PedidoDelivery pedido;
    private static final String ENDERECO_TESTE = "Avenida Brasil, 1500";
    private String respostaDespacho;

    @BeforeEach
    void setUp() {

        FormaPagamento cartao = new PagamentoCartao();
        pedido = new PedidoDelivery(cartao);
        pedido.adicionarItem(new HamburguerCarne());

        ApiLoggiExterna servicoLoggi = new ApiLoggiExterna();
        logisticaTerceirizada = new LoggiAdapter(servicoLoggi);

        respostaDespacho = logisticaTerceirizada.despacharPedido(pedido, ENDERECO_TESTE);
    }

    // --- TESTES DE INTEGRAÇÃO COM A API EXTERNA (ADAPTER) ---

    @Test
    void deveConterOEnderecoDeDestinoNaMensagemDeDespachoDaLoggi() {

        assertTrue(respostaDespacho.contains("Motoboy da Loggi a caminho de [" + ENDERECO_TESTE + "]"));
    }

    @Test
    void deveConterATagDeCobrancaComOValorTotalNaMensagemDeDespacho() {

        assertTrue(respostaDespacho.contains("Valor a cobrar: R$"));
    }
}