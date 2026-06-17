package hamburgueria;

public class Bacon extends AdicionalDecorator {


    private static final String NOME_ADICIONAL = "Bacon";
    private static final float PRECO_ADICIONAL = 5.0f;

    public Bacon(Item itemDecorado) {
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