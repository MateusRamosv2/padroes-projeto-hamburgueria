package hamburgueria;

public class Refrigerante implements Item {
    @Override
    public float getPreco() {
        return 8.0f;
    }

    @Override
    public String getDescricao() {
        return "Refrigerante Lata";
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}