package ru.suplasma;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 0, i = 0, n = 0;
        boolean flag;

        int width = 0, height = 0, length = 0;

        do {
            try {
                System.out.println("Ширина контейнера: ");
                width = scanner.nextInt();
            } catch (InputMismatchException e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (width <= 0) {
                flag = true;
                System.out.println("Ширина контейнера должна быть больше нуля, введите еще раз");
            } else
                flag = false;
        } while (flag);

        do {
            System.out.println("Высота контейнера: ");
            try {
                height = scanner.nextInt();
            } catch (Exception e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (height <= 0) {
                flag = true;
                System.out.println("Высота контейнера должна быть больше нуля, введите еще раз");
            } else
                flag = false;
        } while (flag);

        do {
            System.out.println("Длина контейнера: ");
            try {
                length = scanner.nextInt();
            } catch (Exception e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (length <= 0) {
                flag = true;
                System.out.println("Длина контейнера должна быть больше нуля, введите еще раз");
            } else
                flag = false;
        } while (flag);

        do {
            System.out.println("Количество блоков: ");
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (n <= 0) {
                flag = true;
                System.out.println("Количество блоков должно быть больше нуля, введите еще раз");
            } else
                flag = false;
        } while (flag);


        int[][] sizeBlock = new int[n][3];

        while (i < n) {

            do {
                System.out.println("Ширина блока " + (i + 1) + ":");
                try {
                    a = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    System.out.println("Нужно ввести целое число, введите еще раз");
                    scanner.next();
                    continue;
                }
                if (a <= 0) {
                    flag = true;
                    System.out.println("Ширина блока должна быть больше нуля, введите еще раз");
                } else if (a > width) {
                    flag = true;
                    System.out.println("Ширина блока не может быть больше ширины контейнера (" + width + "), введите еще раз");
                } else
                    flag = false;
            } while (flag);

            sizeBlock[i][0] = a;

            do {
                System.out.println("Высота блока " + (i + 1) + ":");
                try {
                    a = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    System.out.println("Нужно ввести целое число, введите еще раз");
                    scanner.next();
                    continue;
                }
                if (a <= 0) {
                    flag = true;
                    System.out.println("Высота блока должна быть больше нуля, введите еще раз");
                } else if (a > height) {
                    flag = true;
                    System.out.println("Высота блока не может быть больше высоты контейнера (" + height + "), введите еще раз");
                } else
                    flag = false;
            } while (flag);

            sizeBlock[i][1] = a;

            do {
                System.out.println("Длина блока " + (i + 1) + ":");
                try {
                    a = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    System.out.println("Нужно ввести целое число, введите еще раз");
                    scanner.next();
                    continue;
                }
                if (a <= 0) {
                    flag = true;
                    System.out.println("Длина блока должна быть больше нуля, введите еще раз");
                } else if (a > length) {
                    flag = true;
                    System.out.println("Длина блока не может быть больше длины контейнера (" + length + "), введите еще раз");
                } else
                    flag = false;
            } while (flag);

            sizeBlock[i++][2] = a;

        }

        int numberOfGenes = 0, remainingAmount = 0, populations = 0;

        do {
            System.out.println("Количество особей: ");
            try {
                numberOfGenes = scanner.nextInt();
            } catch (Exception e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (numberOfGenes <= 0) {
                flag = true;
                System.out.println("Количество особей должно быть больше нуля, введите еще раз");
            } else
                flag = false;
        } while (flag);

        do {
            System.out.println("Отобрать особей: ");
            try {
                remainingAmount = scanner.nextInt();
            } catch (Exception e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (remainingAmount <= 0) {
                flag = true;
                System.out.println("Количество отобранных особей должно быть больше нуля, введите еще раз");
            } else if (remainingAmount > numberOfGenes) {
                flag = true;
                System.out.println("Количество отобранных особей не может быть больше количества всех особей (" + numberOfGenes + "), введите еще раз");
            } else
                flag = false;
        } while (flag);

        do {
            System.out.println("Популяций: ");
            try {
                populations = scanner.nextInt();
            } catch (Exception e) {
                flag = true;
                System.out.println("Нужно ввести целое число, введите еще раз");
                scanner.next();
                continue;
            }
            if (populations <= 0) {
                flag = true;
                System.out.println("Популяций должно быть больше нуля, введите еще раз");
            } else
                flag = false;
        } while (flag);


        scanner.close();

        new Gen(sizeBlock, width, height, length, numberOfGenes, remainingAmount, populations);
    }
}