package ru.suplasma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, i = 0, n;

        int width, height;

        System.out.println("Ширина контейнера: ");
        width = scanner.nextInt();
        System.out.println("Высота контейнера: ");
        height = scanner.nextInt();

        System.out.println("Количество блоков: ");
        n = scanner.nextInt();

        int[][] sizeBlock = new int[n][2];

        while (i < n) {
            do {
                System.out.println("Ширина блока " + (i + 1) + ":");
                a = scanner.nextInt();
            } while (a <= 0 || a > width);

            sizeBlock[i][0] = a;

            do {
                System.out.println("Высота блока " + (i + 1) + ":");
                a = scanner.nextInt();
            } while (a <= 0 || a > height);

            sizeBlock[i++][1] = a;
        }


        scanner.close();

        new Algorithm(sizeBlock, width, height);
    }
}