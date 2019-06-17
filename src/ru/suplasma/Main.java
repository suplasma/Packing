package ru.suplasma;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int width = 0, height = 0, length = 0, sizeBlock[][] = null, numberOfGenes = 0, remainingAmount = 0, populations = 0;
        readDataIn(sizeBlock, width, height, length, numberOfGenes, remainingAmount, populations);
    }

    private static int readDataIn(int[][] sizeBlock, int width, int height, int length, int numberOfGenes, int remainingAmount, int populations) {
        ArrayList<Integer> array = new ArrayList<>();
        int n = 0;
        try {
            File file = new File("dataIn.txt");
            if (!file.exists()) {
                System.out.println("Ошибка, файла не существует");
                return 1;
            }

            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            width = scan.nextInt();
            height = scan.nextInt();
            length = scan.nextInt();
            numberOfGenes = scan.nextInt();
            remainingAmount = scan.nextInt();
            populations = scan.nextInt();
            int i = 0, k;
            while (scan.hasNextLine()) {
                array.add(i++, scan.nextInt());
                array.add(i++, scan.nextInt());
                array.add(i++, scan.nextInt());
                k = scan.nextInt();
                array.add(i++, k);
                n += k;
            }
            fr.close();
        } catch (Exception e) {
            return 2;
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
        return 0;
    }
}