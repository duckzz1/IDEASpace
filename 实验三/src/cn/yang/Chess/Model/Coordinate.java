package cn.yang.Chess.Model;

import static cn.yang.Chess.Main.pieces;

public class Coordinate {
    private int x;
    private int y;
    private int camp1;
    private int camp2;

    public Coordinate(int x, int y, int camp1) {
        this.x = x;
        this.y = y;
        if (!isOutBound()) {
            this.camp1 = camp1;
            if (pieces[x][y] != null) {
                this.camp2 = pieces[x][y].getCamp();
            } else  {
                this.camp2 = 0;
            }
        }
    }

    private int getCamp1() {
        return camp1;
    }

    private int getCamp2() {
        return camp2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean isOutBound() {
        return (this.getX() < 0 || this.getX() > 7 || this.getY() < 0 || this.getY() > 7);
    }

    private boolean isSameCamp() {
        return (getCamp1() == getCamp2());
    }

    /**
     * 0    超出棋盘界限
     * 1    友方棋子
     * 2    敌方棋子
     * 3    无棋子
     */
    public int judge() {
        if (isOutBound()) {
            return 0;
        } else {
            if (pieces[this.getX()][this.getY()] == null) {
                return 3;
            } else if (getCamp1() == getCamp2()) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
