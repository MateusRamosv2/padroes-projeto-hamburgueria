package hamburgueria.bridge;
import hamburgueria.core.Item;
import hamburgueria.state.EstadoPedido;
import hamburgueria.state.Recebido;
import java.util.ArrayList;
import java.util.List;


public abstract class Pedido {
    protected FormaPagamento formaPagamento;
    protected EstadoPedido estadoAtual;
    protected List<Item> itens = new ArrayList<>();

    public Pedido(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.estadoAtual = new Recebido(); // Estado inicial
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void avancarEstado() {
        this.estadoAtual = this.estadoAtual.avancar();
    }

    public void cancelarPedido() {
        this.estadoAtual = this.estadoAtual.cancelar();
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