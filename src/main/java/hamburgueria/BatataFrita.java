package hamburgueria;

public class BatataFrita implements Item {

    // Referência para o objeto leve compartilhado (Flyweight)
    private final DetalheItem detalhePesado;

    public BatataFrita() {
        this.detalhePesado = DetalheItemFactory.getDetalhe(
                "Batata Frita Média",
                10.0f,
                "https://cdn.hamburgueria.com/img/batata_media_4k.png",
                "Porção de 150g de batata palito frita. 312Kcal."
        );
    }

    @Override
    public float getPreco() {
        return this.detalhePesado.getPrecoBase();
    }

    @Override
    public String getDescricao() {
        return this.detalhePesado.getNome();
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}