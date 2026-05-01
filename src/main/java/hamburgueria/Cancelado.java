package hamburgueria;

public class Cancelado implements EstadoPedido {

    public EstadoPedido avancar() { return this; }
    public EstadoPedido cancelar() { return this; }
    public String getNomeEstado() { return "Cancelado"; }
}