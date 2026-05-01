package hamburgueria;

public class FabricaComboTradicional implements FabricaCombo {
    public Item criarHamburguer() { return new HamburguerCarne(); }

    public Item criarAcompanhamento() {
        return new Item() {
            public String getDescricao() { return "Batata Frita Média"; }
            public float getPreco() { return 10.0f; }
        };
    }

    public Item criarBebida() {
        return new Item() {
            public String getDescricao() { return "Refrigerante Cola"; }
            public float getPreco() { return 8.0f; }
        };
    }
}