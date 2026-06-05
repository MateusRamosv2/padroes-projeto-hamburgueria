package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RelatorioFinanceiroProxyTest {

    @Test
    void devePermitirAcessoAosDadosSigilososParaGerente() {

        FuncionarioHamburgueria gerente = new FuncionarioGerente(null);

        RelatorioFinanceiro proxy = new RelatorioFinanceiroProxy(gerente, "2026-06-05");


        assertEquals(5, proxy.gerarRelatorioFaturamento().size());
        assertTrue(proxy.gerarRelatorioFaturamento().get(1).contains("Receita Total: R$"));
    }
}