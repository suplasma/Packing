package ru.suplasma;

class Gen {

    Gen(int[][] sizeBlock, int width, int height, int length, int numberOfGenes, int remainingAmount, int populations) {
        Pack[] packs = new Pack[numberOfGenes];
        int fitness = Integer.MAX_VALUE;
        int min[] = new int[remainingAmount];
        int mini[] = new int[remainingAmount];

        for (int i = 0; i < packs.length; i++) {
            packs[i] = new Pack(sizeBlock, width, height, length);
            packs[i].start();
        }

        for (int k = 0; k < populations; k++) {
            for (int i = 0; i < min.length; i++)
                min[i] = Integer.MAX_VALUE;

            for (int i = 0; i < packs.length; i++) {
                for (int t = 0; t < min.length; t++)
                    if (min[t] > packs[i].fitness()) {
                        for (int j = min.length - 1; j > t; j--) {
                            min[j] = min[j - 1];
                            mini[j] = mini[j - 1];
                        }
                        min[t] = packs[i].fitness();
                        mini[t] = i;
                        break;
                    }
                if (fitness > packs[i].fitness()) {
                    fitness = packs[i].fitness();
                    packs[i].write();
                }
            }

            int[] maxi = new int[numberOfGenes - remainingAmount];
            int t = 0;
            boolean flag;
            for (int i = 0; i < numberOfGenes; i++) {
                flag = true;
                for (int j = 0; j < remainingAmount; j++)
                    if (mini[j] == i)
                        flag = false;

                if (flag)
                    maxi[t++] = i;
            }

            for (int i = 0; i < (numberOfGenes - remainingAmount); i++) {
                packs[maxi[i]].cross(packs, mini, numberOfGenes - remainingAmount);
                packs[maxi[i]].mutation();
                packs[maxi[i]].start();
            }
        }
        System.out.println("Успешное завершение\nРезультат: " + fitness);
    }
}