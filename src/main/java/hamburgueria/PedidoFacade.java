package hamburgueria;

public class PedidoFacade {

    public static boolean autorizarPreparo(Pedido pedido) {
        if (Estoque.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false; // Faltou hambúrguer ou pão!
        }
        if (FinanceiroHamburgueria.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false; // Cartão recusado!
        }
        if (Logistica.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false; // Sem motoboy disponível!
        }
        return true; // Tudo certo, pode mandar para a chapa!
    }
}