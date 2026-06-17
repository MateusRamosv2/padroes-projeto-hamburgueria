package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetalheItemFactoryTest {

    // --- TESTE DO PADRÃO FLYWEIGHT ---

    @Test
    void deveArmazenarApenasInstanciasUnicasNaMemoriaFlyweight() {
        // Instancia 5 objetos, mas que compartilham apenas 2 tipos
        new HamburguerCarne();
        new HamburguerCarne();
        new BatataFrita();
        new HamburguerCarne();
        new BatataFrita();

        // O cache deve conter apenas 2 instâncias pesadas salvas
        assertEquals(2, DetalheItemFactory.getTotalDetalhesEmMemoria());
    }

    // --- TESTES DE ATRIBUTOS (HAMBÚRGUER) ---

    @Test
    void deveRetornarDescricaoCorretaDoHamburguerCarne() {
        Item pedidoCliente = new HamburguerCarne();
        assertEquals("Hambúrguer de Carne", pedidoCliente.getDescricao());
    }

    @Test
    void deveRetornarPrecoCorretoDoHamburguerCarne() {
        Item pedidoCliente = new HamburguerCarne();
        assertEquals(25.0f, pedidoCliente.getPreco());
    }

    // --- TESTES DE ATRIBUTOS (BATATA FRITA) ---

    @Test
    void deveRetornarDescricaoCorretaDaBatataFrita() {
        Item pedidoCliente = new BatataFrita();
        assertEquals("Batata Frita Média", pedidoCliente.getDescricao());
    }

    @Test
    void deveRetornarPrecoCorretoDaBatataFrita() {
        Item pedidoCliente = new BatataFrita();
        assertEquals(10.0f, pedidoCliente.getPreco());
    }
}