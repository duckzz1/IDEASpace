import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	Canvas c;
	Cell[][] cells;
	boolean choose = false;
	int[][] arr = new int[][] { { 5, 4, 3, 2, 1, 3, 4, 5 }, { 6, 6, 6, 6, 6, 6, 6, 6 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ -6, -6, -6, -6, -6, -6, -6, -6 }, { -5, -4, -3, -2, -1, -3, -4, -5 } };

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Pane p = new Pane();
		Scene s = new Scene(p, 512, 512);
		c = new Canvas(512, 512);
		Cell[][] cells = createCells();
		Draw d = new Draw(c.getGraphicsContext2D(), cells);
		Move rule = new Move();
		d.initPane();
		p.getChildren().add(c);
		arg0.setScene(s);
		arg0.show();
		s.setOnMouseClicked(e -> {
			int row = (int) Math.floor(e.getSceneY() / 64);
			int colum = (int) Math.floor(e.getX() / 64);
			rule.move(cells, row, colum, d);
			judgeWin(cells, arg0);
		});
	}

	public Cell[][] createCells() {
		cells = new Cell[8][8];
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				cells[i][j] = new Cell(i, j, arr[i][j], false);
			}
		}
		return cells;
	}

	public void judgeWin(Cell[][] cells, Stage arg0) {
		int red = 0, blue = 0;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].getFlag() == 1) {
					blue++;
				} else if (cells[i][j].getFlag() == -1) {
					red++;
				}
			}
		}
		if (red == 1 && blue == 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("红方胜利！");
			alert.showAndWait();
			try {
				this.start(arg0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (red == 0 && blue == 1) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("蓝方胜利！");
			alert.showAndWait();
			try {
				this.start(arg0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
