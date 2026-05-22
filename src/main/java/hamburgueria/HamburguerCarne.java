package hamburgueria;

public class HamburguerCarne implements Item {

    // Referência para o objeto leve compartilhado (Flyweight)
    private final DetalheItem detalhePesado;

    public HamburguerCarne() {

        this.detalhePesado = DetalheItemFactory.getDetalhe(
                "Hambúrguer de Carne",
                25.0f,
                "https://cdn.hamburgueria.com/img/carne_4k.png",
                "Blend 200g, Pão Brioche. 600Kcal. Contém Glúten e Lactose."
        );
    }

    public String getDescricao() {
        return this.detalhePesado.getNome();
    }

    public float getPreco() {
        return this.detalhePesado.getPrecoBase();
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }
}