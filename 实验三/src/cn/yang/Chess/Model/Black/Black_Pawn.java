package cn.yang.Chess.Model.Black;

import cn.yang.Chess.Model.Coordinate;
import cn.yang.Chess.Model.Piece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;


public class Black_Pawn extends Piece {

    public Black_Pawn(int x, int y) {
        super(x, y);
        this.setCamp(1);
    }

    @Override
    public void drawPiece(GraphicsContext g) {
        if (isAlive()) {
            setImage(new Image("cn/yang/Chess/View/img/Black_Pawn.png"));
            g.drawImage(getImage(), this.getX() * 60, this.getY() * 60, 60, 60);
        }
    }

    public ArrayList<Coordinate> detectRoute() {
        ArrayList<Coordinate> c = new ArrayList<>();
        Coordinate c1 = new Coordinate(getX(), getY()+1, this.getCamp());
        Coordinate c2 = new Coordinate(getX(), getY()+2, this.getCamp());
        Coordinate c3 = new Coordinate(getX()+1, getY()+1, this.getCamp());
        Coordinate c4 = new Coordinate(getX()-1, getY()+1, this.getCamp());
        if (c1.judge() > 1) {
            c.add(c1);
        }
        if (c2.judge() > 1) {
            if (this.getStepCount() == 0) {
                c.add(c2);
            }
        }
        if (c3.judge() == 2) {
            c.add(c3);
        }
        if (c4.judge() == 2) {
            c.add(c4);
        }
        return c;
    }
}