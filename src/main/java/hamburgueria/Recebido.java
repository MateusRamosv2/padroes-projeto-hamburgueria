package hamburgueria;

public class Recebido implements EstadoPedido {
    public EstadoPedido avancar() { return new EmPreparo(); }
    public EstadoPedido cancelar() { return new Cancelado(); }
    public String getNomeEstado() { return "Recebido"; }
}