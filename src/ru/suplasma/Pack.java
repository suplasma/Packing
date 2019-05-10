package ru.suplasma;

import java.util.LinkedList;
import java.util.Random;

public class Pack {
    private LinkedList<Container> containers;
    private int numberContainer;
    private Block[] blocks;
    private int width;
    private int height;

    private Random random;

    Pack(int[][] sizeBlock, int width, int height) {
        blocks = new Block[sizeBlock.length];

        for (int i = 0; i < sizeBlock.length; i++)
            blocks[i] = new Block(sizeBlock[i], i);


        this.width = width;
        this.height = height;
        containers = new LinkedList<>();

        numberContainer = 0;

        blockPlacement(0); //размещение блоков

        //создание контейнеров
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() >= containers.size())
                containers.add(new Container(width, height, numberContainer++));
        }

        check(); //проверка контейнеров

        start();
    }

    Pack(int[][] sizeBlock, int width, int height, Block[] genome) {
        blocks = new Block[sizeBlock.length];

        set(genome);


        this.width = width;
        this.height = height;
        containers = new LinkedList<>();

        blockPlacement(blocks.length); //размещение блоков

        //создание контейнеров
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() >= containers.size())
                containers.add(new Container(width, height, numberContainer++));
        }

        start();
    }

    private void start() {

        recont(); //обновить расположение блоков в контейнере
    }

    private void blockPlacement(int number) {
        random = new Random();
        int numberCont = 0, numberBlock, count = number;
        numberBlock = random.nextInt(blocks.length); //взять случайный блок
        while (count < blocks.length) { //пока все блоки не будут размещены
            if (blocks[numberBlock].getNumberContainer() != 0) { //если блок уже размещен
                numberBlock = random.nextInt(blocks.length); //взять другой блок
            } else { //иначе разместить в этом блоке в случайном месте
                blocks[numberBlock].setNumberContainer(numberCont + 1);
                blocks[numberBlock].setX(random.nextInt(width - blocks[numberBlock].getWidth() + 1));
                blocks[numberBlock].setY(random.nextInt(height - blocks[numberBlock].getHeight() + 1));

                count++; //количество размещенных блоков

                if (random.nextInt(2) == 0) //создать новый контейнер шанс 1 к 2
                    numberCont++;
            }
        }
    }

    void check() {
        for (int i = 0; i < containers.size(); i++)
            if (!containers.get(i).passedTheTest(blocks)) //проверка наложение блоков друг на друга
                for (Block block : blocks) //если да ищем контейнер
                    if (block.getNumberContainer() == i) {
                        switch (random.nextInt(3)) {
                            case 0: { //переместить в новый контейнер
                                containers.add(new Container(width, height, numberContainer));
                                block.setNumberContainer(numberContainer++);
                                block.setX(random.nextInt(width - block.getWidth() + 1));
                                block.setY(random.nextInt(height - block.getHeight() + 1));

                                //System.out.print("\nСоздан новый контейнер");

                                break;
                            }
                            case 1: { //переместить в другой контейнер
                                block.setNumberContainer(random.nextInt(numberContainer));
                                block.setX(random.nextInt(width - block.getWidth() + 1));
                                block.setY(random.nextInt(height - block.getHeight() + 1));

                                //System.out.print("\nПеремещен в другой контейнер");

                                i--;//проверить еще раз

                                break;
                            }
                            case 2: {//передвинуть
                                block.setX(random.nextInt(width - block.getWidth() + 1));
                                block.setY(random.nextInt(height - block.getHeight() + 1));

                                //System.out.print("\nПередвинут");

                                i--;//проверить еще раз

                                break;
                            }
                        }
                    }
    }

    void recont() {
        //обновление контейнеров
        containers = new LinkedList<>();
        numberContainer = 0;
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() >= containers.size())
                containers.add(new Container(width, height, numberContainer++));
        }
        numberContainer = 0;
        for (Container container : containers)
            if (container.recont(blocks))
                numberContainer++;
    }

    void print() {
        for (Container container : containers)
            container.print();
    }

    int fitness() {
        return numberContainer;
    }

    Block[] get() {
        return blocks;
    }

    void set(Block[] blocks) {
        this.blocks = blocks;
    }

    void cross(Pack[] packs, int n) {
        int r = random.nextInt(packs.length - n);
        int r1 = random.nextInt(packs.length - n);

        for (int i = 0; i < blocks.length; i++)
            if (random.nextInt(2) == 0)
                blocks[i] = packs[r].blocks[i];
            else
                blocks[i] = packs[r1].blocks[i];
    }

    void mutation() {
        for (int r = 0; r < blocks.length; r++) {
            blocks[r].setNumberContainer(random.nextInt(numberContainer));
            blocks[r].setX(random.nextInt(width - blocks[r].getWidth() + 1));
            blocks[r].setY(random.nextInt(height - blocks[r].getHeight() + 1));
        }
    }
}