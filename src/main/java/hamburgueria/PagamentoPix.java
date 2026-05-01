package hamburgueria;

public class PagamentoPix implements FormaPagamento {
    public float aplicarTaxas(float valorTotal) {
        return valorTotal * 0.9f; // 10% de desconto
    }
}