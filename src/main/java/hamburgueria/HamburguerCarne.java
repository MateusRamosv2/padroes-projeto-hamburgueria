package hamburgueria;

public class HamburguerCarne implements Item {
    public String getDescricao() { return "Hambúrguer de Carne"; }
    public float getPreco() { return 25.0f; }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}