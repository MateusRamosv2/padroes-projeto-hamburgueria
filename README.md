#  Sistema de Gestão de Hamburgueria - Arquitetura e Projeto de Software
Este repositório contém o código-fonte de um sistema corporativo completo para uma rede de hamburguerias. O projeto foi desenvolvido como um laboratório prático e intensivo de **Arquitetura de Software**, aplicando rigorosamente os **Design Patterns (Padrões de Projeto) do GoF**.

Através de uma arquitetura escalável e focada nos princípios **SOLID**, o sistema gerencia desde a customização de hambúrgueres no totem de autoatendimento, regras de negócio e controle de estoque, até a integração com APIs logísticas e segurança de módulos financeiros.

---

## Objetivos e Soluções Arquiteturais
- **Eliminação de Complexidade Ciclomática:** Remoção de cadeias de `if/else` através de Polimorfismo, State e Strategy.
- **Desacoplamento:** Uso de Mediator para comunicação entre setores e Observer para notificação de clientes, garantindo baixo acoplamento.
- **Integração Sistêmica:** Harmonização de múltiplos padrões operando simultaneamente (ex: um *Builder* que monta um *Pedido* contendo *Composite* de Itens, cujo preço é calculado por *Template Method* e pago via *Bridge/Strategy*, sendo despachado via *Adapter*).

---

## Padrões de Projeto Implementados

### 🛠️ Padrões Criacionais (Criação de Objetos)
1. **Builder:** `PedidoBuilder` constrói gradativamente objetos complexos (`Pedido`), mantendo o estado dos itens e forma de pagamento até a montagem final (`build()`).
2. **Factory Method / Abstract Factory:** `FabricaComboTradicional` encapsula a criação padronizada de famílias de produtos (Hambúrguer, Batata, Bebida).
3. **Prototype:** O `Cardapio` mantém instâncias base de Combos que implementam `Cloneable`. O cliente recebe um `.clone()` para customizar sem alterar a receita original.
4. **Singleton:** Classes globais e únicas no ciclo de vida da aplicação (`Configuracao`, `OuvidoriaHamburgueria` e validadores como `Estoque`).

### Padrões Estruturais (Composição e Interfaces)
5. **Adapter:** `LoggiAdapter` adapta a interface do nosso ERP (`IntegracaoLogistica`) para os métodos incompatíveis da API externa (`ApiLoggiExterna`).
6. **Bridge:** A abstração `Pedido` está separada da sua implementação `FormaPagamento`, permitindo evoluções independentes (ex: criar PedidoApp sem afetar o PagamentoPix).
7. **Composite:** O `Combo` implementa `Item` e contém uma lista de `Item`, tratando elementos individuais e agrupamentos de forma idêntica.
8. **Decorator:** `AdicionalDecorator` (`Bacon`, `Queijo`) envelopa objetos `Item` dinamicamente, somando preços e descrições sem necessidade de herança múltipla.
9. **Facade:** `PedidoFacade` consolida verificações complexas de subsistemas (Estoque, Logística e Financeiro) através de uma única interface simples.
10. **Flyweight:** `DetalheItemFactory` atua como cache, extraindo propriedades intrínsecas e pesadas para a classe `DetalheItem` e compartilhando-as entre milhares de instâncias (ex: `HamburguerCarne`).
11. **Proxy:** `RelatorioFinanceiroProxy` estabelece um *Protection Proxy* (RBAC) e *Virtual Proxy* (Lazy Initialization), bloqueando acessos não autorizados ao `RelatorioFinanceiroReal`.

### Padrões Comportamentais (Comunicação e Algoritmos)
12. **Chain of Responsibility:** A cadeia `FuncionarioAtendente` → `Gerente` → `Financeiro` processa reclamações escalonando-as até o nível de autoridade competente.
13. **Command:** `GerenciadorTransacoesEstoque` atua como *Invoker*, registrando comandos (`ComandoBaixarEstoque`) sobre o *Receiver* (`EstoqueIngredientes`), permitindo o comportamento de estorno (`desfazer()`).
14. **Interpreter:** `MotorBuscaPedidos` processa expressões textuais da gramática de filtros (`OperadorE`, `FiltroValorMaiorQue`) validando regras de negócio em uma Árvore Sintática Abstrata (AST).
15. **Iterator:** `IteratorPedidosCozinha` varre a fila de preparo de forma encapsulada, ocultando a estrutura de dados e filtrando automaticamente pedidos cancelados.
16. **Mediator:** `OuvidoriaHamburgueria` centraliza e roteia o tráfego de comunicação entre o `Cliente` e os setores (`SetorAdministracao`, `SetorCozinha`), promovendo desacoplamento total entre emissor e receptor.
17. **Memento:** `CarrinhoMemento` captura o estado imutável da lista de itens do `ClienteTotem`, permitindo a restauração do carrinho de compras.
18. **Observer:** `Pedido` atua como contexto notificando dinamicamente a coleção de assinantes `ClienteObserver` a cada mudança de status.
19. **State:** A interface `EstadoPedido` encapsula regras de transição. As transições (`Recebido`, `EmPreparo`, `Pronto`) gerenciam seu próprio comportamento.
20. **Strategy:** `CalculadoraPagamento` utiliza polimorfismo (`EstrategiaCartao`, `EstrategiaPix`) para variar algoritmos de cálculo de taxas no momento do fechamento da `Caixa`.
21. **Template Method:** A classe base `Pedido` define o esqueleto do método `calcularTotalFinal()`, delegando variações lógicas aos herdeiros (`PedidoBalcao`, `PedidoDelivery`).
22. **Visitor:** `AuditoriaDietaVisitor` percorre as estruturas (visitando até mesmo *Decorators* e *Composites*) para extrair relatórios paralelos (como tabelas de alergênicos) sem poluir o domínio dos Itens.

---

## Diagrama de Classes Global

Este diagrama detalha a fidelidade arquitetural do sistema, mapeando a segregação de padrões estruturais, criacionais e comportamentais implementados no código-fonte.

```mermaid
classDiagram
    %% ==========================================
    %% SINGLETON & FACADE & MEDIATOR
    %% ==========================================
    class Configuracao {
        <<Singleton>>
        -Configuracao instancia$
        -Configuracao()
        +getInstance()$ Configuracao
    }
    
    class PedidoFacade {
        +autorizarPreparo(pedido: Pedido) boolean
    }
    
    class OuvidoriaHamburgueria {
        <<Mediator>>
        -OuvidoriaHamburgueria instancia$
        +receberReclamacaoCozinha(msg: String)
        +receberElogioAdministracao(msg: String)
    }

    OuvidoriaHamburgueria --> SetorCozinha : notifica
    OuvidoriaHamburgueria --> SetorAdministracao : notifica

    %% ==========================================
    %% BUILDER & FACTORY & PROTOTYPE
    %% ==========================================
    class PedidoBuilder {
        -Pedido pedido
        -List~Item~ itens
        -String tipoPedido
        +setFormaPagamento() PedidoBuilder
        +adicionarItem() PedidoBuilder
        +build() Pedido
    }

    class FabricaCombo {
        <<interface>>
        +criarHamburguer() Item
        +criarBebida() Item
    }
    
    class Cloneable {
        <<interface>>
        +clone() Object
    }
    
    class Cardapio {
        -Map~String, Combo~ combosProntos
        +solicitarCombo(chave: String) Combo
    }

    FabricaCombo <|.. FabricaComboTradicional
    Cloneable <|.. Combo
    Cardapio ..> Combo : clona (Prototype)
    PedidoBuilder o-- Pedido : constrói

    %% ==========================================
    %% CORE: PEDIDO (BRIDGE, TEMPLATE, OBSERVER)
    %% ==========================================
    class Pedido {
        <<abstract>>
        #List~Item~ itens
        #EstadoPedido estadoAtual
        #FormaPagamento formaPagamento
        #List~ClienteObserver~ observadores
        +adicionarItem(item: Item)
        +avancarEstado()
        +cancelarPedido()
        +calcularTotalFinal()* float
    }

    Pedido <|-- PedidoDelivery
    Pedido <|-- PedidoBalcao
    Pedido o-- "*" ClienteObserver : observadores
    Pedido o-- FormaPagamento : delega (Bridge)

    %% ==========================================
    %% COMPOSITE, DECORATOR, VISITOR, FLYWEIGHT
    %% ==========================================
    class Item {
        <<interface>>
        +getDescricao() String
        +getPreco() float
        +aceitar(visitor: VisitorItem) String
    }

    class Combo {
        -List~Item~ itensCombo
        +adicionarItemCombo(item: Item)
    }

    class AdicionalDecorator {
        <<abstract>>
        #Item itemDecorado
    }
    
    class DetalheItem {
        +nome: String
        +calorias: String
    }
    
    class DetalheItemFactory {
        -Map~String, DetalheItem~ cache
        +getDetalhe() DetalheItem
    }
    
    class VisitorItem {
        <<interface>>
        +visitar(HamburguerCarne)
        +visitar(Combo)
    }

    Item <|.. HamburguerCarne
    Item <|.. Combo
    Item <|.. AdicionalDecorator
    AdicionalDecorator <|-- Bacon
    AdicionalDecorator <|-- Queijo
    AdicionalDecorator o-- "1" Item : decora
    Combo o-- "*" Item : contém (Composite)
    HamburguerCarne --> DetalheItemFactory : solicita
    DetalheItemFactory o-- "*" DetalheItem : armazena
    VisitorItem <|.. AuditoriaDietaVisitor
    VisitorItem --> Item : analisa

    %% ==========================================
    %% STRATEGY (PAGAMENTO)
    %% ==========================================
    class EstrategiaPagamento {
        <<interface>>
        +calcularPrecoFinal(valor: float) float
    }
    class CalculadoraPagamento {
        +calcular(estrategia: EstrategiaPagamento)
    }
    
    FormaPagamento <|.. PagamentoPix
    FormaPagamento <|.. PagamentoCartao
    CalculadoraPagamento --> EstrategiaPagamento : utiliza
    EstrategiaPagamento <|.. EstrategiaPix
    EstrategiaPagamento <|.. EstrategiaCartao

    %% ==========================================
    %% STATE (CICLO DE VIDA)
    %% ==========================================
    class EstadoPedido {
        <<interface>>
        +avancar() EstadoPedido
        +cancelar() EstadoPedido
    }

    Pedido *-- "1" EstadoPedido : estado atual
    EstadoPedido <|.. Recebido
    EstadoPedido <|.. EmPreparo
    EstadoPedido <|.. Pronto
    EstadoPedido <|.. Entregue
    EstadoPedido <|.. Cancelado
    EstadoPedido <|.. Devolucao

    %% ==========================================
    %% COMMAND & ADAPTER
    %% ==========================================
    class ComandoEstoque {
        <<interface>>
        +executar()
        +desfazer()
    }
    class GerenciadorTransacoesEstoque {
        -List~ComandoEstoque~ historico
    }
    class EstoqueIngredientes {
        +darBaixa()
        +adicionarEstoque()
    }
    
    class ServicoEntregaExterno {
        <<External API>>
        +requestRider()
    }
    class LoggiAdapter {
        -ApiLoggiExterna api
    }

    ComandoEstoque <|.. ComandoBaixarEstoque
    GerenciadorTransacoesEstoque o-- "*" ComandoEstoque : invoker
    ComandoBaixarEstoque --> EstoqueIngredientes : receiver
    IntegracaoLogistica <|.. LoggiAdapter
    LoggiAdapter --> ServicoEntregaExterno : adapta (ApiLoggiExterna)

    %% ==========================================
    %% CHAIN, PROXY, INTERPRETER
    %% ==========================================
    class FuncionarioHamburgueria {
        <<abstract>>
        -FuncionarioHamburgueria superior
        +tratarReclamacao()
    }
    class RelatorioFinanceiroProxy {
        -RelatorioFinanceiroReal real
        -FuncionarioHamburgueria logado
    }
    class FiltroPedidoExpressao {
        <<interface>>
        +interpretar(pedido: Pedido) boolean
    }

    FuncionarioHamburgueria <|-- FuncionarioAtendente
    FuncionarioHamburgueria <|-- FuncionarioGerente
    FuncionarioHamburgueria o-- FuncionarioHamburgueria : sucessor (Chain)
    RelatorioFinanceiroProxy --> RelatorioFinanceiroReal : controla acesso
    RelatorioFinanceiro <|.. RelatorioFinanceiroProxy
    FiltroPedidoExpressao <|.. MotorBuscaPedidos
    FiltroPedidoExpressao <|.. OperadorE
    FiltroPedidoExpressao <|.. FiltroValorMaiorQue
    MotorBuscaPedidos o-- FiltroPedidoExpressao : constrói AST

   ```

   ---

   ## Diagrama de Estados

O sistema de gestão da hamburgueria utiliza o Padrão **State** para governar de forma estrita o ciclo de vida de cada `Pedido`. Esta abordagem elimina falhas comuns de lógica (como enviar para a chapa um pedido já cancelado) e garante que o objeto mude o seu comportamento dinamicamente de acordo com a sua fase operacional.

---

### Visão Arquitetural da Máquina de Estados

* **Estado Inicial (`Recebido`):** Assim que o `PedidoBuilder` finaliza a construção da instância, o pedido nasce obrigatoriamente no estado `Recebido`. A partir daqui, ele aguarda as validações do `PedidoFacade` para avançar à cozinha.
* **Estados de Transição (`EmPreparo`, `Pronto`):** Representam o fluxo de trabalho natural. Nestas fases, os pedidos podem progredir utilizando o método `avancarEstado()` ou ser interrompidos prematuramente via `cancelarPedido()`.
* **Estados Terminais (`Entregue`, `Cancelado`, `Devolucao`):** Uma vez que o pedido alcança um destes três estados, o seu ciclo de vida é encerrado. Decisão arquitetural de segurança: tentativas de chamar `avancar()` ou `cancelar()` num estado terminal são anuladas retornando a própria instância (`return this;`), prevenindo transições ilegais.
* **Delegação de Responsabilidades (Observer):** O Padrão **State** é responsável apenas pelas regras de transição. É a classe de contexto (`Pedido`) que gerencia a comunicação e aciona o **Observer**, notificando os clientes de forma centralizada após cada mudança de estado bem-sucedida.

O diagrama abaixo ilustra essa máquina de estados operante:

```mermaid
stateDiagram-v2
    direction TB
    
    [*] --> Recebido : Instanciado via Builder
    
    note right of Recebido
        A interface EstadoPedido dita as regras.
        O objeto Pedido aciona o Observer nas transições.
    end note
    
    Recebido --> EmPreparo : avancarEstado()
    Recebido --> Cancelado : cancelarPedido()
    Recebido --> Devolucao : devolverPedido()
    
    EmPreparo --> Pronto : avancarEstado()
    EmPreparo --> Cancelado : cancelarPedido()
    EmPreparo --> Devolucao : devolverPedido()
    
    Pronto --> Entregue : avancarEstado()
    Pronto --> Cancelado : cancelarPedido() 
    Pronto --> Devolucao : devolverPedido()

    %% Transições para Estados Terminais encerram o ciclo de vida
    Entregue --> [*] : finalizar()
    Cancelado --> [*] : encerrar()
    Devolucao --> [*] : estornar_e_encerrar()
    
    note right of Entregue
        Estados Terminais (Finais).
        Chamadas adicionais de avancar() ou 
        cancelar() retornam o próprio estado (return this).
    end note
