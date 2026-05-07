package hamburgueria;

public class PedidoBalcao extends Pedido {
    public PedidoBalcao(FormaPagamento formaPagamento) { super(formaPagamento); }

    @Override
    public float calcularTotalFinal() {
        float subtotal = somarItens();
        return this.formaPagamento.aplicarTaxas(subtotal);
    }

    @Override
    public String obterInstrucaoEntrega() {
        return "Retirar no balcão quando a senha for chamada no painel.";
    }
}