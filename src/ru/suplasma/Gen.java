package ru.suplasma;

public class Gen {

    private Pack[] packs;

    Gen(int[][] blocksSize, int width, int height) {
        packs = new Pack[10];

        for (Pack pack : packs)
            pack = new Pack(blocksSize, width, height);
    }
}