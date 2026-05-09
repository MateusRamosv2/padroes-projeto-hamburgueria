package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComboTest {

    @Test
    void deveCriarComboComDescontoEValidarPrecoEDescricao() {

        Item lanche = new Queijo(new HamburguerCarne()); // Preço: 25.0 + 3.0 = 28.0
        Item batata = new BatataFrita(); // Preço: 10.0
        Item refri = new Refrigerante(); // Preço: 8.0


        // 2. Criando o Composto (Combo com 10% de desconto)
        Combo comboClassico = new Combo("Combo Clássico", 0.10f);
        comboClassico.adicionarItemCombo(lanche);
        comboClassico.adicionarItemCombo(batata);
        comboClassico.adicionarItemCombo(refri);

        // 3. Validações do Composite
        assertEquals("Combo Clássico (Hambúrguer de Carne + Queijo + Batata Frita Média + Refrigerante Lata)", comboClassico.getDescricao());
        assertEquals(41.4f, comboClassico.getPreco(), 0.01f); // 46.0 - 10% = 41.4
    }

    @Test
    void deveIntegrarComboNoPedidoBuilderPerfeitamente() {
        // Criando um combo simples
        Combo combo = new Combo("Combo Simples", 0.0f); // Sem desconto para facilitar
        combo.adicionarItemCombo(new HamburguerCarne()); // 25.0
        combo.adicionarItemCombo(new Refrigerante()); // 8.0

        Pedido pedido = new PedidoBuilder()
                .setTipoPedido("Balcao")
                .setFormaPagamento(new PagamentoPix())
                .adicionarItem(combo)
                .build();


        assertEquals(29.7f, pedido.calcularTotalFinal(), 0.01f);
        assertTrue(pedido.gerarResumo().contains("Combo Simples (Hambúrguer de Carne + Refrigerante Lata)"));
    }
}