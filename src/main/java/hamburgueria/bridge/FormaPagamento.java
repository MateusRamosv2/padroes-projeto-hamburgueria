package hamburgueria.bridge;

// Implementador do Bridge
public interface FormaPagamento {
    float aplicarTaxas(float valorTotal);
}