package cn.yang.Chess.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Piece {

    private int row;
    private int col;
    private Image image;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Piece() {

    }

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public abstract void drawPiece(GraphicsContext gc);

    /**
     * 棋子的移动规则
     */
    public abstract void moveRule();

}
