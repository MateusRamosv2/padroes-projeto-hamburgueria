package hamburgueria;

public class Refrigerante implements Item {


    private static final String DESCRICAO_PADRAO = "Refrigerante Lata";
    private static final float PRECO_BASE = 8.0f;

    @Override
    public float getPreco() {
        return PRECO_BASE;
    }

    @Override
    public String getDescricao() {
        return DESCRICAO_PADRAO;
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}