package hamburgueria;

public class HamburguerVegano implements Item {


    private static final String DESCRICAO_PADRAO = "Hambúrguer de Grão de Bico";
    private static final float PRECO_BASE = 28.0f;

    @Override
    public String getDescricao() {
        return DESCRICAO_PADRAO;
    }

    @Override
    public float getPreco() {
        return PRECO_BASE;
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}