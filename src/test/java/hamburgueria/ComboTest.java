package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComboTest {

    private Combo comboClassico;
    private Pedido pedidoIntegrado;

    @BeforeEach
    void setUp() {
        // 1. Cenário: Combo com desconto (Testando o Padrão Composite Isolado)
        Item lanche = new Queijo(new HamburguerCarne()); // Preço: 25.0 + 3.0 = 28.0
        Item batata = new BatataFrita();
        Item refri = new Refrigerante();

        comboClassico = new Combo("Combo Clássico", 0.10f); // 10% de desconto
        comboClassico.adicionarItemCombo(lanche);
        comboClassico.adicionarItemCombo(batata);
        comboClassico.adicionarItemCombo(refri);

        // 2. Cenário: Combo dentro de um Pedido (Testando a Integração Builder + Composite + Strategy)
        Combo comboSimples = new Combo("Combo Simples", 0.0f);
        comboSimples.adicionarItemCombo(new HamburguerCarne());
        comboSimples.adicionarItemCombo(new Refrigerante());

        pedidoIntegrado = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(new PagamentoPix()) // O Pix aplica 10% de desconto no Total
                .adicionarItem(comboSimples)
                .build();
    }



    @Test
    void deveGerarDescricaoAgrupandoOsItensDoCombo() {
        String descricaoEsperada = "Combo Clássico (Hambúrguer de Carne + Queijo + Batata Frita Média + Refrigerante Lata)";
        assertEquals(descricaoEsperada, comboClassico.getDescricao());
    }

    @Test
    void deveCalcularOPrecoDoComboAplicandoDescontoDaFabrica() {
        // Preço base: 28.0 + 10.0 + 8.0 = 46.0 | Com 10% de desconto do Combo = 41.4
        assertEquals(41.4f, comboClassico.getPreco(), 0.01f);
    }


    @Test
    void deveCalcularTotalFinalDoPedidoContendoOCombo() {

        assertEquals(29.7f, pedidoIntegrado.calcularTotalFinal(), 0.01f);
    }

    @Test
    void resumoDoPedidoDeveConterADescricaoDoComboInterno() {

        assertTrue(pedidoIntegrado.gerarResumo().contains("Combo Simples (Hambúrguer de Carne + Refrigerante Lata)"));
    }
}