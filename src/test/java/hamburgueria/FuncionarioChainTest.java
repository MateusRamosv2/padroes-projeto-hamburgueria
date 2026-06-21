package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioChainTest {

    private FuncionarioHamburgueria atendente;
    private FuncionarioHamburgueria gerente;
    private FuncionarioHamburgueria financeiro;

    @BeforeEach
    void setUp() {
        // Montando a cadeia perfeita para os testes de Caminho Feliz:
        // Atendente -> Gerente -> Financeiro -> Null (Fim da linha)
        financeiro = new FuncionarioFinanceiro(null);
        gerente = new FuncionarioGerente(financeiro);
        atendente = new FuncionarioAtendente(gerente);
    }

    @Test
    void atendenteDeveResolverReclamacaoDeLancheFrioDiretamente() {
        Reclamacao reclamacao = new Reclamacao(TipoReclamacaoLancheFrio.getInstancia());
        assertEquals("Atendente", atendente.tratarReclamacao(reclamacao));
    }

    @Test
    void gerenteDeveResolverReclamacaoDeAtrasoAposEscalonamento() {
        Reclamacao reclamacao = new Reclamacao(TipoReclamacaoAtraso.getInstancia());
        // A reclamação entra pelo Atendente, mas quem resolve é o Gerente
        assertEquals("Gerente", atendente.tratarReclamacao(reclamacao));
    }

    @Test
    void financeiroDeveResolverReclamacaoDeEstornoNoFinalDaCadeia() {
        Reclamacao reclamacao = new Reclamacao(TipoReclamacaoEstorno.getInstancia());
        // Entra no Atendente -> passa pelo Gerente -> Financeiro resolve
        assertEquals("Financeiro", atendente.tratarReclamacao(reclamacao));
    }

    @Test
    void deveRetornarSemSolucaoQuandoNenhumFuncionarioSouberTratar() {
        // Criamos um tipo anônimo de reclamação que não está na lista de ninguém
        TipoReclamacao tipoDesconhecido = new TipoReclamacao() {};
        Reclamacao reclamacao = new Reclamacao(tipoDesconhecido);

        assertEquals("Sem solução definida", atendente.tratarReclamacao(reclamacao));
    }

    @Test
    void deveRetornarSemSolucaoSeACadeiaForQuebradaIntencionalmente() {
        // Cenário Crítico: Um atendente novato que não foi configurado com um superior
        FuncionarioHamburgueria atendenteIsolado = new FuncionarioAtendente(null);

        // Ele recebe um pedido de estorno, não sabe resolver e não tem pra quem repassar (superior == null)
        Reclamacao reclamacaoEstorno = new Reclamacao(TipoReclamacaoEstorno.getInstancia());

        assertEquals("Sem solução definida", atendenteIsolado.tratarReclamacao(reclamacaoEstorno));
    }

    @Test
    void deveLancarExcecaoAoReceberReclamacaoNula() {
        // Programação defensiva contra o famigerado NullPointerException
        Exception excecao = assertThrows(NullPointerException.class, () -> {
            atendente.tratarReclamacao(null);
        });

        // Validação (se você criou uma mensagem customizada na sua classe base, coloque-a aqui)
        assertNotNull(excecao);
    }
}