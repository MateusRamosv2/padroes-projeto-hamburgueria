package hamburgueria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonitorCozinha implements Iterable<Pedido> {

    private List<Pedido> filaDePreparo = new ArrayList<>();
    private String filtroAtivo = null;

    public void receberPedido(Pedido pedido) {
        this.filaDePreparo.add(pedido);
    }


    public void setFiltroAtivo(String tipoPedido) {
        this.filtroAtivo = tipoPedido;
    }

    public void limparFiltro() {
        this.filtroAtivo = null;
    }

    @Override
    public Iterator<Pedido> iterator() {

        return new IteratorPedidosCozinha(filaDePreparo, filtroAtivo);
    }

    // --- NOVO MÉTODO (Encapsulando a lógica para o teste) ---
    public List<Pedido> obterPedidosVisiveis() {
        List<Pedido> pedidosVisiveis = new ArrayList<>();

        for (Pedido pedido : this) {
            pedidosVisiveis.add(pedido);
        }
        return pedidosVisiveis;
    }

}