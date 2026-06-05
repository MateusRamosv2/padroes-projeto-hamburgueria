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

    @Test
    void devePermitirAcessoAosDadosSigilososParaFinanceiro() {
        FuncionarioHamburgueria financeiro = new FuncionarioFinanceiro(new FuncionarioGerente(null));
        RelatorioFinanceiro proxy = new RelatorioFinanceiroProxy(financeiro, "2026-06-05");

        assertNotNull(proxy.gerarRelatorioFaturamento());
    }

    @Test
    void deveBloquearAcessoEAtirarExcecaoParaAtendente() {
        FuncionarioHamburgueria atendente = new FuncionarioAtendente(new FuncionarioGerente(null));
        RelatorioFinanceiro proxy = new RelatorioFinanceiroProxy(atendente, "2026-06-05");


        Exception excecao = assertThrows(SecurityException.class, () -> {
            proxy.gerarRelatorioFaturamento();
        });

        assertEquals("Acesso Negado: O cargo de Atendente não possui permissão para visualizar o faturamento.", excecao.getMessage());
    }

}