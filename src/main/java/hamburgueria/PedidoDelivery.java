package hamburgueria;

public class PedidoDelivery extends Pedido {
    public PedidoDelivery(FormaPagamento formaPagamento) { super(formaPagamento); }

    public float calcularTotalFinal() {
        float subtotal = somarItens();
        float subtotalComPagamento = this.formaPagamento.aplicarTaxas(subtotal);
        return subtotalComPagamento + Configuracao.getInstance().getTaxaEntrega();
    }
}