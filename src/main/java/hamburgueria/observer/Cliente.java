package hamburgueria.observer;

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
}