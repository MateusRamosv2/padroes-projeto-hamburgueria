package hamburgueria.observer;

public interface ClienteObserver {
    void notificarStatus(String novoEstado);
    void notificarDevolucao();
}