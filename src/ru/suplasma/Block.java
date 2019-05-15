package ru.suplasma;

public class Block {
    private int width;
    private int height;
    private int numberContainer;
    private int x;
    private int y;
    private int number;

    Block(int[] coordinats, int number) {
        this.width = coordinats[0];
        this.height = coordinats[1];
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

    public boolean check(Block block) {
        if (block.getX() > x + width && block.getX() + block.getX() > x + width)
            return true;
        if (block.getX() < x && block.getX() + block.getX() < x)
            return true;
        if (block.getY() > y + height && block.getY() + block.getY() > y + height)
            return true;
        if (block.getY() < y && block.getY() + block.getY() < y)
            return true;
        return false;
    }
}