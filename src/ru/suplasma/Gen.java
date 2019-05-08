package ru.suplasma;

public class Gen {

    Gen(int[][] sizeBlock, int width, int height) {
        Pack[] packs = new Pack[10];


        for (int k = 0; k < 10; k++) {
            System.out.println("--------------------------" + (k + 1) + " популяция-------------------------");

            int min[] = new int[5];

            for (int m : min)
                m = Integer.MAX_VALUE;


            for (int i = 0; i < 10; i++) {
                System.out.print("\n" + (i + 1) + " ген");
                packs[i] = new Pack(sizeBlock, width, height);
                for (int t = 0; t < 5; t++)
                    if (min[t] > packs[i].fitness()) {
                        for (int j = 4; j > t; j--)
                            min[j] = min[j - 1];
                        min[t] = i;
                        break;
                    }
            }

            int j = 0;
            for (int i = 0; i < 10; i++)
                if (packs[i].fitness() <= min[4]) {
                    Block[] blocks = packs[i].get();
                    packs[j++].set(blocks);
                }

            int n = j;

            for (; j < 10; j++)
                packs[j].cross(packs, n);
        }
    }
}