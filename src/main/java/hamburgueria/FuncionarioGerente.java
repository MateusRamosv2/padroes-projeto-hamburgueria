package hamburgueria;

public class FuncionarioGerente extends FuncionarioHamburgueria {
    public FuncionarioGerente(FuncionarioHamburgueria superior) {
        this.listaReclamacoes.add(TipoReclamacaoAtraso.getInstancia());
        setSuperior(superior);
    }
    public String getCargo() { return "Gerente"; }
}