package hamburgueria;

public class SetorCozinha implements Setor {
    private static final SetorCozinha instancia = new SetorCozinha();

    private SetorCozinha() {}

    public static SetorCozinha getInstancia() {
        return instancia;
    }

    @Override
    public String receberReclamacao(String mensagem) {
        return "A Cozinha revisará o preparo referente à reclamação: " + mensagem;
    }

    @Override
    public String receberElogio(String mensagem) {
        return "A Cozinha agradece o elogio e continuará mantendo o padrão no item: " + mensagem;
    }

    @Override
    public String receberSugestao(String mensagem) {
        return "A Cozinha avaliará a inclusão do ingrediente/método sugerido: " + mensagem;
    }
}