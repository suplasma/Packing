package ru.suplasma;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pack {
    private Block[] blocks;
    private int width;
    private int height;
    private int length;

    private Random random;

    Pack(int[][] sizeBlock, int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;

        random = new Random();
        blocks = new Block[sizeBlock.length];
        for (int i = 0; i < sizeBlock.length; i++)
            blocks[i] = new Block(sizeBlock[i]);

        blockPlacement(); //размещение блоков
    }

    void start() {
        check(); //проверка контейнеров
        remove();
        gravity();
    }

    private void gravity() {
        boolean flag = true;
        while (flag)
            for (Block block : blocks) {
                flag = false;
                do {
                    block.setY(block.getY() - 1);
                    if (Container.passedTheTest(blocks, block.getNumberContainer(), width, height, length))
                        flag = true;
                    else
                        break;
                } while (true);
                block.setY(block.getY() + 1);
            }
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
            for (Block block : blocks)
                if (block.getNumberContainer() == p)
                    flag = false;
            if (flag) {
                for (Block block : blocks)
                    if ((k = block.getNumberContainer()) > p)
                        block.setNumberContainer(k - 1);
                p--;
            }
        }
    }

    private void check() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Block block : blocks)
                if (!Container.passedTheTest(blocks, block.getNumberContainer(), width, height, length)) { //проверка наложение блоков друг на друга
                    switch (random.nextInt(4)) {
                        case 0: { //переместить в новый контейнер
                            block.setNumberContainer(fitness() + 1);
                            block.setX(random.nextInt(width - block.getWidth() + 1));
                            block.setY(random.nextInt(height - block.getHeight() + 1));
                            block.setZ(random.nextInt(length - block.getLength() + 1));
                            block.turn(random.nextInt(4), width, height, length);

                            flag = true;

                            break;
                        }
                        case 1: { //переместить в другой контейнер
                            block.setNumberContainer(random.nextInt(fitness()) + 1);
                            block.setX(random.nextInt(width - block.getWidth() + 1));
                            block.setY(random.nextInt(height - block.getHeight() + 1));
                            block.setZ(random.nextInt(length - block.getLength() + 1));
                            block.turn(random.nextInt(4), width, height, length);

                            flag = true;


                            break;
                        }
                        case 2: {//передвинуть
                            block.setX(random.nextInt(width - block.getWidth() + 1));
                            block.setY(random.nextInt(height - block.getHeight() + 1));
                            block.setZ(random.nextInt(length - block.getLength() + 1));
                            block.turn(random.nextInt(4), width, height, length);

                            flag = true;

                            break;
                        }
                        case 3: {//повернуть
                            block.turn(random.nextInt(3), width, height, length);

                            flag = true;

                            break;
                        }
                    }
                }
        }
    }

    int fitness() {
        int max = 0;
        for (Block block : blocks)
            if (block.getNumberContainer() > max)
                max = block.getNumberContainer();
        return max;
    }

    private int[][] get() {
        int[][] gen = new int[blocks.length][4];
        for (int i = 0; i < blocks.length; i++) {
            gen[i][0] = blocks[i].getNumberContainer();
            gen[i][1] = blocks[i].getX();
            gen[i][2] = blocks[i].getY();
            gen[i][3] = blocks[i].getZ();
        }
        return gen;
    }

    void cross(Pack[] packs, int[] mini, int n) {
        int[][] genome1 = packs[mini[random.nextInt(n)]].get();
        int[][] genome2 = packs[mini[random.nextInt(n)]].get();
        for (int i = 0; i < blocks.length; i++)
            if (random.nextInt(2) == 0) {
                blocks[i].setNumberContainer(genome1[i][0]);
                blocks[i].setX(genome1[i][1]);
                blocks[i].setY(genome1[i][2]);
                blocks[i].setZ(genome1[i][3]);
            } else {
                blocks[i].setNumberContainer(genome2[i][0]);
                blocks[i].setX(genome2[i][1]);
                blocks[i].setY(genome2[i][2]);
                blocks[i].setZ(genome2[i][3]);
            }
    }


    void mutation() {
        for (Block block : blocks) {
            if (random.nextInt(10) == 0) {
                block.setNumberContainer(random.nextInt(fitness()) + 1);
                block.setX(random.nextInt(width - block.getWidth() + 1));
                block.setY(random.nextInt(height - block.getHeight() + 1));
                block.setZ(random.nextInt(length - block.getLength() + 1));
                block.turn(random.nextInt(4), width, height, length);
            }
        }
    }

    void write() {
        try {
            FileWriter fw = new FileWriter("result.txt");
            for (int i = 1; i <= fitness(); i++) {
                fw.write(i + " Контейнер\n");
                for (int[][] coordinates : Container.write(blocks, i, width, height, length)) {
                    for (int[] cr : coordinates) {
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
                System.out.println("Ошибка создания файла result.txt");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
                return;
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Ошибка записи файла result.txt");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
}