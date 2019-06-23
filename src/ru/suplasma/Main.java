package ru.suplasma;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static int width = 0, height = 0, length = 0, numberBlocks = 0;

    public static void main(String[] args) {
        if (readDataIn())
            new Window(width, height, length, numberBlocks);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean readDataIn() {
        ArrayList<Integer> array = new ArrayList<>();
        int numberOfGenes = 0, remainingAmount = 0, populations = 0;
        int sizeBlock[][];
        try {
            File file = new File("dataIn.txt");
            if (!file.exists()) {
                System.out.println("Ошибка, dataIn.txt файла не существует");
                return false;
            }

            FileReader fr = new FileReader(file);
            Scanner scanner = new Scanner(fr);
            boolean flag;
            do {
                try {
                    width = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    scanner.next();
                    continue;
                }
                if (width <= 0) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Ширина контейнера должна быть больше нуля, введите еще раз");
                    return false;
                } else
                    flag = false;
            } while (flag);

            do {
                try {
                    height = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    scanner.next();
                    continue;
                }
                if (height <= 0) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Высота контейнера должна быть больше нуля, введите еще раз");
                    return false;
                } else
                    flag = false;
            } while (flag);

            do {
                try {
                    length = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    scanner.next();
                    continue;
                }
                if (length <= 0) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Длина контейнера должна быть больше нуля, введите еще раз");
                    return false;
                } else
                    flag = false;
            } while (flag);

            do {
                try {
                    numberOfGenes = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    scanner.next();
                    continue;
                }
                if (numberOfGenes <= 0) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Количество особей должно быть больше нуля, введите еще раз");
                    return false;
                } else
                    flag = false;
            } while (flag);

            do {
                try {
                    remainingAmount = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    scanner.next();
                    continue;
                }
                if (remainingAmount <= 0) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Количество отобранных особей должно быть больше нуля, введите еще раз");
                    return false;
                } else if (remainingAmount > numberOfGenes) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Количество отобранных особей не может быть больше количества всех особей (" + numberOfGenes + "), введите еще раз");
                    return false;
                } else
                    flag = false;
            } while (flag);

            do {
                try {
                    populations = scanner.nextInt();
                } catch (Exception e) {
                    flag = true;
                    scanner.next();
                    continue;
                }
                if (populations <= 0) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Популяций должно быть больше нуля, введите еще раз");
                    return false;
                } else
                    flag = false;
            } while (flag);

            int i = 0, k = 0;
            while (scanner.hasNextLine()) {
                do {
                    try {
                        k = scanner.nextInt();
                    } catch (Exception e) {
                        flag = true;
                        scanner.next();
                        continue;
                    }
                    if (k <= 0) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Ширина блока должна быть больше нуля, введите еще раз");
                        return false;
                    } else if (k > width) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Ширина блока не может быть больше ширины контейнера (" + width + "), введите еще раз");
                        return false;
                    } else
                        flag = false;
                } while (flag);

                array.add(i++, k);

                do {
                    try {
                        k = scanner.nextInt();
                    } catch (Exception e) {
                        flag = true;
                        scanner.next();
                        continue;
                    }
                    if (k <= 0) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Высота блока должна быть больше нуля, введите еще раз");
                        return false;
                    } else if (k > height) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Высота блока не может быть больше высоты контейнера (" + height + "), введите еще раз");
                        return false;
                    } else
                        flag = false;
                } while (flag);

                array.add(i++, k);

                do {
                    try {
                        k = scanner.nextInt();
                    } catch (Exception e) {
                        flag = true;
                        scanner.next();
                        continue;
                    }
                    if (k <= 0) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Длина блока должна быть больше нуля, введите еще раз");
                        return false;
                    } else if (k > length) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Длина блока не может быть больше длины контейнера (" + length + "), введите еще раз");
                        return false;
                    } else
                        flag = false;
                } while (flag);

                array.add(i++, k);

                do {
                    try {
                        k = scanner.nextInt();
                    } catch (Exception e) {
                        flag = true;
                        scanner.next();
                        continue;
                    }
                    if (k <= 0) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Количество блоков должно быть больше нуля, введите еще раз");
                        return false;
                    } else
                        flag = false;
                } while (flag);

                array.add(i++, k);

                numberBlocks += k;
            }
            scanner.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла dataIn.txt");
            return false;
        }

        sizeBlock = new int[numberBlocks][3];
        int k = 0;

        for (int i = 0; i < numberBlocks; ) {
            for (int j = 0; j < array.get(k + 3); j++, i++) {
                sizeBlock[i][0] = array.get(k);
                sizeBlock[i][1] = array.get(k + 1);
                sizeBlock[i][2] = array.get(k + 2);
            }
            k += 4;
        }


        new Gen(sizeBlock, width, height, length, numberOfGenes, remainingAmount, populations);
        return true;
    }
}