import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int PAREDE = -1;
        int GRAMA = 1;
        int LAMA = 5;

        System.out.println("Digite o tamanho do mapa (n x n): ");
        int tamanho = scanner.nextInt();

        System.out.println("Digite a quantidade de paredes: ");
        int quantidadeParedes = scanner.nextInt();

        int mapa[][] = new int[tamanho][tamanho];

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = random.nextBoolean() ? GRAMA : LAMA;
            }
        }

        int paredesColocadas = 0;
        while (paredesColocadas < quantidadeParedes) {
            int x = random.nextInt(tamanho);
            int y = random.nextInt(tamanho);

            if (mapa[x][y] != PAREDE && !((x == 0 && y == 0) || (x == tamanho - 1 && y == tamanho - 1))) {
                mapa[x][y] = PAREDE;
                paredesColocadas++;
            }
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("A ");
                } else if (i == tamanho - 1 && j == tamanho - 1) {
                    System.out.print("B ");
                } else {
                    switch (mapa[i][j]) {
                        case -1:
                            System.out.print("# ");
                            break;
                        case 1:
                            System.out.print("1 ");
                            break;
                        case 5:
                            System.out.print("5 ");
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println("\nIniciando Busca em Largura...");
        Service.buscaEmLargura(mapa, tamanho);

        System.out.println("\nIniciando Busca em Profundidade...");
        Service.buscaEmProfundidade(mapa, tamanho);

        System.out.println("\nIniciando Busca em Gulosa...");
        Service.buscaGulosa(mapa, tamanho);

        System.out.println("\nIniciando Busca em A*...");
        Service.buscaAEstrela(mapa, tamanho);

    }

}
