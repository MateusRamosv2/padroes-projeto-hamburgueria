package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraPagamentoTest {

    @Test
    void deveCalcularPagamentoViaPixComDesconto() {
        // 1. O valor base entra no construtor do Contexto (Calculadora)
        CalculadoraPagamento calculadora = new CalculadoraPagamento(100.0f);

        // 2. A Estratégia entra no momento do cálculo
        assertEquals(90.0f, calculadora.calcular(new EstrategiaPix()), 0.01f);
    }

    @Test
    void deveCalcularPagamentoViaCartaoComAcrecimo() {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(100.0f);

        assertEquals(105.0f, calculadora.calcular(new EstrategiaCredito()), 0.01f);
    }



    @Test
    void devePermitirPagamentoEmBoletoNoValorExatoDoLimiteMinimo() {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(20.0f);
        // O limite exato de 20.0 passa no IF interno da Estratégia
        assertDoesNotThrow(() -> calculadora.calcular(new EstrategiaBoleto()));
    }

    @Test
    void deveLancarExcecaoParaPagamentoBoletoAbaixoDoLimiteMinimo() {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(19.99f);
        // A Exceção aqui acontece DENTRO da EstrategiaBoleto, não na Calculadora
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> calculadora.calcular(new EstrategiaBoleto()));
        assertEquals("Boleto não permitido para valores menores que R$ 20,00", excecao.getMessage());
    }



    @Test
    void deveCalcularPagamentoExatamenteZeroSemLancarExcecao() {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(0.0f);

        // 0.0 com desconto do Pix continua sendo 0.0
        assertEquals(0.0f, calculadora.calcular(new EstrategiaPix()), 0.01f);
    }

    @Test
    void deveProcessarMatematicaDeValoresNegativosSemLancarExcecao() {
        CalculadoraPagamento calculadora = new CalculadoraPagamento(-100.0f);

        // -100.0 com acréscimo de 5% do Cartão de Crédito resulta em -105.0
        assertEquals(-105.0f, calculadora.calcular(new EstrategiaCredito()), 0.01f);
    }
}