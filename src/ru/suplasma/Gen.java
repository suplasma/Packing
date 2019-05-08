package ru.suplasma;

public class Gen {

    Gen(int[][] sizeBlock, int width, int height) {
        Pack[] packs = new Pack[10];
        Block[][] genom = new Block[10][sizeBlock.length];
        boolean flag = false;


        for (int k = 0; k < 1000; k++) {
            System.out.println("--------------------------" + (k + 1) + " популяция-------------------------");

            int min[] = new int[5];

            for (int i = 0; i < min.length; i++)
                min[i] = Integer.MAX_VALUE;


            for (int i = 0; i < 10; i++) {
                System.out.print("\n" + (i + 1) + " ген");

                if (flag)
                    packs[i] = new Pack(sizeBlock, width, height, genom[i]);
                else
                    packs[i] = new Pack(sizeBlock, width, height);

                System.out.println("Fitness: " + packs[i].fitness());
                for (int t = 0; t < 5; t++)
                    if (min[t] > packs[i].fitness()) {
                        for (int j = 4; j > t; j--)
                            min[j] = min[j - 1];
                        min[t] = packs[i].fitness();
                        break;
                    }
            }

            System.out.println("Min fitness: " + min[0]);

            int j = 0;
            for (int i = 0; i < 10; i++)
                if (packs[i].fitness() <= min[4]) {
                    Block[] blocks = packs[i].get();
                    packs[j].set(blocks);
                    genom[j] = packs[j++].get();
                }

            int n = j;

            for (; j < 10; j++) {
                packs[j].cross(packs, n);
                genom[j] = packs[j].get();
            }


        }
    }
}