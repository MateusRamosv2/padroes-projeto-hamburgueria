package hamburgueria;

public class CalculadoraPagamento {
    private float valorBase;

    public CalculadoraPagamento(float valorBase) {
        this.valorBase = valorBase;
    }

    public float calcular(EstrategiaPagamento estrategia) {
        return estrategia.calcularPrecoFinal(valorBase);
    }
}