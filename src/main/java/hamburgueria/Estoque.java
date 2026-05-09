package hamburgueria;

public class Estoque extends ValidadorPedido {
    private static Estoque instancia = new Estoque();
    private Estoque() {};
    public static Estoque getInstancia() { return instancia; }
}