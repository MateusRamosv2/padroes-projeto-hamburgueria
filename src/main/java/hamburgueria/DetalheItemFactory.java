package hamburgueria;

import java.util.HashMap;
import java.util.Map;

public class DetalheItemFactory {

    private static Map<String, DetalheItem> cacheDetalhes = new HashMap<>();

    public static DetalheItem getDetalhe(String nome, float precoBase, String urlImagem, String dadosNutricionais) {
        DetalheItem detalhe = cacheDetalhes.get(nome);

        if (detalhe == null) {
            detalhe = new DetalheItem(nome, precoBase, urlImagem, dadosNutricionais);
            cacheDetalhes.put(nome, detalhe);
        }

        return detalhe;
    }

    public static int getTotalDetalhesEmMemoria() {
        return cacheDetalhes.size();
    }
}