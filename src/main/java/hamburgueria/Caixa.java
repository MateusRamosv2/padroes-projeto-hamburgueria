package hamburgueria;

public class Caixa {

    private float valorFinalPago;

    public float getValorFinalPago() {
        return valorFinalPago;
    }

    public void pagarComPix(float valorBase) {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(valorBase);
        this.valorFinalPago = calculadora.calcular(new EstrategiaPix());
    }

    public void pagarComCredito(float valorBase) {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(valorBase);
        this.valorFinalPago = calculadora.calcular(new EstrategiaCredito());
    }

    public void pagarComDebito(float valorBase) {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(valorBase);
        this.valorFinalPago = calculadora.calcular(new EstrategiaDebito());
    }

    public void pagarComBoleto(float valorBase) {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(valorBase);
        this.valorFinalPago = calculadora.calcular(new EstrategiaBoleto());
    }
}