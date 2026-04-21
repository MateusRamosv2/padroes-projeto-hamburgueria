package hamburgueria.factory;
import hamburgueria.core.Item;


public interface FabricaCombo {
    Item criarHamburguer();
    Item criarAcompanhamento();
    Item criarBebida();
}