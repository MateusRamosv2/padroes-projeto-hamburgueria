package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Item {
    private String nomeCombo;
    private List<Item> itensCombo = new ArrayList<>();
    private float desconto;

    public Combo(String nomeCombo, float desconto) {
        this.nomeCombo = nomeCombo;
        this.desconto = desconto;
    }

    public void adicionarItemCombo(Item item) {
        this.itensCombo.add(item);
    }

    @Override
    public float getPreco() {
        float total = 0;
        for (Item item : itensCombo) {
            total += item.getPreco();
        }
        return total - (total * desconto);
    }

    @Override
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder(nomeCombo + " (");
        for (int i = 0; i < itensCombo.size(); i++) {
            descricao.append(itensCombo.get(i).getDescricao());
            if (i < itensCombo.size() - 1) {
                descricao.append(" + ");
            }
        }
        descricao.append(")");
        return descricao.toString();
    }
}