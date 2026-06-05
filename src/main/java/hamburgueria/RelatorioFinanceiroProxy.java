package hamburgueria;

import java.util.List;

public class RelatorioFinanceiroProxy implements RelatorioFinanceiro {

    private RelatorioFinanceiroReal relatorioReal;
    private FuncionarioHamburgueria funcionarioLogado;
    private String dataFechamento;

    public RelatorioFinanceiroProxy(FuncionarioHamburgueria funcionarioLogado, String dataFechamento) {
        this.funcionarioLogado = funcionarioLogado;
        this.dataFechamento = dataFechamento;
    }

    @Override
    public List<String> gerarRelatorioFaturamento() {


        if (funcionarioLogado instanceof FuncionarioGerente || funcionarioLogado instanceof FuncionarioFinanceiro) {

            if (this.relatorioReal == null) {
                this.relatorioReal = new RelatorioFinanceiroReal(this.dataFechamento);
            }
            return this.relatorioReal.gerarRelatorioFaturamento();

        } else {

            throw new SecurityException("Acesso Negado: O cargo de " + funcionarioLogado.getCargo() + " não possui permissão para visualizar o faturamento.");
        }
    }
}