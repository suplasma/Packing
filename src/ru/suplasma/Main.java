package ru.suplasma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, i = 0, n;

        System.out.println("Количество блоков: ");
        n = scanner.nextInt();

        int[][] blocksSize = new int[n][6];

        while (i < n) {
            do {
                System.out.println("Ширина блока " + (i + 1) + ":");
                a = scanner.nextInt();
            } while (a <= 0);

            blocksSize[i][0] = a;

            do {
                System.out.println("Высота блока " + (i + 1) + ":");
                a = scanner.nextInt();
            } while (a <= 0);

            blocksSize[i][1] = a;

            blocksSize[i][5] = i++;
        }

        int width, height;

        System.out.println("Ширина контейнера: ");
        width = scanner.nextInt();
        System.out.println("Высота контейнера: ");
        height = scanner.nextInt();


        scanner.close();

        new Algorithm(blocksSize, width, height);
    }
}