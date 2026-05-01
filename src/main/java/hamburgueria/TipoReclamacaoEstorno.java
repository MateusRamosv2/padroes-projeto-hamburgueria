package hamburgueria;

public class TipoReclamacaoEstorno implements TipoReclamacao {
    private static final TipoReclamacaoEstorno instancia = new TipoReclamacaoEstorno();
    private TipoReclamacaoEstorno() {}
    public static TipoReclamacaoEstorno getInstancia() { return instancia; }
}