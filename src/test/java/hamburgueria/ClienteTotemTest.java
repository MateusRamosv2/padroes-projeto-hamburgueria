package hamburgueria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTotemTest {

    private ClienteTotem totem;

    @BeforeEach
    void setUp() {
        totem = new ClienteTotem("Mateus Ramos");

        Item lancheDecorado = new Bacon(new Queijo(new HamburguerCarne()));
        totem.adicionarAoCarrinho(lancheDecorado);
        totem.finalizarCompra();

        Combo combo = new Combo("Combo Básico", 0.0f);
        combo.adicionarItemCombo(new HamburguerCarne());
        totem.adicionarAoCarrinho(combo);
        totem.finalizarCompra();
    }


    @Test
    void deveArmazenarQuantidadeCorretaDePedidosNoHistorico() {
        // Como fiz 2 compras no setUp, o histórico deve ter tamanho 2
        assertEquals(2, totem.getQuantidadePedidosNoHistorico());
    }

    @Test
    void deveEsvaziarCarrinhoAtualAposFinalizarCompra() {
        // Após a última chamada de "finalizarCompra()" no setUp, o carrinho deve zerar
        assertTrue(totem.getCarrinhoAtual().isEmpty());
    }


    @Test
    void deveRestaurarAQuantidadeExataDeItensDoPedidoAnterior() {
        totem.restaurarPedidoAnterior(0); // Restaura a Compra 1
        assertEquals(1, totem.getCarrinhoAtual().size());
    }

    @Test
    void pedidoRestauradoDevePreservarOAdicionalDeBacon() {
        totem.restaurarPedidoAnterior(0); // Restaura a Compra 1
        assertTrue(totem.getCarrinhoAtual().get(0).getDescricao().contains("Bacon"));
    }

    @Test
    void pedidoRestauradoDevePreservarOAdicionalDeQueijo() {
        totem.restaurarPedidoAnterior(0); // Restaura a Compra 1
        assertTrue(totem.getCarrinhoAtual().get(0).getDescricao().contains("Queijo"));
    }


    @Test
    void deveLancarExcecaoAoTentarRestaurarIndiceInexistente() {
        // Padrão moderno do JUnit 5 para testar Exceções
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            totem.restaurarPedidoAnterior(5); // Índice 5 não existe (temos apenas 0 e 1)
        });

        assertEquals("Índice de histórico inválido", excecao.getMessage());
    }
}