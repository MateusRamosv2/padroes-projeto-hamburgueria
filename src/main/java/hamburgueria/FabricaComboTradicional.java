package hamburgueria;

public class FabricaComboTradicional implements FabricaCombo {

    @Override
    public Item criarHamburguer() {
        return new HamburguerCarne();
    }

    @Override
    public Item criarAcompanhamento() {
        return new BatataFrita(); // Usando a classe concreta que já tem o Visitor!
    }

    @Override
    public Item criarBebida() {
        return new Refrigerante(); // Usando a classe concreta que já tem o Visitor!
    }
}