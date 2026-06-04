package hamburgueria;

public class ComandoBaixarEstoque implements ComandoEstoque {
    private EstoqueIngredientes estoque;
    private String ingrediente;
    private int quantidade;

    public ComandoBaixarEstoque(EstoqueIngredientes estoque, String ingrediente, int quantidade) {
        this.estoque = estoque;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    @Override
    public void executar() {

        estoque.darBaixa(ingrediente, quantidade);
    }

    @Override
    public void desfazer() {

        estoque.adicionarEstoque(ingrediente, quantidade);
    }
}