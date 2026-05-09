package hamburgueria;

public class FinanceiroHamburgueria extends ValidadorPedido {
    private static FinanceiroHamburgueria instancia = new FinanceiroHamburgueria();
    private FinanceiroHamburgueria() {};
    public static FinanceiroHamburgueria getInstancia() { return instancia; }
}