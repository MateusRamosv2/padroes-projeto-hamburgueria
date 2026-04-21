package hamburgueria.state;

public class Pronto implements EstadoPedido {
    public EstadoPedido avancar() { return new Entregue(); }
    public EstadoPedido cancelar() { return new Cancelado(); }
    public String getNomeEstado() { return "Pronto"; }
}