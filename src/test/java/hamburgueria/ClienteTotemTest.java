package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTotemTest {

    @Test
    void deveArmazenarHistoricoDePedidos() {
        ClienteTotem totem = new ClienteTotem("Mateus Ramos");


        totem.adicionarAoCarrinho(new HamburguerCarne());
        totem.finalizarCompra();


        totem.adicionarAoCarrinho(new BatataFrita());
        totem.adicionarAoCarrinho(new Refrigerante());
        totem.finalizarCompra();

        assertEquals(2, totem.getQuantidadePedidosNoHistorico());
        assertTrue(totem.getCarrinhoAtual().isEmpty());
    }

    @Test
    void deveRestaurarPedidoAnteriorComItensComplexos() {
        ClienteTotem totem = new ClienteTotem("Mateus Ramos");


        Item lancheDecorado = new Bacon(new Queijo(new HamburguerCarne()));
        totem.adicionarAoCarrinho(lancheDecorado);
        totem.finalizarCompra();


        Combo combo = new Combo("Combo Básico", 0.0f);
        combo.adicionarItemCombo(new HamburguerCarne());
        totem.adicionarAoCarrinho(combo);
        totem.finalizarCompra();


        totem.restaurarPedidoAnterior(0);


        assertEquals(1, totem.getCarrinhoAtual().size());
        assertTrue(totem.getCarrinhoAtual().get(0).getDescricao().contains("Bacon"));
        assertTrue(totem.getCarrinhoAtual().get(0).getDescricao().contains("Queijo"));
    }


}