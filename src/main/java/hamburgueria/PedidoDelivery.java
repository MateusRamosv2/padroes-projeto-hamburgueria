package hamburgueria;

public class PedidoDelivery extends Pedido {
    public PedidoDelivery(FormaPagamento formaPagamento) { super(formaPagamento); }

    @Override
    public float calcularTotalFinal() {
        float subtotal = somarItens();
        float subtotalComPagamento = this.formaPagamento.aplicarTaxas(subtotal);
        return subtotalComPagamento + Configuracao.getInstance().getTaxaEntrega();
    }

    @Override
    public String obterInstrucaoEntrega() {
        return "Aguardar o motoboy no endereço cadastrado.";
    }
}