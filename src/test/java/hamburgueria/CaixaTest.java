package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CaixaTest {

    @Test
    void devePagarComPixComDesconto() {
        Caixa caixa = new Caixa();
        caixa.pagarComPix(100.0f);
        assertEquals(90.0f, caixa.getValorFinalPago(), 0.01f);
    }

    @Test
    void devePagarComCreditoComTaxa() {
        Caixa caixa = new Caixa();
        caixa.pagarComCredito(100.0f);
        assertEquals(105.0f, caixa.getValorFinalPago(), 0.01f);
    }

    @Test
    void devePagarComDebitoSemTaxa() {
        Caixa caixa = new Caixa();
        caixa.pagarComDebito(100.0f);
        assertEquals(100.0f, caixa.getValorFinalPago(), 0.01f);
    }

    @Test
    void devePagarComBoletoComTaxaFixa() {
        Caixa caixa = new Caixa();
        caixa.pagarComBoleto(100.0f);
        assertEquals(101.50f, caixa.getValorFinalPago(), 0.01f);
    }

    @Test
    void naoDevePermitirBoletoParaValorBaixo() {
        try {
            Caixa caixa = new Caixa();
            caixa.pagarComBoleto(15.0f);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Boleto não permitido para valores menores que R$ 20,00", e.getMessage());
        }
    }
}