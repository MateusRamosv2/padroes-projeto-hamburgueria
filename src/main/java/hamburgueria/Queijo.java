package hamburgueria;

public class Queijo extends AdicionalDecorator {
    public Queijo(Item itemDecorado) { super(itemDecorado); }
    public String getNomeAdicional() { return "Queijo"; }
    public float getPrecoAdicional() { return 3.0f; }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }

}