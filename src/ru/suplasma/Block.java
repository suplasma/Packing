package ru.suplasma;

public class Block {
    private int width;
    private int height;
    private int length;
    private int numberContainer;
    private int x;
    private int y;
    private int z;

    Block(int[] size) {
        this.width = size[0];
        this.height = size[1];
        this.length = size[2];
    }

    public void turn(int c, int w, int h, int l) {
        switch (c) {
            case 0: {
                if (width <= h && height <= w) {
                    width = width + height;
                    height = width - height;
                    width = width - height;
                }
                break;
            }
            case 1: {
                if (width <= l && length <= w) {
                    width = width + length;
                    length = width - length;
                    width = width - length;
                    break;
                }
            }
            case 2: {
                if (length <= h && height <= l) {
                    length = length + height;
                    height = length - height;
                    length = length - height;
                    break;
                }
            }
        }
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

    public void setZ(int z) {
        this.z = z;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
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

    public int getZ() {
        return z;
    }
}