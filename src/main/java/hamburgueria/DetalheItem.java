package hamburgueria;

public class DetalheItem {
    private String nome;
    private float precoBase;
    private String urlImagemAltaResolucao;
    private String dadosNutricionais;

    public DetalheItem(String nome, float precoBase, String urlImagemAltaResolucao, String dadosNutricionais) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.urlImagemAltaResolucao = urlImagemAltaResolucao;
        this.dadosNutricionais = dadosNutricionais;
    }

    public String getNome() { return nome; }
    public float getPrecoBase() { return precoBase; }


    public String renderizarParaApp() {
        return "Exibindo [" + urlImagemAltaResolucao + "] - " + dadosNutricionais;
    }
}