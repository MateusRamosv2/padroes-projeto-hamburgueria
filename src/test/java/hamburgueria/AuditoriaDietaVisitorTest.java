package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditoriaDietaVisitorTest {

    @Test
    void deveAuditarHamburguerComBacon() {
        Item lanche = new Bacon(new HamburguerCarne()); // Decorator
        AuditoriaDietaVisitor auditor = new AuditoriaDietaVisitor();

        String resultado = lanche.aceitar(auditor);

        assertEquals("[Carnívoro/Porco] Adicional de Bacon -> [Carnívoro] Hambúrguer Bovino Tradicional", resultado);
    }

    @Test
    void deveAuditarComboInteiroEVerificarAlergenicos() {

        Combo combo = new Combo("Combo Monstro", 0.10f);


        combo.adicionarItemCombo(new Queijo(new HamburguerCarne()));


        combo.adicionarItemCombo(new BatataFrita());
        combo.adicionarItemCombo(new Refrigerante());

        AuditoriaDietaVisitor auditor = new AuditoriaDietaVisitor();
        String relatorioTotem = combo.aceitar(auditor);


        assertTrue(relatorioTotem.contains("--- Auditoria do Combo Monstro"));
        assertTrue(relatorioTotem.contains("[Vegetariano/Laticínio] Adicional de Queijo"));
        assertTrue(relatorioTotem.contains("[Carnívoro] Hambúrguer Bovino Tradicional"));
        assertTrue(relatorioTotem.contains("[Vegano] Batata Frita"));
    }

}