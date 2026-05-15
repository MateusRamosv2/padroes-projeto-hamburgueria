package hamburgueria;

public class HamburguerVegano implements Item {
    public String getDescricao() { return "Hambúrguer de Grão de Bico"; }
    public float getPreco() { return 28.0f; }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}