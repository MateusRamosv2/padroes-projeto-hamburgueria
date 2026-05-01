package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public abstract class FuncionarioHamburgueria {
    protected List<TipoReclamacao> listaReclamacoes = new ArrayList<>();
    private FuncionarioHamburgueria superior;

    public FuncionarioHamburgueria getSuperior() { return superior; }
    public void setSuperior(FuncionarioHamburgueria superior) { this.superior = superior; }

    public abstract String getCargo();

    public String tratarReclamacao(Reclamacao reclamacao) {
        if (listaReclamacoes.contains(reclamacao.getTipo())) {
            return getCargo();
        } else {
            if (superior != null) {
                return superior.tratarReclamacao(reclamacao);
            } else {
                return "Sem solução definida";
            }
        }
    }
}