package hamburgueria;

public class OuvidoriaHamburgueria {
    private static final OuvidoriaHamburgueria instancia = new OuvidoriaHamburgueria();

    private OuvidoriaHamburgueria() {}

    public static OuvidoriaHamburgueria getInstancia() {
        return instancia;
    }

    // Mediação para a Cozinha
    public String receberElogioCozinha(String mensagem) {
        return "Ouvidoria: Recebemos seu contato.\n" +
                "A Cozinha respondeu: " + SetorCozinha.getInstancia().receberElogio(mensagem);
    }

    public String receberReclamacaoCozinha(String mensagem) {
        return "Ouvidoria: Recebemos seu contato.\n" +
                "A Cozinha respondeu: " + SetorCozinha.getInstancia().receberReclamacao(mensagem);
    }

    // Mediação para a Administração
    public String receberElogioAdministracao(String mensagem) {
        return "Ouvidoria: Recebemos seu contato.\n" +
                "A Administração respondeu: " + SetorAdministracao.getInstancia().receberElogio(mensagem);
    }

    public String receberReclamacaoAdministracao(String mensagem) {
        return "Ouvidoria: Recebemos seu contato.\n" +
                "A Administração respondeu: " + SetorAdministracao.getInstancia().receberReclamacao(mensagem);
    }
}