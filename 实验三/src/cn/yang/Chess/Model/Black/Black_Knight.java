package cn.yang.Chess.Model.Black;

import cn.yang.Chess.Model.Coordinate;
import cn.yang.Chess.Model.Piece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Black_Knight extends Piece {

    public Black_Knight(int x, int y) {
        super(x, y);
        setCamp(1);
    }

    @Override
    public void drawPiece(GraphicsContext g) {
        if (isAlive()) {
            setImage(new Image("cn/yang/Chess/View/img/Black_Knight.png"));
            g.drawImage(getImage(), this.getX() * 60, this.getY() * 60, 60, 60);
        }
    }

    @Override
    public ArrayList<Coordinate> detectRoute() {
        return detectForKnight();
    }
}