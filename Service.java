import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Service {

    public static int CalcularDistancia(int linhaAtual, int colunaAtual, int proximaLinha, int proximaColuna) {
        int diferencaLinha = linhaAtual - proximaLinha;
        int diferencaColuna = colunaAtual - proximaColuna;

        if (diferencaLinha < 0) {
            diferencaLinha = diferencaLinha * -1;
        }

        if (diferencaColuna < 0) {
            diferencaColuna = diferencaColuna * -1;
        }

        return diferencaLinha + diferencaColuna;
    }

    public static void buscaAEstrela(int mapa[][], int tamanho) {
        long tempoInicio = System.nanoTime();
        int nosExpandidos = 0;

        PriorityQueue<NoDeBusca> filaPrioridade = new PriorityQueue<>(
                (a, b) -> Integer.compare((a.custoAcumulado + a.estimativa), (b.estimativa + b.custoAcumulado)));
        boolean[][] nosVisitados = new boolean[tamanho][tamanho];

        NoDeBusca noInicial = new NoDeBusca(0, 0, 0, CalcularDistancia(0, 0, tamanho - 1, tamanho - 1), null);
        filaPrioridade.add(noInicial);
        nosVisitados[0][0] = true;

        int[] direcoesLinha = { -1, 1, 0, 0 };
        int[] direcoesColuna = { 0, 0, -1, 1 };

        while (!filaPrioridade.isEmpty()) {
            NoDeBusca noAtual = filaPrioridade.poll();
            nosExpandidos++;
            if (noAtual.linha == tamanho - 1 && noAtual.coluna == tamanho - 1) {
                Long tempoFim = System.nanoTime();
                double TempoExecucao = (tempoFim - tempoInicio) / 1000000.0;
                ExibirResultado(noAtual, nosExpandidos, TempoExecucao);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int novaLinha = noAtual.linha + direcoesLinha[i];
                int novaColuna = noAtual.coluna + direcoesColuna[i];
                if (novaLinha >= 0 && novaLinha < tamanho && novaColuna >= 0 && novaColuna < tamanho) {
                    if (mapa[novaLinha][novaColuna] != -1 && !nosVisitados[novaLinha][novaColuna]) {
                        nosVisitados[novaLinha][novaColuna] = true;
                        int novoCustoAcumulado = noAtual.custoAcumulado + mapa[novaLinha][novaColuna];
                        NoDeBusca vizinho = new NoDeBusca(novaLinha, novaColuna, novoCustoAcumulado,
                                CalcularDistancia(novaLinha, novaColuna, tamanho - 1, tamanho - 1), noAtual);
                        filaPrioridade.add(vizinho);
                    }
                }
            }

        }
        System.out.println("Caminho não encontrado!");
    }

    public static void buscaGulosa(int mapa[][], int tamanho) {
        long tempoInicio = System.nanoTime();
        int nosExpandidos = 0;

        PriorityQueue<NoDeBusca> filaPrioridade = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.estimativa, b.estimativa));
        boolean[][] nosVisitados = new boolean[tamanho][tamanho];

        NoDeBusca noInicial = new NoDeBusca(0, 0, 0, CalcularDistancia(0, 0, tamanho - 1, tamanho - 1), null);
        filaPrioridade.add(noInicial);
        nosVisitados[0][0] = true;

        int[] direcoesLinha = { -1, 1, 0, 0 };
        int[] direcoesColuna = { 0, 0, -1, 1 };

        while (!filaPrioridade.isEmpty()) {
            NoDeBusca noAtual = filaPrioridade.poll();
            nosExpandidos++;
            if (noAtual.linha == tamanho - 1 && noAtual.coluna == tamanho - 1) {
                Long tempoFim = System.nanoTime();
                double TempoExecucao = (tempoFim - tempoInicio) / 1000000.0;
                ExibirResultado(noAtual, nosExpandidos, TempoExecucao);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int novaLinha = noAtual.linha + direcoesLinha[i];
                int novaColuna = noAtual.coluna + direcoesColuna[i];
                if (novaLinha >= 0 && novaLinha < tamanho && novaColuna >= 0 && novaColuna < tamanho) {
                    if (mapa[novaLinha][novaColuna] != -1 && !nosVisitados[novaLinha][novaColuna]) {
                        nosVisitados[novaLinha][novaColuna] = true;
                        int novoCustoAcumulado = noAtual.custoAcumulado + mapa[novaLinha][novaColuna];
                        NoDeBusca vizinho = new NoDeBusca(novaLinha, novaColuna, novoCustoAcumulado,
                                CalcularDistancia(novaLinha, novaColuna, tamanho - 1, tamanho - 1), noAtual);
                        filaPrioridade.add(vizinho);
                    }
                }
            }

        }
        System.out.println("Caminho não encontrado!");
    }

    public static void buscaEmProfundidade(int mapa[][], int tamanho) {
        long tempoInicio = System.nanoTime();
        int nosExpandidos = 0;

        Stack<NoDeBusca> pilha = new Stack<>();
        boolean[][] nosVisitados = new boolean[tamanho][tamanho];

        NoDeBusca noInicial = new NoDeBusca(0, 0, 0, mapa[0][0], null);
        pilha.push(noInicial);
        nosVisitados[0][0] = true;

        int[] direcoesLinha = { -1, 1, 0, 0 };
        int[] direcoesColuna = { 0, 0, -1, 1 };

        while (!pilha.isEmpty()) {
            NoDeBusca noAtual = pilha.pop();
            nosExpandidos++;
            if (noAtual.linha == tamanho - 1 && noAtual.coluna == tamanho - 1) {
                long tempoFim = System.nanoTime();
                double tempoExecucao = (tempoFim - tempoInicio) / 1000000.0;
                ExibirResultado(noAtual, nosExpandidos, tempoExecucao);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int novaLinha = noAtual.linha + direcoesLinha[i];
                int novaColuna = noAtual.coluna + direcoesColuna[i];

                if (novaLinha >= 0 && novaLinha < tamanho && novaColuna >= 0 && novaColuna < tamanho) {
                    if (mapa[novaLinha][novaColuna] != -1 && !nosVisitados[novaLinha][novaColuna]) {
                        nosVisitados[novaLinha][novaColuna] = true;
                        int novoCustoAcumulado = noAtual.custoAcumulado + mapa[novaLinha][novaColuna];
                        NoDeBusca vizinho = new NoDeBusca(novaLinha, novaColuna, novoCustoAcumulado, 0, noAtual);
                        pilha.push(vizinho);
                    }
                }
            }
        }
        System.out.println("Caminho não encontrado!");
    }

    public static void buscaEmLargura(int mapa[][], int tamanho) {
        long tempoInicio = System.nanoTime();
        int nosExpandidos = 0;

        Queue<NoDeBusca> fila = new LinkedList<>();
        boolean[][] nosVisitados = new boolean[tamanho][tamanho];

        NoDeBusca noInicial = new NoDeBusca(0, 0, 0, mapa[0][0], null);
        fila.add(noInicial);
        nosVisitados[0][0] = true;

        int[] direcoesLinha = { -1, 1, 0, 0 };
        int[] direcoesColuna = { 0, 0, -1, 1 };

        while (!fila.isEmpty()) {
            NoDeBusca noAtual = fila.poll();
            nosExpandidos++;

            if (noAtual.linha == tamanho - 1 && noAtual.coluna == tamanho - 1) {
                long tempoFim = System.nanoTime();
                double tempoExecucao = (tempoFim - tempoInicio) / 1000000.0;

                ExibirResultado(noAtual, nosExpandidos, tempoExecucao);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int novaLinha = noAtual.linha + direcoesLinha[i];
                int novaColuna = noAtual.coluna + direcoesColuna[i];

                if (novaLinha >= 0 && novaLinha < tamanho && novaColuna >= 0 && novaColuna < tamanho) {
                    if (mapa[novaLinha][novaColuna] != -1 && !nosVisitados[novaLinha][novaColuna]) {
                        nosVisitados[novaLinha][novaColuna] = true;
                        int novoCustoAcumulado = noAtual.custoAcumulado + mapa[novaLinha][novaColuna];
                        NoDeBusca vizinho = new NoDeBusca(novaLinha, novaColuna, novoCustoAcumulado, 0, noAtual);
                        fila.add(vizinho);
                    }
                }
            }
        }
        System.out.println("Caminho não encontrado!");
    }

    public static void ExibirResultado(NoDeBusca destino, int nosExpandidos, double tempoExecucao) {
        System.out.println("Caminho encontrado! Custo total: " + destino.custoAcumulado);
        System.out.println("Nós expandidos: " + nosExpandidos);
        System.out.println("Tempo de execução: " + tempoExecucao + " ms");

        List<String> caminho = new ArrayList<>();
        NoDeBusca atual = destino;

        while (atual != null) {
            caminho.add("(" + atual.linha + ", " + atual.coluna + ")");
            atual = atual.pai;
        }

        Collections.reverse(caminho);
        System.out.println("Caminho percorrido: " + String.join(" -> ", caminho));
    }

}
