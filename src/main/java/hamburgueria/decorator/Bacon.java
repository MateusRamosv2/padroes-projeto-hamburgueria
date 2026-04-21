package hamburgueria.decorator;
import hamburgueria.core.Item;

public class Bacon extends AdicionalDecorator {
    public Bacon(Item itemDecorado) { super(itemDecorado); }
    public String getNomeAdicional() { return "Bacon"; }
    public float getPrecoAdicional() { return 5.0f; }
}