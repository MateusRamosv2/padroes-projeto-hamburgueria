package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditoriaDietaVisitorTest {

    private String relatorioComboMonstro;

    @BeforeEach
    void setUp() {

        Combo combo = new Combo("Combo Monstro", 0.10f);
        combo.adicionarItemCombo(new Queijo(new HamburguerCarne()));
        combo.adicionarItemCombo(new BatataFrita());
        combo.adicionarItemCombo(new Refrigerante());

        AuditoriaDietaVisitor auditor = new AuditoriaDietaVisitor();

        relatorioComboMonstro = combo.aceitar(auditor);
    }



    @Test
    void deveAuditarHamburguerComBacon() {
        Item lanche = new Bacon(new HamburguerCarne()); // Decorator
        AuditoriaDietaVisitor auditor = new AuditoriaDietaVisitor();

        String resultado = lanche.aceitar(auditor);
        assertEquals("[Carnívoro/Porco] Adicional de Bacon -> [Carnívoro] Hambúrguer Bovino Tradicional", resultado);
    }



    @Test
    void auditoriaDeComboDeveConterCabecalhoComNomeDoCombo() {
        assertTrue(relatorioComboMonstro.contains("--- Auditoria do Combo Monstro"));
    }

    @Test
    void auditoriaDeComboDeveIdentificarAdicionalDeQueijoComoLaticinio() {
        assertTrue(relatorioComboMonstro.contains("[Vegetariano/Laticínio] Adicional de Queijo"));
    }

    @Test
    void auditoriaDeComboDeveIdentificarHamburguerDeCarneComoCarnivoro() {
        assertTrue(relatorioComboMonstro.contains("[Carnívoro] Hambúrguer Bovino Tradicional"));
    }

    @Test
    void auditoriaDeComboDeveIdentificarBatataFritaComoVegana() {
        assertTrue(relatorioComboMonstro.contains("[Vegano] Batata Frita"));
    }

}