package hamburgueria;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MotorBuscaPedidos implements FiltroPedidoExpressao {

    private FiltroPedidoExpressao arvoreSintatica;

    public MotorBuscaPedidos(String queryBusca) {
        Stack<FiltroPedidoExpressao> pilha = new Stack<>();
        List<String> tokens = Arrays.asList(queryBusca.split(" "));
        Iterator<String> iterator = tokens.iterator();

        while (iterator.hasNext()) {
            String token = iterator.next();

            if (token.equals("TIPO")) {
                if (!iterator.hasNext()) throw new IllegalArgumentException("Falta o tipo do pedido (ex: Delivery)");
                pilha.push(new FiltroTipoPedido(iterator.next()));

            } else if (token.equals("VALOR_MAIOR_QUE")) {
                if (!iterator.hasNext()) throw new IllegalArgumentException("Falta o valor alvo");
                pilha.push(new FiltroValorMaiorQue(Float.parseFloat(iterator.next())));

            } else if (token.equals("E")) {
                FiltroPedidoExpressao esquerda = pilha.pop();
                FiltroPedidoExpressao direita = parseProximoTokenTerminal(iterator);
                pilha.push(new OperadorE(esquerda, direita));

            } else if (token.equals("OU")) {
                FiltroPedidoExpressao esquerda = pilha.pop();
                FiltroPedidoExpressao direita = parseProximoTokenTerminal(iterator);
                pilha.push(new OperadorOu(esquerda, direita));

            } else {
                throw new IllegalArgumentException("Token de busca inválido: " + token);
            }
        }
        arvoreSintatica = pilha.pop();
    }

    private FiltroPedidoExpressao parseProximoTokenTerminal(Iterator<String> iterator) {
        String token = iterator.next();
        if (token.equals("TIPO")) {
            return new FiltroTipoPedido(iterator.next());
        }
        if (token.equals("VALOR_MAIOR_QUE")) {
            return new FiltroValorMaiorQue(Float.parseFloat(iterator.next()));
        }
        throw new IllegalArgumentException("Filtro inválido no lado direito do operador lógico");
    }

    @Override
    public boolean interpretar(Pedido pedido) {
        return arvoreSintatica.interpretar(pedido);
    }
}