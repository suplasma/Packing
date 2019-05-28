package ru.suplasma;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Pack {
    private Block[] blocks;
    private int width;
    private int height;
    private int length;

    private Random random;

    Pack(int[][] sizeBlock, int width, int height, int length, int[][] genome) {
        this.width = width;
        this.height = height;
        this.length = length;

        random = new Random();
        blocks = new Block[sizeBlock.length];
        for (int i = 0; i < sizeBlock.length; i++)
            blocks[i] = new Block(sizeBlock[i]);
        if (genome == null) {


            blockPlacement(); //размещение блоков

        } else
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].setNumberContainer(genome[i][0]);
                blocks[i].setX(genome[i][1]);
                blocks[i].setY(genome[i][2]);
                blocks[i].setZ(genome[i][3]);
            }

        start();
    }

    private void start() {
        check(); //проверка контейнеров
        remove();
    }

    private void blockPlacement() {
        int numberCont = 1, numberBlock, count = 0;
        numberBlock = random.nextInt(blocks.length); //взять случайный блок
        while (count < blocks.length) { //пока все блоки не будут размещены
            if (blocks[numberBlock].getNumberContainer() != 0) { //если блок уже размещен
                numberBlock = random.nextInt(blocks.length); //взять другой блок
            } else { //иначе разместить в этом блоке в случайном месте
                blocks[numberBlock].setNumberContainer(numberCont);
                blocks[numberBlock].setX(random.nextInt(width - blocks[numberBlock].getWidth() + 1));
                blocks[numberBlock].setY(random.nextInt(height - blocks[numberBlock].getHeight() + 1));
                blocks[numberBlock].setZ(random.nextInt(length - blocks[numberBlock].getLength() + 1));

                count++; //количество размещенных блоков

                if (random.nextInt(2) == 0) { //создать новый контейнер шанс 1 к 2
                    numberCont++;
                }
            }
        }
    }

    private void remove() {
        int k;
        boolean flag;
        for (int p = 1; p <= fitness(); p++) {
            flag = true;
            for (int i = 0; i < blocks.length; i++)
                if (blocks[i].getNumberContainer() == p)
                    flag = false;
            if (flag) {
                for (int t = 0; t < blocks.length; t++)
                    if ((k = blocks[t].getNumberContainer()) > p)
                        blocks[t].setNumberContainer(k - 1);
                p--;
            }
        }
    }

    private void check() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < blocks.length; i++)
                if (!Container.passedTheTest(blocks, blocks[i].getNumberContainer(), width, height, length)) { //проверка наложение блоков друг на друга
                    switch (random.nextInt(4)) {
                        case 0: { //переместить в новый контейнер
                            blocks[i].setNumberContainer(fitness() + 1);
                            blocks[i].setX(random.nextInt(width - blocks[i].getWidth() + 1));
                            blocks[i].setY(random.nextInt(height - blocks[i].getHeight() + 1));
                            blocks[i].setZ(random.nextInt(length - blocks[i].getLength() + 1));
                            flag = true;

                            break;
                        }
                        case 1: { //переместить в другой контейнер
                            blocks[i].setNumberContainer(random.nextInt(fitness()) + 1);
                            blocks[i].setX(random.nextInt(width - blocks[i].getWidth() + 1));
                            blocks[i].setY(random.nextInt(height - blocks[i].getHeight() + 1));
                            blocks[i].setZ(random.nextInt(length - blocks[i].getLength() + 1));
                            flag = true;


                            break;
                        }
                        case 2: {//передвинуть
                            blocks[i].setX(random.nextInt(width - blocks[i].getWidth() + 1));
                            blocks[i].setY(random.nextInt(height - blocks[i].getHeight() + 1));
                            blocks[i].setZ(random.nextInt(length - blocks[i].getLength() + 1));

                            flag = true;

                            break;
                        }
                        case 3: {
                            blocks[i].turn(random.nextInt(3));

                            break;
                        }
                    }
                }
        }
    }

    int fitness() {
        int max = 0;
        for (int i = 0; i < blocks.length; i++)
            if (blocks[i].getNumberContainer() > max)
                max = blocks[i].getNumberContainer();
        return max;
    }

    int[][] get() {
        int[][] gen = new int[blocks.length][4];
        for (int i = 0; i < blocks.length; i++) {
            gen[i][0] = blocks[i].getNumberContainer();
            gen[i][1] = blocks[i].getX();
            gen[i][2] = blocks[i].getY();
            gen[i][3] = blocks[i].getZ();
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
            if (random.nextInt(10) == 0) {
                blocks[r].setNumberContainer(random.nextInt(fitness()) + 1);
                blocks[r].setX(random.nextInt(width - blocks[r].getWidth() + 1));
                blocks[r].setY(random.nextInt(height - blocks[r].getHeight() + 1));
                blocks[r].setZ(random.nextInt(length - blocks[r].getLength() + 1));
                blocks[r].turn(random.nextInt(4));
            }
        }
    }

    int write() {
        try {
            FileWriter fw = new FileWriter("result.txt");
            for (int i = 1; i <= fitness(); i++) {
                fw.write(i + " Контейнер\n");
                for (int[][] coord : Container.write(blocks, i, width, height, length)) {
                    for (int[] cr : coord) {
                        for (int c : cr)
                            fw.write("\t" + c);
                        fw.write("\t");
                    }
                    fw.write("\n");
                }
                fw.write("\n\n");
            }

            File file = new File("result.txt");
            if (!file.exists()) {
                System.out.println("Ошибка создания файла");
                return 1;
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        }
        return 0;
    }
}