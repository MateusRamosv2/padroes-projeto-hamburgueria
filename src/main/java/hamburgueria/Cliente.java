package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements ClienteObserver {
    private final String nome;
    private final List<String> notificacoes;

    public Cliente(String nome) {
        this.nome = nome;
        this.notificacoes = new ArrayList<>();
    }

    public void notificarStatus(String novoEstado) {
        this.notificacoes.add(nome + ", seu pedido está: " + novoEstado);
    }

    public void notificarDevolucao() {
        this.notificacoes.add(nome + ", confirmamos a devolução do seu pedido.");
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }


    // --- Novos métodos para o Padrão Mediator ---

    public String elogiarCozinha(String mensagem) {
        return OuvidoriaHamburgueria.getInstancia().receberElogioCozinha(mensagem);
    }

    public String reclamarCozinha(String mensagem) {
        return OuvidoriaHamburgueria.getInstancia().receberReclamacaoCozinha(mensagem);
    }

    public String elogiarAdministracao(String mensagem) {
        return OuvidoriaHamburgueria.getInstancia().receberElogioAdministracao(mensagem);
    }

    public String reclamarAdministracao(String mensagem) {
        return OuvidoriaHamburgueria.getInstancia().receberReclamacaoAdministracao(mensagem);
    }


}