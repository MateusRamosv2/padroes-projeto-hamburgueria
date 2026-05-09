package hamburgueria;

public class Logistica extends ValidadorPedido {
    private static Logistica instancia = new Logistica();
    private Logistica() {};
    public static Logistica getInstancia() { return instancia; }
}