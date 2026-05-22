package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetalheItemFactoryTest {

    @Test
    void deveCompartilharDetalhesPesadosNaMemoriaParaMilharesDePedidos() {

        Item pedidoCliente1 = new HamburguerCarne();
        Item pedidoCliente2 = new HamburguerCarne();
        Item pedidoCliente3 = new BatataFrita();
        Item pedidoCliente4 = new HamburguerCarne();
        Item pedidoCliente5 = new BatataFrita();


        assertEquals(2, DetalheItemFactory.getTotalDetalhesEmMemoria());


        assertEquals("Hambúrguer de Carne", pedidoCliente1.getDescricao());
        assertEquals(25.0f, pedidoCliente1.getPreco());

        assertEquals("Batata Frita Média", pedidoCliente3.getDescricao());
        assertEquals(10.0f, pedidoCliente3.getPreco());
    }
}