package ru.suplasma;

public class Block {
    private int width;
    private int height;
    private int numberContainer;
    private int x;
    private int y;
    private int number;

    Block(int[] size, int number) {
        this.width = size[0];
        this.height = size[1];
        this.number = number;
    }

    public void setNumberContainer(int numberContainer) {
        this.numberContainer = numberContainer;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberContainer() {
        return numberContainer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }
}