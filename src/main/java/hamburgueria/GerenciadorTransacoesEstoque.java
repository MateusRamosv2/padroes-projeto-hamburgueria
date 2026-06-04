package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorTransacoesEstoque {

    private List<ComandoEstoque> historicoTransacoes = new ArrayList<>();

    public void processarTransacao(ComandoEstoque comando) {
        comando.executar();
        this.historicoTransacoes.add(comando);
    }

    public void estornarUltimaTransacao() {
        if (!historicoTransacoes.isEmpty()) {

            ComandoEstoque ultimoComando = historicoTransacoes.remove(historicoTransacoes.size() - 1);
            ultimoComando.desfazer();
        }
    }
}