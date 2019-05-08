package ru.suplasma;

public class Container {
    private int[][] size;
    private int number;

    Container(int width, int height, int number) {
        size = new int[width][height];
        this.number = number;
    }

    boolean passedTheTest(Block[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getNumberContainer() == number) {
                for (int w = 0; w < blocks[i].getWidth(); w++)
                    for (int h = 0; h < blocks[i].getHeight(); h++) {
                        if (size[w + blocks[i].getX()][h + blocks[i].getY()] != 0)
                            return false;
                        size[w + blocks[i].getX()][h + blocks[i].getY()] = blocks[i].getNumber() + 1;
                    }
            }
        }
        return true;
    }

    boolean print(Block[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getNumberContainer() == number) {
                for (int w = 0; w < blocks[i].getWidth(); w++)
                    for (int h = 0; h < blocks[i].getHeight(); h++) {
                        size[w + blocks[i].getX()][h + blocks[i].getY()] = blocks[i].getNumber() + 1;
                    }
            }
        }

        if (!isEmpty()) {
 //           System.out.println();
            for (int[] coord : size) {
//                for (int c : coord)
//                    System.out.print(c);
//                System.out.println();
            }
            return true;
        }

        return false;
    }

    boolean isEmpty() {
        for (int[] coord : size)
            for (int c : coord)
                if (c != 0)
                    return false;
        return true;
    }

    void renull() {
        for (int[] w : size)
            for (int h : w)
                h = 0;
    }
}