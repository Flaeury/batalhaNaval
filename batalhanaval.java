import java.util.Random;
import java.util.Scanner;

public class batalhanaval {
    // Constantes do tabuleiro
    static final char AGUA = '~';
    static final char NAVIO = 'N';
    static final char ACERTO = 'X';
    static final char ERRO = 'O';

    static final int TAMANHO_TABULEIRO = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Jogador vs Jogador");
        System.out.println("2 - Jogador vs Máquina");
        System.out.println("3 - Máquina vs Máquina");
        int modo = scanner.nextInt();

        char[][] jogador1 = gerarCenarioAleatorio();
        char[][] jogador2 = gerarCenarioAleatorio();

        boolean[][] ataques1 = new boolean[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        boolean[][] ataques2 = new boolean[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

        boolean vezDoJogador1 = true;

        while (true) {
            System.out.println("Tabuleiro do adversário:");
            if (vezDoJogador1) {
                exibirCenario(jogador2, true);
            } else {
                exibirCenario(jogador1, true);
            }

            int linha, coluna;

            if (modo == 3 || (modo == 2 && !vezDoJogador1)) {
                // Movimento da máquina
                Random rand = new Random();
                linha = rand.nextInt(TAMANHO_TABULEIRO);
                coluna = rand.nextInt(TAMANHO_TABULEIRO);
                System.out.println("Máquina escolheu: (" + linha + ", " + coluna + ")");
            } else {
                // Movimento do jogador
                System.out.println("Informe a linha e coluna do ataque:");
                linha = scanner.nextInt();
                coluna = scanner.nextInt();
            }

            boolean acerto;
            if (vezDoJogador1) {
                if (verificarAtaqueRepetido(ataques1, linha, coluna)) {
                    System.out.println("Posição já atacada! Tente novamente.");
                    continue;
                }
                acerto = realizarAtaque(jogador2, linha, coluna);
                atualizarCenario(jogador2, linha, coluna, acerto);
                ataques1[linha][coluna] = true;
            } else {
                if (verificarAtaqueRepetido(ataques2, linha, coluna)) {
                    System.out.println("Posição já atacada! Tente novamente.");
                    continue;
                }
                acerto = realizarAtaque(jogador1, linha, coluna);
                atualizarCenario(jogador1, linha, coluna, acerto);
                ataques2[linha][coluna] = true;
            }

            if (verificarVitoria(vezDoJogador1 ? jogador2 : jogador1)) {
                System.out.println("Jogador " + (vezDoJogador1 ? "1" : "2") + " venceu!");
                System.out.println("Tabuleiro final do jogador adversário:");
                exibirCenario(vezDoJogador1 ? jogador2 : jogador1, false);
                break;
            }

            vezDoJogador1 = !vezDoJogador1;
        }
        scanner.close();
    }

    public static char[][] gerarCenarioAleatorio() {
        char[][] cenario = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                cenario[i][j] = AGUA;
            }
        }

        int[] tamanhos = {1, 2, 3, 4, 5};
        Random rand = new Random();

        for (int tamanho : tamanhos) {
            boolean posicionado = false;
            while (!posicionado) {
                int linha = rand.nextInt(TAMANHO_TABULEIRO);
                int coluna = rand.nextInt(TAMANHO_TABULEIRO);
                boolean horizontal = rand.nextBoolean();

                if (podePosicionar(cenario, linha, coluna, tamanho, horizontal)) {
                    for (int i = 0; i < tamanho; i++) {
                        if (horizontal) {
                            cenario[linha][coluna + i] = NAVIO;
                        } else {
                            cenario[linha + i][coluna] = NAVIO;
                        }
                    }
                    posicionado = true;
                }
            }
        }
        return cenario;
    }

    public static boolean podePosicionar(char[][] cenario, int linha, int coluna, int tamanho, boolean horizontal) {
        if (horizontal) {
            if (coluna + tamanho > TAMANHO_TABULEIRO) return false;
            for (int i = 0; i < tamanho; i++) {
                if (cenario[linha][coluna + i] != AGUA) return false;
            }
        } else {
            if (linha + tamanho > TAMANHO_TABULEIRO) return false;
            for (int i = 0; i < tamanho; i++) {
                if (cenario[linha + i][coluna] != AGUA) return false;
            }
        }
        return true;
    }

    public static void exibirCenario(char[][] cenario, boolean ocultarEmbarcacoes) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (ocultarEmbarcacoes && cenario[i][j] == NAVIO) {
                    System.out.print(AGUA + " ");
                } else {
                    System.out.print(cenario[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static boolean realizarAtaque(char[][] cenario, int linha, int coluna) {
        return cenario[linha][coluna] == NAVIO;
    }

    public static void atualizarCenario(char[][] cenario, int linha, int coluna, boolean acerto) {
        cenario[linha][coluna] = acerto ? ACERTO : ERRO;
    }

    public static boolean verificarAtaqueRepetido(boolean[][] ataques, int linha, int coluna) {
        return ataques[linha][coluna];
    }

    public static boolean verificarVitoria(char[][] cenario) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (cenario[i][j] == NAVIO) return false;
            }
        }
        return true;
    }
}
