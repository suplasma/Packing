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
        containers = new LinkedList<>();

        blocks = new Block[sizeBlock.length];
        for(int i=0;i<sizeBlock.length;i++)
            blocks[i] = new Block(sizeBlock[i], i);

        numberContainer = 0;
        this.width = width;
        this.height = height;

        blockPlacement(); //размещение блоков


        //создание контейнеров
        for (int i = 0; i < blocks.length; i++) {
            while (blocks[i].getNumberContainer() >= containers.size())
                containers.add(new Container(width, height, numberContainer++));
        }

        check(); //проверка контейнеров
        print();
    }

    private void blockPlacement() {
        random = new Random();
        int numberCont = 0, numberBlock, count = 0;
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
                                containers.add(new Container(width, height, numberContainer++));
                                block.setNumberContainer(numberContainer);
                                block.setX(random.nextInt(width - block.getWidth() + 1));
                                block.setY(random.nextInt(height - block.getHeight() + 1));

                                System.out.println("Создан новый контейнер");

                                break;
                            }
                            case 1: { //переместить в другой контейнер
                                block.setNumberContainer(random.nextInt(numberContainer));
                                block.setX(random.nextInt(width - block.getWidth() + 1));
                                block.setY(random.nextInt(height - block.getHeight() + 1));

                                System.out.println("Перемещен в другой контейнер");

                                i--;//проверить еще раз

                                break;
                            }
                            case 2: {//передвинуть
                                block.setX(random.nextInt(width - block.getWidth() + 1));
                                block.setY(random.nextInt(height - block.getHeight() + 1));

                                System.out.println("Передвинут");

                                i--;//проверить еще раз

                                break;
                            }
                        }
                    }
    }

    void print() {
//        while (containers.size() != 0)
//            containers.remove(0);
//
//        for (int i = 0; i < numberContainer; i++)
//            containers.add(new Container(width, height, i));

        for(Container container:containers){
            container.renull();
            container.passedTheTest(blocks);
        }

        for (Container container : containers)
            container.print(blocks);
    }
}