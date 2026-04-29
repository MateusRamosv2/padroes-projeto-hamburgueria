package hamburgueria.chainofresponsibility;

public class FuncionarioAtendente extends FuncionarioHamburgueria {
    public FuncionarioAtendente(FuncionarioHamburgueria superior) {
        this.listaReclamacoes.add(TipoReclamacaoLancheFrio.getInstancia());
        setSuperior(superior);
    }
    public String getCargo() { return "Atendente"; }
}