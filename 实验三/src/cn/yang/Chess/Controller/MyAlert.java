package cn.yang.Chess.Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MyAlert {

    MyAlert(String str) {
        alertWindow(str);
    }

    public static void alertWindow(String title) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        Label lable = new Label();
        Button button = new Button("OK");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(lable, button);
        button.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        button.setOnMouseClicked(e->{
            stage.close();
        });

        Scene scene = new Scene(hBox, 300, 100);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
