package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardapioTest {

    @Test
    void deveClonarComboPermitindoCustomizacaoSemAfetarOOriginal() {
        Cardapio cardapio = new Cardapio();

        Combo comboMaster = new Combo("Combo Casal", 0.10f);
        BatataFrita batata = new BatataFrita();
        Refrigerante refri = new Refrigerante();

        comboMaster.adicionarItemCombo(new HamburguerCarne());
        comboMaster.adicionarItemCombo(new HamburguerCarne());
        comboMaster.adicionarItemCombo(batata);
        comboMaster.adicionarItemCombo(refri);

        cardapio.cadastrarComboPrototype("COMBO_CASAL", comboMaster);


        Combo comboDoCliente = cardapio.solicitarCombo("COMBO_CASAL");

        comboDoCliente.removerItemCombo(batata);

        comboDoCliente.adicionarItemCombo(new Bacon(new HamburguerCarne()));



        assertEquals(4, comboDoCliente.getItensCombo().size());
        assertTrue(comboDoCliente.getDescricao().contains("Bacon"));
        assertFalse(comboDoCliente.getDescricao().contains("Batata Frita"));


        Combo comboOriginalProtegido = cardapio.solicitarCombo("COMBO_CASAL");


        assertEquals(4, comboOriginalProtegido.getItensCombo().size());
        assertTrue(comboOriginalProtegido.getDescricao().contains("Batata Frita"));
        assertFalse(comboOriginalProtegido.getDescricao().contains("Bacon"));
    }
}