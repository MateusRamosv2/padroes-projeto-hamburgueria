package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoMemento {

    private final List<Item> itensCapturados;

    public CarrinhoMemento(List<Item> itens) {
        this.itensCapturados = new ArrayList<>(itens);
    }

    public List<Item> getItensCapturados() {
        return this.itensCapturados;
    }
}