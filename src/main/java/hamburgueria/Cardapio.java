package hamburgueria;

import java.util.HashMap;
import java.util.Map;

public class Cardapio {

    // O nosso catálogo de Protótipos
    private Map<String, Combo> combosProntos = new HashMap<>();

    public void cadastrarComboPrototype(String chave, Combo combo) {
        combosProntos.put(chave, combo);
    }

    // Quando o cliente pede um combo, ele NUNCA recebe o original, ele recebe um CLONE!
    public Combo solicitarCombo(String chave) {
        Combo prototipo = combosProntos.get(chave);
        if (prototipo == null) {
            throw new IllegalArgumentException("Combo não encontrado no cardápio!");
        }
        return prototipo.clone();
    }
}