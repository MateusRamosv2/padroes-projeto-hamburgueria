package hamburgueria;

public class EstrategiaBoleto implements EstrategiaPagamento {
    public float calcularPrecoFinal(float valorBase) {
        // Implementando a mesma lógica de exceção (erro) do professor na Divisão por Zero
        if (valorBase < 20.0f) {
            throw new IllegalArgumentException("Boleto não permitido para valores menores que R$ 20,00");
        } else {
            return valorBase + 1.50f; // Taxa fixa de R$ 1,50 de emissão bancária
        }
    }
}