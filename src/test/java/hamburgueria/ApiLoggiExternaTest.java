package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiLoggiExternaTest {

    @Test
    void deveLancarExcecaoQuandoApiKeyForNula() {
        ApiLoggiExterna api = new ApiLoggiExterna();
        Exception excecao = assertThrows(SecurityException.class, () -> {
            api.requestRider(null, "Rua A", 50.0);
        });
        assertEquals("Acesso negado: API Key inválida na Loggi.", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoApiKeyForVazia() {
        ApiLoggiExterna api = new ApiLoggiExterna();
        Exception excecao = assertThrows(SecurityException.class, () -> {
            api.requestRider("", "Rua A", 50.0);
        });
        assertEquals("Acesso negado: API Key inválida na Loggi.", excecao.getMessage());
    }
}