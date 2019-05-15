package ru.suplasma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, i = 0, n;

        int width, height;

        System.out.println("Ширина контейнера: ");
        //width = scanner.nextInt();
        width = 15;
        System.out.println("Высота контейнера: ");
        // height = scanner.nextInt();
        height = 15;

        System.out.println("Количество блоков: ");
        //n = scanner.nextInt();
        n = 50;

        int[][] sizeBlock = new int[n][2];

        while (i < n) {
            do {
                System.out.println("Ширина блока " + (i + 1) + ":");
                //a = scanner.nextInt();
                a = 7;
            } while (a <= 0 || a > width);

            sizeBlock[i][0] = a;

            do {
                System.out.println("Высота блока " + (i + 1) + ":");
                //a = scanner.nextInt();
                a = 6;
            } while (a <= 0 || a > height);

            sizeBlock[i++][1] = a;
        }


        scanner.close();

        new Gen(sizeBlock, width, height, 10, 5, 1000);
    }
}