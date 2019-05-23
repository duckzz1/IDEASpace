import java.util.ArrayList;

public class Cell {
	private int row;
	private int colum;
	private int flag;
	private boolean choose;
	private ArrayList<Pos> canWalk = new ArrayList<Pos>();

	public ArrayList<Pos> getCanWalk() {
		return canWalk;
	}

	public void setCanWalk(ArrayList<Pos> canWalk) {
		this.canWalk = canWalk;
	}

	public Cell(int row, int colum, int flag, boolean choose) {
		super();
		this.row = row;
		this.colum = colum;
		this.flag = flag;
		this.choose = choose;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColum() {
		return colum;
	}

	public void setColum(int colum) {
		this.colum = colum;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}
}
