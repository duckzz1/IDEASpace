package cn.yang.Chess.View;

import cn.yang.Chess.Model.Black.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Draw extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws IOException {
        stage.setTitle("国际象棋");
        stage.getIcons().add(new Image("cn/yang/Chess/View/img/icon.png"));
        stage.setResizable(false);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 480, 480);
        Canvas canvas = new Canvas(480, 480);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        initPane(gc);


        drawPiece(gc);



        pane.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
    }

    public  void initPane(GraphicsContext gc) {
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
                        gc.setFill(Color.rgb(109, 86, 204));
                    }

                    gc.fillRect(j * 60, i * 60, 60, 60);
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 != 0) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.rgb(109, 86, 204));
                    }

                    gc.fillRect(j * 60, i * 60, 60, 60);
                }
            }
        }
    }

    public void drawPiece(GraphicsContext gc) {
        Black_King black_king = new Black_King(0, 4);

        Black_Pawn[] bp = new Black_Pawn[8];

        for (int i = 0; i < 8; i++) {
            bp[i].setCol(1);
            bp[i].setRow(i);
            bp[i].drawPiece(gc);
        }

//        Black_Pawn black_pawn1 = new Black_Pawn(1,0);
//        Black_Pawn black_pawn2 = new Black_Pawn(1,1);
//        Black_Pawn black_pawn3 = new Black_Pawn(1,2);
//        Black_Pawn black_pawn4 = new Black_Pawn(1,3);
//        Black_Pawn black_pawn5 = new Black_Pawn(1,4);
//        Black_Pawn black_pawn6 = new Black_Pawn(1,5);
//        Black_Pawn black_pawn7 = new Black_Pawn(1,6);
//        Black_Pawn black_pawn8 = new Black_Pawn(1,7);
        Black_Rook black_rook1 = new Black_Rook(0, 0);
        Black_Rook black_rook2 = new Black_Rook(0, 7);
        Black_Knight black_knight1 = new Black_Knight(0, 1);
        Black_Knight black_knight2 = new Black_Knight(0, 6);
        Black_Bishop black_bishop1 = new Black_Bishop(0, 2);
        Black_Bishop black_bishop2 = new Black_Bishop(0, 5);
        Black_Queen black_queen = new Black_Queen(0,3);
        black_king.drawPiece(gc);
//        black_pawn1.drawPiece(gc);
//        black_pawn2.drawPiece(gc);
//        black_pawn3.drawPiece(gc);
//        black_pawn4.drawPiece(gc);
//        black_pawn5.drawPiece(gc);
//        black_pawn6.drawPiece(gc);
//        black_pawn7.drawPiece(gc);
//        black_pawn8.drawPiece(gc);
        black_rook1.drawPiece(gc);
        black_rook2.drawPiece(gc);
        black_knight1.drawPiece(gc);
        black_knight2.drawPiece(gc);
        black_bishop1.drawPiece(gc);
        black_bishop2.drawPiece(gc);
        black_queen.drawPiece(gc);


    }
}
