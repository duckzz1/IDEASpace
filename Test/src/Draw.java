import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Draw {
	GraphicsContext gc;
	Cell[][] cells;

	public Draw(GraphicsContext gc, Cell[][] cells) {
		this.gc = gc;
		this.cells = cells;
	}

	public void initPane() {
		gc.clearRect(0, 0, 320, 320);
		gc.setFill(Color.WHITE);
		for (int i = 1; i < 8; ++i) {
			if (i % 2 == 0) {
				for (int j = 1; j < 8; ++j) {
					gc.setFill(Color.CHARTREUSE);
					gc.fillRect(j * 64, i * 64, 64, 64);
					j++;
				}
			} else {
				for (int j = 0; j < 8; ++j) {
					gc.setFill(Color.CHARTREUSE);
					gc.fillRect(j * 64, i * 64, 64, 64);
					j++;
				}
			}
		}

		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				gc.setStroke(Color.CHARTREUSE);
				gc.strokeLine(j * 64, 0, j * 64, 512);
				gc.strokeLine(0, i * 64, 512, i * 64);
				drawCell(cells[i][j]);
			}
		}
	}

	public void highLight() {
		gc.clearRect(0, 0, 512, 512);
		initPane();
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				gc.setStroke(Color.BROWN);
				gc.setLineWidth(5);
				if (cells[i][j].isChoose())
					gc.strokeRect(j * 64, i * 64, 64, 64);
				gc.setLineWidth(1);
				gc.setStroke(Color.CHARTREUSE);
			}
		}
	}

	public void drawCell(Cell cell) {
		int i = cell.getRow();
		int j = cell.getColum();
		if (cell.getFlag() == 1) {
			gc.drawImage(new Image("file:./res/king.png"), j * 64, i * 64, 64, 64);
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.BLUE);
//			gc.strokeText("王", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
		} else if (cell.getFlag() == 2) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.BLUE);
//			gc.strokeText("后", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/queen.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == 3) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.BLUE);
//			gc.strokeText("象", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/bishop.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == 4) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.BLUE);
//			gc.strokeText("马", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/house.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == 5) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.BLUE);
//			gc.strokeText("車", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/bju.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == 6) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.BLUE);
//			gc.strokeText("兵", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/soldier.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == -1) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.DARKGOLDENROD);
//			gc.strokeText("王", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/wking.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == -2) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.DARKGOLDENROD);
//			gc.strokeText("后", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/wqueen.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == -3) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.DARKGOLDENROD);
//			gc.strokeText("象", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/wbishop.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == -4) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.DARKGOLDENROD);
//			gc.strokeText("马", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/whouse.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == -5) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.DARKGOLDENROD);
//			gc.strokeText("車", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/wju.png"), j * 64, i * 64, 64, 64);
		} else if (cell.getFlag() == -6) {
//			gc.strokeOval(j * 40, i * 40, 40, 40);
//			gc.setFont(Font.font("Consola", FontWeight.BOLD, 20));
//			gc.setStroke(Color.DARKGOLDENROD);
//			gc.strokeText("兵", j * 40 + 10, i * 40 + 25);
//			gc.setStroke(Color.CYAN);
			gc.drawImage(new Image("file:./res/wsoldier.png"), j * 64, i * 64, 64, 64);
		}
	}
}