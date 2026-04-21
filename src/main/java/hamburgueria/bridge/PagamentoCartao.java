package hamburgueria.bridge;

public class PagamentoCartao implements FormaPagamento {
    public float aplicarTaxas(float valorTotal) {
        return valorTotal; // Preço normal
    }
}