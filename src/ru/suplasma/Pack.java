package ru.suplasma;

import java.util.LinkedList;
import java.util.Random;

public class Pack {
    private LinkedList<Container> containers;
    private int numberContainer;
    private int[][] blocksSize;
    private int width;
    private int height;

    private Random random;

    Pack(int[][] blocksSize, int width, int height) {
        containers = new LinkedList<>();

        numberContainer = 0;
        this.blocksSize = blocksSize;
        this.width = width;
        this.height = height;

        blockPlacement(); //размещение блоков


        //наполнение контейнеров
        for (int i = 0; i < blocksSize.length; i++) {
            while (blocksSize[i][2] >= containers.size())
                containers.add(new Container(width, height, numberContainer++));
        }

        check(); //проверка контейнеров
        print();
    }

    private void blockPlacement() {
        random = new Random();
        int numbeCont = 0, numberBlock, count = 0;
        numberBlock = random.nextInt(blocksSize.length);
        while (count < blocksSize.length) {
            if (blocksSize[numberBlock][2] != 0) {
                numberBlock = random.nextInt(blocksSize.length);
                continue;
            }
            blocksSize[numberBlock][2] = numbeCont;
            blocksSize[numberBlock][3] = random.nextInt(width - blocksSize[numberBlock][0] + 1);
            blocksSize[numberBlock][4] = random.nextInt(height - blocksSize[numberBlock][1] + 1);

            count++;
            if (random.nextInt(2) == 0)
                numbeCont++;
        }
    }

    void check() {
        for (int i = 0; i < containers.size(); i++)
            if (!containers.get(i).passedTheTest(blocksSize)) //проверка наложение блоков друг на друга
                for (int block[] : blocksSize) //если да ищем контейнер
                    if (block[2] == i) {
                        switch (random.nextInt(3)) {
                            case 0: { //переместить в новый контейнер
                                containers.add(new Container(width, height, numberContainer++));
                                block[2] = numberContainer;
                                block[3] = random.nextInt(width - block[0] + 1);
                                block[4] = random.nextInt(height - block[1] + 1);

                                System.out.println("Создан новый контейнер");

                                break;
                            }
                            case 1: { //переместить в другой контейнер
                                block[2] = random.nextInt(numberContainer);
                                try {
                                    block[3] = random.nextInt(width - block[0]);
                                } catch (IllegalArgumentException e) {
                                    block[3] = 0;
                                }
                                try {
                                    block[4] = random.nextInt(height - block[1]);
                                } catch (IllegalArgumentException e) {
                                    block[4] = 0;
                                }
                                System.out.println("Перемещен в другой контейнер");

                                i--;//проверить еще раз

                                break;
                            }
                            case 2: {//передвинуть
                                try {
                                    block[3] = random.nextInt(width - block[0]);
                                } catch (IllegalArgumentException e) {
                                    block[3] = 0;
                                }
                                try {
                                    block[4] = random.nextInt(height - block[1]);
                                } catch (IllegalArgumentException e) {
                                    block[4] = 0;
                                }
                                System.out.println("Передвинут");

                                i--;//проверить еще раз

                                break;
                            }
                        }
                    }
    }

    void print(){
        while (containers.size() != 0)
            containers.remove(0);

        for (int i = 0; i < numberContainer; i++)
            containers.add(new Container(width, height, i));

        for (Container container : containers)
            container.print(blocksSize);
    }
}