package hamburgueria;

public class Bacon extends AdicionalDecorator {
    public Bacon(Item itemDecorado) { super(itemDecorado); }
    public String getNomeAdicional() { return "Bacon"; }
    public float getPrecoAdicional() { return 5.0f; }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }

}