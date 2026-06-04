package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoggiAdapterTest {

    @Test
    void deveDespacharPedidoViaApiExternaLoggiComSucesso() {

        FormaPagamento cartao = new PagamentoCartao();
        PedidoDelivery pedido = new PedidoDelivery(cartao);
        pedido.adicionarItem(new HamburguerCarne());


        ApiLoggiExterna servicoLoggi = new ApiLoggiExterna();
        IntegracaoLogistica logisticaTerceirizada = new LoggiAdapter(servicoLoggi);


        String resposta = logisticaTerceirizada.despacharPedido(pedido, "Avenida Brasil, 1500");


        assertTrue(resposta.contains("Motoboy da Loggi a caminho de [Avenida Brasil, 1500]"));


        assertTrue(resposta.contains("Valor a cobrar: R$"));
    }
}