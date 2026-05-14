package hamburgueria;

public interface Item {
    String getDescricao();
    float getPreco();

    String aceitar(VisitorItem visitor);
}