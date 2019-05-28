package cn.yang.Chess;

import cn.yang.Chess.Controller.Action;
import cn.yang.Chess.Model.Piece;
import cn.yang.Chess.View.Draw;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application{
    public static Piece[][] pieces = new Piece[8][8];

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 480, 480);
        Canvas canvas = new Canvas(480, 480);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Draw draw = new Draw();
        draw.initWindows(stage);
        Action action = new Action(draw, canvas);
        draw.drawPiece(gc);
        action.exchangeSide();
        draw.refreshPic(gc);
        pane.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
    }
}
