package hamburgueria.chainofresponsibility;

public class TipoReclamacaoLancheFrio implements TipoReclamacao {
    private static final TipoReclamacaoLancheFrio instancia = new TipoReclamacaoLancheFrio();
    private TipoReclamacaoLancheFrio() {}
    public static TipoReclamacaoLancheFrio getInstancia() { return instancia; }
}