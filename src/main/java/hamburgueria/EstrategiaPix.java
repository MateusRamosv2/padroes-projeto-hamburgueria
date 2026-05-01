package hamburgueria;

public class EstrategiaPix implements EstrategiaPagamento {
    public float calcularPrecoFinal(float valorBase) {
        return valorBase * 0.90f; // 10% de desconto no Pix
    }
}