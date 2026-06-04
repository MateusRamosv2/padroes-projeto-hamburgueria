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

    @Test
    void deveDarBaixaNoEstoqueAoProcessarPedido() {

        ComandoEstoque baixaCarne = new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 2);
        gerenciador.processarTransacao(baixaCarne);


        assertEquals(98, estoque.consultarEstoque("Hambúrguer de Carne"));
    }

    @Test
    void deveLancarExcecaoAoTentarBaixarMaisDoQueOEstoquePermite() {

        ComandoEstoque baixaInvalida = new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 150);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.processarTransacao(baixaInvalida);
        });

        assertEquals("Estoque insuficiente para o ingrediente: Hambúrguer de Carne", exception.getMessage());
    }

    @Test
    void deveEstornarIngredientesSeOPedidoForCancelado() {
        ComandoEstoque baixaPao = new ComandoBaixarEstoque(estoque, "Pão Brioche", 1);
        ComandoEstoque baixaCarne = new ComandoBaixarEstoque(estoque, "Hambúrguer de Carne", 1);

        gerenciador.processarTransacao(baixaPao);
        gerenciador.processarTransacao(baixaCarne);

        assertEquals(99, estoque.consultarEstoque("Pão Brioche"));
        assertEquals(99, estoque.consultarEstoque("Hambúrguer de Carne"));


        gerenciador.estornarUltimaTransacao();

        assertEquals(99, estoque.consultarEstoque("Pão Brioche"));
        assertEquals(100, estoque.consultarEstoque("Hambúrguer de Carne"));
    }

}