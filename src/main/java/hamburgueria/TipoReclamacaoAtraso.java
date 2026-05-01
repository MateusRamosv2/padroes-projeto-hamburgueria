package hamburgueria;

public class TipoReclamacaoAtraso implements TipoReclamacao {
    private static final TipoReclamacaoAtraso instancia = new TipoReclamacaoAtraso();
    private TipoReclamacaoAtraso() {}
    public static TipoReclamacaoAtraso getInstancia() { return instancia; }
}