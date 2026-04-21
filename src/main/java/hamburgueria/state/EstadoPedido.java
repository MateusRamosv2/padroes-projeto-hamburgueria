package hamburgueria.state;

public interface EstadoPedido {
    EstadoPedido avancar();
    EstadoPedido cancelar();
    String getNomeEstado();
}