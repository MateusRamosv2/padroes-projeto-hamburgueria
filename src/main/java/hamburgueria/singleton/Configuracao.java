package hamburgueria.singleton;

public class Configuracao {

    private static final Configuracao instancia = new Configuracao();
    private float taxaEntrega = 10.0f;

    private Configuracao() {}

    public static Configuracao getInstance() {
        return instancia;
    }

    public float getTaxaEntrega() { return taxaEntrega; }
    public void setTaxaEntrega(float taxa) { this.taxaEntrega = taxa; }
}