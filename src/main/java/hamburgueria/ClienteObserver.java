package hamburgueria;

public interface ClienteObserver {
    void notificarStatus(String novoEstado);
    void notificarDevolucao();
}