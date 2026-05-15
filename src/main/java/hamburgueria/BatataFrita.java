package hamburgueria;

public class BatataFrita implements Item {
    @Override
    public float getPreco() {
        return 10.0f;
    }

    @Override
    public String getDescricao() {
        return "Batata Frita Média";
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}