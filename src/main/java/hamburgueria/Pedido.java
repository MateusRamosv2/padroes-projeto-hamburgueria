package hamburgueria;
import java.util.ArrayList;
import java.util.List;


public abstract class Pedido {
    protected FormaPagamento formaPagamento;
    protected EstadoPedido estadoAtual;
    protected List<Item> itens = new ArrayList<>();

    protected List<ClienteObserver> observadores = new ArrayList<>();

    public Pedido(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.estadoAtual = new Recebido(); // Estado inicial
    }


    // Métodos para o Padrão Observer
    public void addObservador(ClienteObserver o) {
        this.observadores.add(o);
    }



    public void notificarObservadores() {
        for (ClienteObserver o : observadores) {
            o.notificarStatus(this.estadoAtual.getNomeEstado());
        }
    }


    public void notificarDevolucaoObservadores() {
        for (ClienteObserver o : observadores) {
            o.notificarDevolucao();
        }
    }


    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void avancarEstado() {
        this.estadoAtual = this.estadoAtual.avancar();
        notificarObservadores();
    }

    public void cancelarPedido() {
        this.estadoAtual = this.estadoAtual.cancelar();
        notificarObservadores();
    }


    public void devolverPedido() {

        this.estadoAtual = new Devolucao();
        notificarDevolucaoObservadores();
    }

    public String getStatus() {
        return this.estadoAtual.getNomeEstado();
    }

    protected float somarItens() {
        float total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public abstract float calcularTotalFinal();
}