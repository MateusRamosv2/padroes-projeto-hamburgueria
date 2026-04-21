package hamburgueria.decorator;
import hamburgueria.core.Item;

public abstract class AdicionalDecorator implements Item {
    protected Item itemDecorado;

    public AdicionalDecorator(Item itemDecorado) {
        this.itemDecorado = itemDecorado;
    }

    public abstract String getNomeAdicional();
    public abstract float getPrecoAdicional();

    public String getDescricao() {
        return itemDecorado.getDescricao() + " + " + getNomeAdicional();
    }

    public float getPreco() {
        return itemDecorado.getPreco() + getPrecoAdicional();
    }
}