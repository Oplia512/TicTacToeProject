package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class BoardSize {
    private int width;
    private int height;

    public BoardSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "BoardSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
