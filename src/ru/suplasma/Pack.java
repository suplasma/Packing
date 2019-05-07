package ru.suplasma;

import java.util.LinkedList;
import java.util.Random;

public class Pack {
    private LinkedList<Container> containers;
    private LinkedList<Block> blocks;
    private int number_container;
    private int[][] blocksSize;
    private int width;
    private int height;

    Pack(int[][] blocksSize, int width, int height) {
        blocks = new LinkedList<>();
        containers = new LinkedList<>();

        number_container = 0;
        this.blocksSize = blocksSize;
        this.width = width;
        this.height = height;

        blockPlacement();
        System.out.println(check());

        for (int i = 0; i < blocksSize.length; i++) {
            blocks.add(new Block(blocksSize[i]));
            while (blocksSize[i][2] >= containers.size())
                containers.add(new Container(width, height, number_container++));
        }
    }

    private void blockPlacement(){
        Random random = new Random();
        int numberContainer = 0, numberBlock, count = 0;
        numberBlock = random.nextInt(blocksSize.length);
        while (count < blocksSize.length) {
            if (blocksSize[numberBlock][2] != 0) {
                numberBlock = random.nextInt(blocksSize.length);
                continue;
            }
            blocksSize[numberBlock][2] = numberContainer;
            try {
                blocksSize[numberBlock][3] = random.nextInt(width - blocksSize[numberBlock][0]);
            } catch (IllegalArgumentException e) {
                blocksSize[numberBlock][3] = 0;
            }
            try {
                blocksSize[numberBlock][4] = random.nextInt(height - blocksSize[numberBlock][1]);
            } catch (IllegalArgumentException e) {
                blocksSize[numberBlock][4] = 0;
            }
            count++;
            if (random.nextInt(2) == 0)
                numberContainer++;
        }
    }

    int check() {
        for (int i = 0; i < containers.size(); i++)
            if (!containers.get(i).passedTheTest(blocksSize))
                return i;
        return -1;
    }
}