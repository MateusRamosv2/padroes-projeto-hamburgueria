package hamburgueria;

public class Queijo extends AdicionalDecorator {


    private static final String NOME_ADICIONAL = "Queijo";
    private static final float PRECO_ADICIONAL = 3.0f;

    public Queijo(Item itemDecorado) {
        super(itemDecorado);
    }

    @Override
    public String getNomeAdicional() {
        return NOME_ADICIONAL;
    }

    @Override
    public float getPrecoAdicional() {
        return PRECO_ADICIONAL;
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}