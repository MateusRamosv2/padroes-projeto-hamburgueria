package hamburgueria.state;

public class Entregue implements EstadoPedido {

    public EstadoPedido avancar() { return this; }
    public EstadoPedido cancelar() { return this; }
    public String getNomeEstado() { return "Entregue"; }
}