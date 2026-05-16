package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTotemTest {

    @Test
    void deveArmazenarHistoricoDePedidos() {
        ClienteTotem totem = new ClienteTotem("Mateus Ramos");

        // Pedido 1
        totem.adicionarAoCarrinho(new HamburguerCarne());
        totem.finalizarCompra();


        totem.adicionarAoCarrinho(new BatataFrita());
        totem.adicionarAoCarrinho(new Refrigerante());
        totem.finalizarCompra();

        assertEquals(2, totem.getQuantidadePedidosNoHistorico());
        assertTrue(totem.getCarrinhoAtual().isEmpty());
    }

}