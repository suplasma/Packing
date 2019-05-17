package ru.suplasma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, i = 0, n;

        int width, height;

        do {
            System.out.println("Ширина контейнера: ");
            width= scanner.nextInt();
        } while (width <= 0);

        do {
            System.out.println("Высота контейнера: ");
            height = scanner.nextInt();
        } while (height <= 0);

        do {
            System.out.println("Количество блоков: ");
            n = scanner.nextInt();
        } while (n <= 0);



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
        int numberOfGenes, remainingAmount, populations;

        do {
            System.out.println("Количество особей: ");
            numberOfGenes = scanner.nextInt();
        } while (numberOfGenes <= 0);

        do {
            System.out.println("Отобрать особей: ");
            remainingAmount = scanner.nextInt();
        } while (remainingAmount <= 0 || remainingAmount > numberOfGenes);

        do {
            System.out.println("Популяций: ");
            populations = scanner.nextInt();
        } while (populations <= 0);


        scanner.close();

        new Gen(sizeBlock, width, height, numberOfGenes, remainingAmount, populations);
    }
}