package hamburgueria.state;

public class EmPreparo implements EstadoPedido {
    public EstadoPedido avancar() { return new Pronto(); }
    public EstadoPedido cancelar() { return new Cancelado(); }
    public String getNomeEstado() { return "Em Preparo"; }
}