package hamburgueria;

public class SetorAdministracao implements Setor {
    private static final SetorAdministracao instancia = new SetorAdministracao();

    private SetorAdministracao() {}

    public static SetorAdministracao getInstancia() {
        return instancia;
    }

    @Override
    public String receberReclamacao(String mensagem) {
        return "A Administração lamenta o ocorrido e tomará providências sobre: " + mensagem;
    }

    @Override
    public String receberElogio(String mensagem) {
        return "A Administração fica feliz com o seu feedback positivo sobre: " + mensagem;
    }

    @Override
    public String receberSugestao(String mensagem) {
        return "A Administração agradece e enviará para análise da diretoria a sugestão: " + mensagem;
    }
}