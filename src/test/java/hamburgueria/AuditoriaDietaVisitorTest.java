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
}