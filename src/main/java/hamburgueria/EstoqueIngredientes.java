package hamburgueria;

import java.util.HashMap;
import java.util.Map;

public class EstoqueIngredientes {
    private Map<String, Integer> ingredientes = new HashMap<>();

    public void adicionarEstoque(String ingrediente, int quantidade) {
        int atual = ingredientes.getOrDefault(ingrediente, 0);
        ingredientes.put(ingrediente, atual + quantidade);
    }

    public void darBaixa(String ingrediente, int quantidade) {
        int atual = ingredientes.getOrDefault(ingrediente, 0);
        if (atual >= quantidade) {
            ingredientes.put(ingrediente, atual - quantidade);
        } else {
            throw new IllegalArgumentException("Estoque insuficiente para o ingrediente: " + ingrediente);
        }
    }

    public int consultarEstoque(String ingrediente) {
        return ingredientes.getOrDefault(ingrediente, 0);
    }
}