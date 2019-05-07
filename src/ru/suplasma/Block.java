package ru.suplasma;

public class Block {
    private int width;
    private int height;
    private int number_container;
    private int x;
    private int y;
    private int number;

    Block(int width, int height, int number_container, int x, int y, int number) {
        this.width = width;
        this.height = height;
        this.number_container = number_container;
        this.x = x;
        this.y = y;
        this.number = number;
    }

    Block(int[] block) {
        this.width = block[0];
        this.height = block[1];
        this.number_container = block[2];
        this.x = block[3];
        this.y = block[4];
        this.number = block[5];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + width;
        result = prime * result + height;
        result = prime * result + number_container;
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
        if (number_container != other.number_container)
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
