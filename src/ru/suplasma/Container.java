package ru.suplasma;

public class Container {
    private int[][] coordinates;
    private int number;

    Container(int width, int height, int number) {
        coordinates = new int[width][height];
        this.number = number;
    }

    boolean passedTheTest(int[][] blockSize) {
        for (int i = 0; i < blockSize.length; i++) {
            if (blockSize[i][2] == number) {
                for (int w = 0; w < blockSize[i][0]; w++)
                    for (int h = 0; h < blockSize[i][1]; h++) {
                        if (coordinates[w + blockSize[i][3]][h + blockSize[i][4]] != 0)
                            return false;
                        coordinates[w + blockSize[i][3]][h + blockSize[i][4]] = blockSize[i][5] + 1;
                    }
            }
        }
        return true;
    }

    void print(int[][] blockSize) {
        for (int i = 0; i < blockSize.length; i++) {
            if (blockSize[i][2] == number) {
                for (int w = 0; w < blockSize[i][0]; w++)
                    for (int h = 0; h < blockSize[i][1]; h++) {
                        coordinates[w + blockSize[i][3]][h + blockSize[i][4]] = blockSize[i][5] + 1;
                    }
            }
        }

        System.out.println();
        for (int[] coord : coordinates) {
            for (int c : coord)
                System.out.print(c);
            System.out.println();
        }
    }

    boolean isEmpty() {
        for (int[] coord : coordinates)
            for (int c : coord)
                if (c != 0)
                    return false;
        return true;
    }
}