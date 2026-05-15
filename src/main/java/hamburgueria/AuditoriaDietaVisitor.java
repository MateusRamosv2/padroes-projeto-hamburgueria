package hamburgueria;

public class AuditoriaDietaVisitor implements VisitorItem {

    @Override
    public String visitar(HamburguerCarne hamburguer) {
        return "[Carnívoro] Hambúrguer Bovino Tradicional";
    }

    @Override
    public String visitar(BatataFrita batata) {
        return "[Vegano] Batata Frita em óleo vegetal";
    }

    @Override
    public String visitar(Refrigerante refrigerante) {
        return "[Vegano] Bebida gaseificada sem derivados animais";
    }

    @Override
    public String visitar(Bacon bacon) {
        // Magia do Visitor + Decorator: Ele audita o Bacon e manda o visitante auditar o que está embaixo do Bacon!
        return "[Carnívoro/Porco] Adicional de Bacon -> " + bacon.getItemDecorado().aceitar(this);
    }

    @Override
    public String visitar(Queijo queijo) {
        return "[Vegetariano/Laticínio] Adicional de Queijo -> " + queijo.getItemDecorado().aceitar(this);
    }

    @Override
    public String visitar(Combo combo) {

        StringBuilder auditoria = new StringBuilder();
        auditoria.append("--- Auditoria do ").append(combo.getDescricao()).append(" ---\n");

        for (Item item : combo.getItensCombo()) {
            auditoria.append("- ").append(item.aceitar(this)).append("\n");
        }
        return auditoria.toString();
    }

    @Override
    public String visitar(HamburguerVegano hamburguerVegano) {
        return "[Vegano] Hambúrguer à base de plantas/soja";
    }
}