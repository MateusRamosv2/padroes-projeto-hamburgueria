package hamburgueria;

public class LoggiAdapter implements IntegracaoLogistica {

    private ApiLoggiExterna apiLoggi;
    private final String apiKeyConfigurada;

    public LoggiAdapter(ApiLoggiExterna apiLoggi) {
        this.apiLoggi = apiLoggi;

        this.apiKeyConfigurada = "TOKEN_SECRETO_LOGGI_2026";
    }

    @Override
    public String despacharPedido(PedidoDelivery pedido, String enderecoCliente) {


        float valorTotalDoPedido = pedido.calcularTotalFinal();


        return apiLoggi.requestRider(this.apiKeyConfigurada, enderecoCliente, valorTotalDoPedido);
    }
}