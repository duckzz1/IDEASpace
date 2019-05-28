package cn.yang.Chess.View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static cn.yang.Chess.Main.pieces;

public class Draw {
    public void initWindows(Stage stage) {
        stage.setTitle("国际象棋");
        stage.getIcons().add(new Image("cn/yang/Chess/View/img/icon.png"));
        stage.setResizable(false);
    }

    private void initPane(GraphicsContext gc) {
        for (int i = 0; i < 9; i++) {
            gc.strokeLine(i * 60, 0, i * 60, 480);
            gc.strokeLine(0, i * 60, 480, i * 60);
        }
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.GRAY);
                    }
                    gc.fillRect(j * 60, i * 60, 60, 60);
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 != 0) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.GRAY);
                    }
                    gc.fillRect(j * 60, i * 60, 60, 60);
                }
            }
        }
    }

    public void drawPiece(GraphicsContext gc) {
        initPane(gc);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isAlive()) {
                        //棋子活着就显示该棋子
                        pieces[i][j].drawPiece(gc);
                    }
                }
            }
        }
    }

    public void refreshPic(GraphicsContext gc){
        gc.clearRect(0, 0, 480, 480);
        initPane(gc);
        drawPiece(gc);
    }
}
