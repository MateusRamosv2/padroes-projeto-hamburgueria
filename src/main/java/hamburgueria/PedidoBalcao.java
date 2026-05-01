package hamburgueria;

public class PedidoBalcao extends Pedido {
    public PedidoBalcao(FormaPagamento formaPagamento) { super(formaPagamento); }

    public float calcularTotalFinal() {
        float subtotal = somarItens();
        // Não adiciona taxa de entrega
        return this.formaPagamento.aplicarTaxas(subtotal);
    }
}