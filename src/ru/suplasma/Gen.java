package ru.suplasma;

public class Gen {

    Gen(int[][] sizeBlock, int width, int height) {
        Pack[] packs = new Pack[10];

        for (int i = 0; i < 1; i++)
            packs[i] = new Pack(sizeBlock, width, height);
    }
}