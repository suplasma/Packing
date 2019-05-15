package ru.suplasma;

import java.util.LinkedList;
import java.util.Random;

public class Pack {
    private LinkedList<Integer> containers;
    private Block[] blocks;
    private int width;
    private int height;

    private Random random;

    Pack(int[][] sizeBlock, int width, int height, int[][] genome) {
        this.width = width;
        this.height = height;
        containers = new LinkedList<>();

        random = new Random();
        blocks = new Block[sizeBlock.length];
        for (int i = 0; i < sizeBlock.length; i++)
            blocks[i] = new Block(sizeBlock[i], i + 1);
        if (genome == null) {


            blockPlacement(); //размещение блоков

        } else
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].setNumberContainer(genome[i][0]);
                blocks[i].setX(genome[i][1]);
                blocks[i].setY(genome[i][2]);
            }

        start();
    }

    private void start() {


        //создание контейнеров
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() > containers.size())
                containers.add(0);
        }


        check(); //проверка контейнеров
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() > containers.size())
                containers.add(0);
        }

        remove();
        //создание контейнеров
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() > containers.size())
                containers.add(0);
        }
        if (containers.size() < 37)
            print();
        int max = 0;
        for (int i = 0; i < blocks.length; i++)
            if (blocks[i].getNumberContainer() > max)
                max = blocks[i].getNumberContainer();
        if (max != containers.size())
            System.out.println(1 / 0);

    }

    private void blockPlacement() {
        int numberCont = 1, numberBlock, count = 0;
        numberBlock = random.nextInt(blocks.length); //взять случайный блок
        containers.add(0);
        while (count < blocks.length) { //пока все блоки не будут размещены
            if (blocks[numberBlock].getNumberContainer() != 0) { //если блок уже размещен
                numberBlock = random.nextInt(blocks.length); //взять другой блок
            } else { //иначе разместить в этом блоке в случайном месте
                blocks[numberBlock].setNumberContainer(numberCont);
                blocks[numberBlock].setX(random.nextInt(width - blocks[numberBlock].getWidth() + 1));
                blocks[numberBlock].setY(random.nextInt(height - blocks[numberBlock].getHeight() + 1));

                count++; //количество размещенных блоков

                if (random.nextInt(2) == 0) { //создать новый контейнер шанс 1 к 2
                    numberCont++;
                    containers.add(0);
                }
            }
        }
        if (numberCont != containers.size()) {
            System.out.println(numberCont + " " + containers.size());
            System.out.println(1 / 0);
        }
    }

    private void remove() {
        int k;
        boolean flag;
        for (int p = 1; p <= containers.size(); p++) {
            flag = true;
            for (int i = 0; i < blocks.length; i++)
                if (blocks[i].getNumberContainer() == p)
                    flag = false;
            if (flag) {
                for (int t = 0; t < blocks.length; t++)
                    if ((k = blocks[t].getNumberContainer()) > p)
                        blocks[t].setNumberContainer(k - 1);
                containers.remove(p - 1);
                p--;
            }
        }
        for (int p = 1; p <= containers.size(); p++)
            if (Container.isEmpty(blocks, p))
                System.out.println(1 / 0);
    }

    private void check() {
        boolean flag = true;
        while (flag) {
            flag = false;
            //for (int i = 1; i <= containers.size(); i++)
            //if (!Container.passedTheTest(blocks, i, width, height)){ //проверка наложение блоков друг на друга
            for (int i = 0; i < blocks.length; i++) //если да ищем контейнер
                if (!Container.passedTheTest(blocks, blocks[i].getNumberContainer(), width, height)) { //проверка наложение блоков друг на друга
                    switch (random.nextInt(3)) {
                        case 0: { //переместить в новый контейнер
                            containers.add(0);
                            blocks[i].setNumberContainer(containers.size() + 1);
                            blocks[i].setX(random.nextInt(width - blocks[i].getWidth() + 1));
                            blocks[i].setY(random.nextInt(height - blocks[i].getHeight() + 1));
                            flag = true;

                            break;
                        }
                        case 1: { //переместить в другой контейнер
                            if (containers.size() == 0)
                                System.out.println(1 / 0);
                            blocks[i].setNumberContainer(random.nextInt(containers.size()) + 1);
                            blocks[i].setX(random.nextInt(width - blocks[i].getWidth() + 1));
                            blocks[i].setY(random.nextInt(height - blocks[i].getHeight() + 1));

                            flag = true;


                            break;
                        }
                        case 2: {//передвинуть
                            blocks[i].setX(random.nextInt(width - blocks[i].getWidth() + 1));
                            blocks[i].setY(random.nextInt(height - blocks[i].getHeight() + 1));

                            flag = true;

                            break;
                        }
                    }
                }
        }
        for (int i = 1; i <= containers.size(); i++)
            if (!Container.passedTheTest(blocks, i, width, height)) //проверка наложение блоков друг на друга
                System.out.println(1 / 0);
//        for (int i = 0; i < blocks.length; i++)
//            for (int j = 0; j < blocks.length; j++)
//                if (i != j && blocks[i].getNumberContainer() == blocks[j].getNumberContainer() && !blocks[i].check(blocks[j]))
//                    System.out.println("Block #" + blocks[i].getNumberContainer());
    }

    void print() {
        for (int i = 1; i <= containers.size(); i++) {
            System.out.println(i + " Контейнер");
            Container.print(blocks, i, width, height);
        }
        System.out.println("\n\n");
        for (Block block : blocks)
            System.out.println(block.getNumber() + " " + block.getNumberContainer());
        System.out.println("\n" + containers.size());
    }

    int fitness() {
//        int max = 0;
//        for (int i = 0; i < blocks.length; i++)
//            if (blocks[i].getNumberContainer() > max)
//                max = blocks[i].getNumberContainer();
//        while (max > containers.size())
//            containers.add(0);
//        while (max < containers.size())
//            remove();
        return containers.size();
    }

    int[][] get() {
        int[][] gen = new int[blocks.length][3];
        for (int i = 0; i < blocks.length; i++) {
            gen[i][0] = blocks[i].getNumberContainer();
            gen[i][1] = blocks[i].getX();
            gen[i][2] = blocks[i].getY();
        }
        return gen;
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
            blocks[r].setNumberContainer(random.nextInt(containers.size()) + 1);
            blocks[r].setX(random.nextInt(width - blocks[r].getWidth() + 1));
            blocks[r].setY(random.nextInt(height - blocks[r].getHeight() + 1));
        }
    }
}