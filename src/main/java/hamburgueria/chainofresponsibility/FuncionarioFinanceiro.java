package hamburgueria.chainofresponsibility;

public class FuncionarioFinanceiro extends FuncionarioHamburgueria {
    public FuncionarioFinanceiro(FuncionarioHamburgueria superior) {
        this.listaReclamacoes.add(TipoReclamacaoEstorno.getInstancia());
        setSuperior(superior);
    }
    public String getCargo() { return "Financeiro"; }
}