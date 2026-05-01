package hamburgueria;

public class Reclamacao {
    private TipoReclamacao tipo;

    public Reclamacao(TipoReclamacao tipo) {
        this.tipo = tipo;
    }

    public TipoReclamacao getTipo() { return tipo; }
}