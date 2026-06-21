package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HamburguerVeganoTest {

    @Test
    void deveRetornarDescricaoCorretaDoHamburguerVegano() {
        HamburguerVegano vegano = new HamburguerVegano();
        assertEquals("Hambúrguer de Grão de Bico", vegano.getDescricao());
    }

    @Test
    void deveRetornarPrecoCorretoDoHamburguerVegano() {
        HamburguerVegano vegano = new HamburguerVegano();
        assertEquals(28.0f, vegano.getPreco());
    }
}