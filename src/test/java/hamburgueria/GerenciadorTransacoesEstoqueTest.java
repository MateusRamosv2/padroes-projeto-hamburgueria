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

}