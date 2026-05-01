package hamburgueria;

public class EstrategiaDebito implements EstrategiaPagamento {
    public float calcularPrecoFinal(float valorBase) {
        return valorBase; // Débito não tem taxa nem desconto, cobra o valor exato
    }
}