package ru.suplasma;

public class Gen {

    Gen(int[][] sizeBlock, int width, int height, int numberOfGenes, int remainingAmount, int populations) {
        Pack[] packs = new Pack[numberOfGenes];
        Block[][] genome = new Block[packs.length][sizeBlock.length];
        int fitness = Integer.MAX_VALUE;
        Pack gene = null;
        int min[] = new int[remainingAmount];
        int mini[] = new int[min.length];

        for (int i = 0; i < packs.length; i++)
            packs[i] = new Pack(sizeBlock, width, height, null);

        for (int k = 0; k < populations; k++) {
            System.out.println("--------------------------" + (k + 1) + " популяция-------------------------");

            for (int i = 0; i < min.length; i++)
                min[i] = Integer.MAX_VALUE;

            for (int i = 0; i < packs.length; i++) {
                System.out.println((i + 1) + " ген");

                System.out.println("Fitness: " + packs[i].fitness());
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
            }

            if (fitness > min[0]) {
                fitness = min[0];
                gene = packs[mini[0]];
            }

            System.out.println("Min fitness: " + min[0]);

            for (int i = 0; i < packs.length; i++) {
                packs[i].cross(packs, 0);
                packs[i].mutation();
                genome[i] = packs[i].get();
                packs[i] = new Pack(sizeBlock, width, height, genome[i]);
            }
        }

        System.out.println("Gen");
        gene.print();
        System.out.println("Result: " + fitness);
    }
}