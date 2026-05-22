package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Item, Cloneable {
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


    public void removerItemCombo(Item item) {
        this.itensCombo.remove(item);
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

    public List<Item> getItensCombo() {
        return this.itensCombo;
    }

    @Override
    public String aceitar(VisitorItem visitor) {
        return visitor.visitar(this);
    }

    @Override
    public Combo clone() {
        try {
            // Faz a cópia superficial do objeto (copia o nomeCombo e o desconto)
            Combo comboClone = (Combo) super.clone();

            comboClone.itensCombo = new ArrayList<>(this.itensCombo);

            return comboClone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Erro ao clonar o Combo", e);
        }
    }


}