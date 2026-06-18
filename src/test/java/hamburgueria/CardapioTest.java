package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardapioTest {

    private Combo comboDoCliente;
    private Combo comboOriginalProtegido;

    @BeforeEach
    void setUp() {
        Cardapio cardapio = new Cardapio();


        Combo comboMaster = new Combo("Combo Casal", 0.10f);
        BatataFrita batata = new BatataFrita(); // Referência salva para poder remover depois
        Refrigerante refri = new Refrigerante();

        comboMaster.adicionarItemCombo(new HamburguerCarne());
        comboMaster.adicionarItemCombo(new HamburguerCarne());
        comboMaster.adicionarItemCombo(batata);
        comboMaster.adicionarItemCombo(refri);

        cardapio.cadastrarComboPrototype("COMBO_CASAL", comboMaster);


        comboDoCliente = cardapio.solicitarCombo("COMBO_CASAL");
        comboDoCliente.removerItemCombo(batata);
        comboDoCliente.adicionarItemCombo(new Bacon(new HamburguerCarne()));


        comboOriginalProtegido = cardapio.solicitarCombo("COMBO_CASAL");
    }

    @Test
    void comboCustomizadoDeveManterQuantidadeTotalDeItens() {

        assertEquals(4, comboDoCliente.getItensCombo().size());
    }

    @Test
    void comboCustomizadoDeveConterOAdicionalIncluido() {
        assertTrue(comboDoCliente.getDescricao().contains("Bacon"));
    }

    @Test
    void comboCustomizadoNaoDeveConterOItemRemovido() {
        assertFalse(comboDoCliente.getDescricao().contains("Batata Frita"));
    }


    @Test
    void comboOriginalDeveManterSuaQuantidadeDeItensProtegida() {
        assertEquals(4, comboOriginalProtegido.getItensCombo().size());
    }

    @Test
    void comboOriginalDeveManterOItemQueOClienteHaviaRemovidoNoClone() {
        assertTrue(comboOriginalProtegido.getDescricao().contains("Batata Frita"));
    }

    @Test
    void comboOriginalNaoDeveSofrerVazamentoDoAdicionalDoCliente() {
        // Se este teste falhar, significa que o .clone() está fazendo cópia por referência errada
        assertFalse(comboOriginalProtegido.getDescricao().contains("Bacon"));
    }
}