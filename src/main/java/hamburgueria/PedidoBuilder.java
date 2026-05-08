package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {
    private FormaPagamento formaPagamento;
    private List<Item> itens = new ArrayList<>();
    private List<ClienteObserver> observadores = new ArrayList<>();
    private String tipoPedido = ""; // "Balcao" ou "Delivery"

    public Pedido build() {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento inválida");
        }
        if (itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens");
        }

        Pedido pedido;
        if (tipoPedido.equalsIgnoreCase("Balcao")) {
            pedido = new PedidoBalcao(formaPagamento);
        } else if (tipoPedido.equalsIgnoreCase("Delivery")) {
            pedido = new PedidoDelivery(formaPagamento);
        } else {
            throw new IllegalArgumentException("Tipo de pedido inválido");
        }

        for (Item item : itens) {
            pedido.adicionarItem(item);
        }
        for (ClienteObserver obs : observadores) {
            pedido.addObservador(obs);
        }

        return pedido;
    }

    public PedidoBuilder setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        return this;
    }

    public PedidoBuilder adicionarItem(Item item) {
        this.itens.add(item);
        return this;
    }

    public PedidoBuilder adicionarCliente(ClienteObserver cliente) {
        this.observadores.add(cliente);
        return this;
    }

    public PedidoBuilder setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
        return this;
    }
}