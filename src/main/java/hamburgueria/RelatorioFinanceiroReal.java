package hamburgueria;

import java.util.Arrays;
import java.util.List;

public class RelatorioFinanceiroReal implements RelatorioFinanceiro {
    private String dataFechamento;

    public RelatorioFinanceiroReal(String dataFechamento) {
        this.dataFechamento = dataFechamento;

        System.out.println("[Log BD] Calculando fluxo de caixa e faturamento para " + dataFechamento + "...");
    }

    @Override
    public List<String> gerarRelatorioFaturamento() {

        return Arrays.asList(
                "Relatório Consolidado: " + dataFechamento,
                "Receita Total: R$ 8.450,00",
                "Custos de Ingredientes: R$ 2.100,00",
                "Taxas de Delivery (Loggi): R$ 450,00",
                "Lucro Líquido: R$ 5.900,00"
        );
    }
}