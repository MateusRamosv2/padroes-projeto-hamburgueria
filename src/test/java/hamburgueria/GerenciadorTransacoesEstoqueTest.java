package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorTransacoesEstoqueTest {

    private EstoqueIngredientes estoque;
    private GerenciadorTransacoesEstoque gerenciador;

    @BeforeEach
    void setUp() {
        estoque = new EstoqueIngredientes();
        gerenciador = new GerenciadorTransacoesEstoque();

        estoque.adicionarEstoque("Hambúrguer de Carne", 100);
        estoque.adicionarEstoque("Pão Brioche", 100);
    }

    // --- TESTES DA EXECUÇÃO DO COMMAND ---

    @Test
    void deveDarBaixaNoEstoqueAoProcessarPedido() {
        ComandoEstoque baixaCarne = new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 2);
        gerenciador.processarTransacao(baixaCarne);

        assertEquals(98, estoque.consultarEstoque("Hambúrguer de Carne"));
    }

    @Test
    void deveLancarExcecaoAoTentarBaixarMaisDoQueOEstoquePermite() {
        ComandoEstoque baixaInvalida = new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 150);

        // O assertThrows por si só já atua como a única asserção deste teste
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.processarTransacao(baixaInvalida);
        });

        assertEquals("Estoque insuficiente para o ingrediente: Hambúrguer de Carne", exception.getMessage());
    }

    // --- TESTES DO ESTORNO (CTRL+Z DO COMMAND) DESMEMBRADOS ---

    @Test
    void deveRestaurarOEstoqueApenasDoUltimoComandoAoEstornar() {
        // Executa dois comandos em sequência
        gerenciador.processarTransacao(new ComandoBaixarEstoque(estoque, "Pão Brioche", 1));
        gerenciador.processarTransacao(new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 1));

        // Aciona o estorno (deve afetar APENAS o último comando: o Hambúrguer)
        gerenciador.estornarUltimaTransacao();

        // Como a carne foi estornada, o estoque deve ter voltado para 100
        assertEquals(100, estoque.consultarEstoque("Hambúrguer de Carne"));
    }

    @Test
    void naoDeveAlterarOEstoqueDeComandosAnterioresAoEstornarOUltimo() {

        gerenciador.processarTransacao(new ComandoBaixarEstoque(estoque, "Pão Brioche", 1));
        gerenciador.processarTransacao(new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 1));

        gerenciador.estornarUltimaTransacao();

        assertEquals(99, estoque.consultarEstoque("Pão Brioche"));
    }
}