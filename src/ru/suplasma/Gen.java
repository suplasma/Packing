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
            //System.out.println("--------------------------" + (k + 1) + " популяция-------------------------");

            for (int i = 0; i < min.length; i++)
                min[i] = Integer.MAX_VALUE;

            for (int i = 0; i < packs.length; i++) {
                //System.out.println((i + 1) + " ген");

                //System.out.println("Fitness: " + packs[i].fitness());
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
                            System.out.print(gene[p][t] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    packs[i].write();
                }
            }


            //System.out.println("Min fitness: " + min[0]);

            for (int i = 0; i < packs.length; i++) {
                packs[i].cross(packs, 0);
                packs[i].mutation();
                genome = packs[i].get();
                packs[i] = new Pack(sizeBlock, width, height, genome);
            }
        }

//        System.out.println("Gen");
////        packs[0]=new Pack(sizeBlock,width,height,gene);
////        packs[0].print();
//        Block[] blocks = new Block[sizeBlock.length];
//        for (int i = 0; i < blocks.length; i++) {
//            blocks[i] = new Block(sizeBlock[i], i);
//            blocks[i].setNumberContainer(gene[i][0]);
//            blocks[i].setX(gene[i][1]);
//            blocks[i].setY(gene[i][2]);
//        }
//        for (int i = 0; i < fitness; i++) {
//            System.out.println((i + 1) + " Контейнер");
//            Container.print(blocks, i + 1, width, height);
//            System.out.println();
//        }
//        System.out.println("\n\n");
        Pack pack = new Pack(sizeBlock, width, height, gene);
        pack.print();

        System.out.println("Result: " + fitness);
    }
}