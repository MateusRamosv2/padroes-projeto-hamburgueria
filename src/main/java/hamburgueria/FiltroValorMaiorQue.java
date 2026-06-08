package hamburgueria;

public class FiltroValorMaiorQue implements FiltroPedidoExpressao {
    private float valorAlvo;

    public FiltroValorMaiorQue(float valorAlvo) {
        this.valorAlvo = valorAlvo;
    }

    @Override
    public boolean interpretar(Pedido pedido) {

        return pedido.calcularTotalFinal() > this.valorAlvo;
    }
}