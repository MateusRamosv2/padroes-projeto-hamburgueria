package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidadorPedido {

    private List<Pedido> pedidosComPendencia = new ArrayList<Pedido>();

    public void addPedidoPendente(Pedido pedido) {
        this.pedidosComPendencia.add(pedido);
    }

    public boolean verificarPedidoComPendencia(Pedido pedido) {
        return this.pedidosComPendencia.contains(pedido);
    }
}