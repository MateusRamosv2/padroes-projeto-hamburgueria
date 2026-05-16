package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class ClienteTotem {
    private String nomeCliente;
    private List<Item> carrinhoAtual;
    private List<CarrinhoMemento> historicoPedidos;

    public ClienteTotem(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.carrinhoAtual = new ArrayList<>();
        this.historicoPedidos = new ArrayList<>();
    }

    public String getNomeCliente() { return nomeCliente; }

    public void adicionarAoCarrinho(Item item) {
        this.carrinhoAtual.add(item);
    }

    public List<Item> getCarrinhoAtual() {
        return this.carrinhoAtual;
    }

    public int getQuantidadePedidosNoHistorico() {
        return this.historicoPedidos.size();
    }


    public void finalizarCompra() {
        if (!this.carrinhoAtual.isEmpty()) {
            this.historicoPedidos.add(new CarrinhoMemento(this.carrinhoAtual));
            this.carrinhoAtual.clear();
        }
    }


    public void restaurarPedidoAnterior(int indice) {
        if (indice < 0 || indice >= this.historicoPedidos.size()) {
            throw new IllegalArgumentException("Índice de histórico inválido");
        }


        CarrinhoMemento memento = this.historicoPedidos.get(indice);


        this.carrinhoAtual = new ArrayList<>(memento.getItensCapturados());
    }
}