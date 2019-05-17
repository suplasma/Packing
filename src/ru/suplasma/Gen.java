package ru.suplasma;

public class Gen {

    Gen(int[][] sizeBlock, int width, int height, int numberOfGenes, int remainingAmount, int populations) {
        Pack[] packs = new Pack[numberOfGenes];
        int[][] genome;
        int fitness = Integer.MAX_VALUE;
        int[][] gene = new int[sizeBlock.length][3];
        int min[] = new int[remainingAmount];
        int mini[] = new int[min.length];

        for (int i = 0; i < packs.length; i++)
            packs[i] = new Pack(sizeBlock, width, height, null);

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
                    for (int p = 0; p < packs[i].get().length; p++) {
                        for (int t = 0; t < 3; t++) {
                            gene[p][t] = packs[i].get()[p][t];
                        }
                    }
                }
            }

            for (int i = 0; i < packs.length; i++) {
                packs[i].cross(packs, 0);
                packs[i].mutation();
                genome = packs[i].get();
                packs[i] = new Pack(sizeBlock, width, height, genome);
            }
        }
        new Pack(sizeBlock,width,height,gene).write();
    }
}