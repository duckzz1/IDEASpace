package cn.yang.Chess.Model.Black;

import cn.yang.Chess.Model.Coordinate;
import cn.yang.Chess.Model.Piece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Black_Queen extends Piece {

    public Black_Queen(int x, int y) {
        super(x, y);
        this.setCamp(1);
    }

    @Override
    public void drawPiece(GraphicsContext g) {
        if (isAlive()) {
            setImage(new Image("cn/yang/Chess/View/img/Black_Queen.png"));
            g.drawImage(getImage(), this.getX() * 60, this.getY() * 60, 60, 60);
        }
    }

    @Override
    public ArrayList<Coordinate> detectRoute() {

        return detectForQueen();
    }

}
