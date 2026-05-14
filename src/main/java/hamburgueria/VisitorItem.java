package hamburgueria;

public interface VisitorItem {
    String visitar(HamburguerCarne hamburguer);
    String visitar(BatataFrita batata);
    String visitar(Refrigerante refrigerante);
    String visitar(Combo combo);
    String visitar(Bacon bacon);
    String visitar(Queijo queijo);
}