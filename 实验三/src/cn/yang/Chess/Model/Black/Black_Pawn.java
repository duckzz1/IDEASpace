package cn.yang.Chess.Model.Black;

import cn.yang.Chess.Model.Piece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Black_Pawn extends Piece {


    public Black_Pawn(int row, int col) {
        super(row, col);
    }


    @Override
    public void drawPiece(GraphicsContext g) {
        setImage(new Image("cn/yang/Chess/View/img/Black_Pawn.png"));
        g.drawImage(getImage(), this.getCol() * 60, this.getRow() * 60, 60, 60);

    }

    @Override
    public void moveRule() {

    }
}
