package hamburgueria;

public class EstrategiaCredito implements EstrategiaPagamento {
    public float calcularPrecoFinal(float valorBase) {
        return valorBase * 1.05f; // 5% de taxa da maquininha
    }
}