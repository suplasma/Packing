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

    public void setWidth(int width) {
        this.width = width;

    }

    public void setHeight(int height) {
        this.height = height;
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

    public void setNumber(int number) {
        this.number = number;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + width;
        result = prime * result + height;
        result = prime * result + numberContainer;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Block other = (Block) obj;
        if (width != other.width)
            return false;
        if (height != other.height)
            return false;
        if (numberContainer != other.numberContainer)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (number != other.number)
            return false;
        return true;
    }
}
