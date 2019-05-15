package ru.suplasma;

public class Container {

    static boolean passedTheTest(Block[] blocks, int number, int width, int height) {
        boolean[][] size = new boolean[width][height];
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getNumberContainer() == number) {
                for (int w = 0; w < blocks[i].getWidth(); w++)
                    for (int h = 0; h < blocks[i].getHeight(); h++) {
                        if (size[w + blocks[i].getX()][h + blocks[i].getY()])
                            return false;
                        size[w + blocks[i].getX()][h + blocks[i].getY()] = true;
                    }
            }
        }
        return true;
    }

    static void print(Block[] blocks, int number, int width, int height) {
        int[][] size = new int[width][height];
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getNumberContainer() == number) {
                for (int w = 0; w < blocks[i].getWidth(); w++)
                    for (int h = 0; h < blocks[i].getHeight(); h++) {
                        size[w + blocks[i].getX()][h + blocks[i].getY()] = blocks[i].getNumber();
                    }
            }
        }

        System.out.println();
        for (int[] coord : size) {
            for (int c : coord)
                System.out.print("\t" + c);
            System.out.println();
        }
    }

    static boolean isEmpty(Block[] blocks, int number) {
        for (Block block : blocks)
            if (block.getNumberContainer() == number)
                return false;
        return true;
    }
}