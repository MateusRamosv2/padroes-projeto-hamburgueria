package hamburgueria;

public interface EstadoPedido {
    EstadoPedido avancar();
    EstadoPedido cancelar();
    String getNomeEstado();
}