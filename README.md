# Batalha Naval em Java

Este arquivo tem como propósito explicar as funcionalidades criadas para simular um jogo de batalha naval via console.

## Primeiros passos: Escolhas principais no design e na implementação
## 1. Estrutura básica e constantes:

- Constantes do tabuleiro: Os símbolos (AGUA, NAVIO, ACERTO, ERRO) são definidos como constantes para facilitar alterações futuras e melhorar a legibilidade.

- Tamanho do tabuleiro: Uma constante (TAMANHO_TABULEIRO = 10) foi usada para centralizar o controle do tamanho do tabuleiro.
Modos de jogo:

- São suportados três modos:
Jogador vs Jogador
Jogador vs Máquina
Máquina vs Máquina

Essa decisão torna o jogo flexível e oferece diferentes experiências, incluindo partidas completamente automatizadas.

## 2. Tabuleiros e ataques:

- Dois tabuleiros (jogador1 e jogador2) armazenam a posição dos navios.
- Matrizes booleanas (ataques1 e ataques2) rastreiam os ataques realizados, evitando repetições.

## 3. Estratégia de posicionamento de navios:

- O método gerarCenarioAleatorio() distribui os navios aleatoriamente no tabuleiro.

- A função podePosicionar() verifica se uma posição está disponível, garantindo que os navios não se sobreponham e permaneçam dentro do tabuleiro.

## 4. Ciclo do jogo:

Um loop infinito (while (true)) é usado até que um jogador vença.
A troca de turnos é feita pela variável vezDoJogador1.

## 5. Detalhamento das funções

gerarCenarioAleatorio():

- Cria um tabuleiro com navios de diferentes tamanhos.
Escolha do tamanho: Um conjunto fixo de tamanhos ({1, 2, 3, 4, 5}) foi usado para simular a diversidade das embarcações.

podePosicionar():

- Garante que o posicionamento de um navio é válido.
Verifica tanto posições horizontais quanto verticais.

exibirCenario():

- Mostra o tabuleiro no console.
O parâmetro ocultarEmbarcacoes esconde os navios do adversário, exceto os atingidos, para preservar a mecânica do jogo.

realizarAtaque():

Determina se um ataque acertou ou não.
Retorna true se um navio foi atingido, false caso contrário.

atualizarCenario():

- Atualiza o tabuleiro após um ataque, marcando com X (acerto) ou O (erro).

verificarAtaqueRepetido():

- Previne ataques repetidos em uma mesma posição, garantindo uma experiência mais justa.

verificarVitoria():

Checa se ainda há navios no tabuleiro adversário.
A vitória ocorre quando todos os navios são destruídos.

## 6. Razões por trás das escolhas
Uso de matrizes para o tabuleiro:

Matrizes bidimensionais são naturais para representar um tabuleiro quadrado.
Facilitam operações como verificar posições específicas e iterar pelo tabuleiro.
Jogadas automáticas para a máquina:

O Random é usado para simular ataques aleatórios, simplificando a lógica da IA.
Em um projeto futuro, estratégias mais avançadas podem ser adicionadas.
Separação de responsabilidades:

Cada método lida com uma tarefa específica (posicionar, exibir, atacar, etc.).
Isso melhora a legibilidade e facilita manutenção e testes.
Mensagens no console:

O programa é interativo, exibindo o estado atual do jogo e instruções para os jogadores.

## 7. Testes manuais
O jogo foi executado em diferentes configurações para verificar o comportamento geral:

- Teste dos modos de jogo:
Procedimento:
Simular partidas em todos os modos:
Jogador vs Jogador: Garantir que a interação com o teclado funciona corretamente.
Jogador vs Máquina: Testar a alternância entre movimentos automáticos e manuais.
Máquina vs Máquina: Observar o fluxo até a vitória de um dos lados.
Resultado esperado: Todos os modos funcionam como esperado, sem travamentos.