package hamburgueria;

public class OperadorOu implements FiltroPedidoExpressao {
    private FiltroPedidoExpressao esquerda;
    private FiltroPedidoExpressao direita;

    public OperadorOu(FiltroPedidoExpressao esquerda, FiltroPedidoExpressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean interpretar(Pedido pedido) {
        return esquerda.interpretar(pedido) || direita.interpretar(pedido);
    }
}