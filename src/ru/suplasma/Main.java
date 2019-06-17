package ru.suplasma;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        readDataIn();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readDataIn() {
        ArrayList<Integer> array = new ArrayList<>();
        int width = 0, height = 0, length = 0, numberOfGenes = 0, remainingAmount = 0, populations = 0;
        int sizeBlock[][];
        int n = 0;
        try {
            File file = new File("dataIn.txt");
            if (!file.exists()) {
                System.out.println("Ошибка, файла не существует");
                return;
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
                    return;
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
                    return;
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
                    return;
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
                    return;
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
                    return;
                } else if (remainingAmount > numberOfGenes) {
                    System.out.println("Ошибка входных данных");
                    System.out.println("Количество отобранных особей не может быть больше количества всех особей (" + numberOfGenes + "), введите еще раз");
                    return;
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
                    return;
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
                        return;
                    } else if (k > width) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Ширина блока не может быть больше ширины контейнера (" + width + "), введите еще раз");
                        return;
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
                        return;
                    } else if (k > height) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Высота блока не может быть больше высоты контейнера (" + height + "), введите еще раз");
                        return;
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
                        return;
                    } else if (k > length) {
                        System.out.println("Ошибка входных данных");
                        System.out.println("Длина блока не может быть больше длины контейнера (" + length + "), введите еще раз");
                        return;
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
                        return;
                    } else
                        flag = false;
                } while (flag);

                array.add(i++, k);

                n += k;
            }
            fr.close();
        } catch (Exception e) {
            return;
        }

        sizeBlock = new int[n][3];
        int k = 0;

        for (int i = 0; i < n; ) {
            for (int j = 0; j < array.get(k + 3); j++, i++) {
                sizeBlock[i][0] = array.get(k);
                sizeBlock[i][1] = array.get(k + 1);
                sizeBlock[i][2] = array.get(k + 2);
            }
            k += 4;
        }


        new Gen(sizeBlock, width, height, length, numberOfGenes, remainingAmount, populations);
    }
}