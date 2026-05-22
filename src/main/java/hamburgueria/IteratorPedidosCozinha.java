package hamburgueria;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorPedidosCozinha implements Iterator<Pedido> {

    private List<Pedido> pedidos;
    private int posicaoAtual = 0;
    private String filtroTipo;

    public IteratorPedidosCozinha(List<Pedido> pedidos, String filtroTipo) {
        this.pedidos = pedidos;
        this.filtroTipo = filtroTipo;
    }

    @Override
    public boolean hasNext() {

        while (posicaoAtual < pedidos.size()) {
            Pedido pedido = pedidos.get(posicaoAtual);

            boolean tipoValido = (filtroTipo == null || pedido.getTipoPedido().equalsIgnoreCase(filtroTipo));
            boolean naoCancelado = !pedido.isCancelado();

            if (tipoValido && naoCancelado) {
                return true;
            }

            posicaoAtual++;
        }
        return false;
    }

    @Override
    public Pedido next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Não há mais pedidos na fila.");
        }
        Pedido pedido = pedidos.get(posicaoAtual);
        posicaoAtual++;
        return pedido;
    }
}