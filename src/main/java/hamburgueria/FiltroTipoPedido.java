package hamburgueria;

public class FiltroTipoPedido implements FiltroPedidoExpressao {
    private String tipoAlvo;

    public FiltroTipoPedido(String tipoAlvo) {
        this.tipoAlvo = tipoAlvo;
    }

    @Override
    public boolean interpretar(Pedido pedido) {

        return pedido.getTipoPedido().equalsIgnoreCase(tipoAlvo);
    }
}