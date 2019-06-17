package ru.suplasma;

public class Container {

    static boolean passedTheTest(Block[] blocks, int number, int width, int height, int length) {
        boolean[][][] size = new boolean[width][height][length];
        for (Block block : blocks) {
            if (block.getNumberContainer() == number) {
                for (int w = 0; w < block.getWidth(); w++)
                    for (int h = 0; h < block.getHeight(); h++)
                        for (int l = 0; l < block.getLength(); l++) {
                            try {
                                if (size[w + block.getX()][h + block.getY()][l + block.getZ()])
                                    return false;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                return false;
                            }

                            size[w + block.getX()][h + block.getY()][l + block.getZ()] = true;
                        }
            }
        }
        return true;
    }

    static int[][][] write(Block[] blocks, int number, int width, int height, int length) {
        int[][][] size = new int[width][height][length];
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getNumberContainer() == number) {
                for (int w = 0; w < blocks[i].getWidth(); w++)
                    for (int h = 0; h < blocks[i].getHeight(); h++)
                        for (int l = 0; l < blocks[i].getLength(); l++) {
                            size[w + blocks[i].getX()][h + blocks[i].getY()][l + blocks[i].getZ()] = i + 1;
                        }
            }
        }

        return size;
    }
}