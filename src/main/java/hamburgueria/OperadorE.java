package hamburgueria;

public class OperadorE implements FiltroPedidoExpressao {
    private FiltroPedidoExpressao esquerda;
    private FiltroPedidoExpressao direita;

    public OperadorE(FiltroPedidoExpressao esquerda, FiltroPedidoExpressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean interpretar(Pedido pedido) {
        return esquerda.interpretar(pedido) && direita.interpretar(pedido);
    }
}