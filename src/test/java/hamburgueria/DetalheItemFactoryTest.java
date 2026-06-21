package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetalheItemFactoryTest {


    @Test
    void deveRetornarExatamenteAMesmaInstanciaEmMemoriaParaChavesIguais() {

        DetalheItem item1 = DetalheItemFactory.getDetalhe("Milkshake", 15.0f, "milk.png", "500 kcal");

        DetalheItem item2 = DetalheItemFactory.getDetalhe("Milkshake", 15.0f, "milk.png", "500 kcal");

        assertSame(item1, item2);
    }

    @Test
    void deveRenderizarAplicativoComSucessoCobrindoOMetodoNaoUtilizado() {

        DetalheItem item = DetalheItemFactory.getDetalhe("Suco Natural", 8.0f, "img_suco.png", "Vitamina C");

        assertEquals("Exibindo [img_suco.png] - Vitamina C", item.renderizarParaApp());
    }

    @Test
    void deveRetornarNomeEPrecoBaseCorretamenteDoDetalheItem() {

        DetalheItem item = DetalheItemFactory.getDetalhe("Agua", 5.0f, "agua.png", "0 kcal");

        assertEquals("Agua", item.getNome());
        assertEquals(5.0f, item.getPrecoBase());
    }


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