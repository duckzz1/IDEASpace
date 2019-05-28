package cn.yang.Chess.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Piece {

    private int y;
    private int x;
    private Image image;
    private boolean alive = true;
    private int camp;
    private int stepCount;

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Piece() {

    }

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public abstract void drawPiece(GraphicsContext gc);

    public abstract ArrayList<Coordinate> detectRoute();

    public ArrayList<Coordinate> detectForKing() {
        ArrayList<Coordinate> c = new ArrayList<>();
        Coordinate[] cc = new Coordinate[8];
        cc[0] = new Coordinate(this.getX()+1, this.getY(), this.getCamp());
        cc[1] = new Coordinate(this.getX(), this.getY()+1, this.getCamp());
        cc[2] = new Coordinate(this.getX()-1, this.getY(), this.getCamp());
        cc[3] = new Coordinate(this.getX(), this.getY()-1, this.getCamp());
        cc[4] = new Coordinate(this.getX()+1, this.getY()+1, this.getCamp());
        cc[5] = new Coordinate(this.getX()+1, this.getY()-1, this.getCamp());
        cc[6] = new Coordinate(this.getX()-1, this.getY()+1, this.getCamp());
        cc[7] = new Coordinate(this.getX()-1, this.getY()-1, this.getCamp());
        for (Coordinate t:cc) {
            if (t.judge() > 1){
                c.add(t);
            }
        }
        return c;
    }

    public ArrayList<Coordinate> detectForKnight() {
        ArrayList<Coordinate> c = new ArrayList<>();
        Coordinate[] cc = new Coordinate[8];
        cc[0] = new Coordinate(this.getX()+1, this.getY()-2, this.getCamp());
        cc[1] = new Coordinate(this.getX()-1, this.getY()-2, this.getCamp());
        cc[2] = new Coordinate(this.getX()-1, this.getY()+2, this.getCamp());
        cc[3] = new Coordinate(this.getX()+1, this.getY()+2, this.getCamp());
        cc[4] = new Coordinate(this.getX()-2, this.getY()-1, this.getCamp());
        cc[5] = new Coordinate(this.getX()-2, this.getY()+1, this.getCamp());
        cc[6] = new Coordinate(this.getX()+2, this.getY()-1, this.getCamp());
        cc[7] = new Coordinate(this.getX()+2, this.getY()+1, this.getCamp());
        for (Coordinate t:cc) {
            if (t.judge() == 3) {
                c.add(t);
            } else if (t.judge() == 2) {
                c.add(t);
            }
        }
        return c;
    }

    public ArrayList<Coordinate> detectForQueen() {
        ArrayList<Coordinate> c = new ArrayList<>();

        c.addAll(detectOblique());
        c.addAll(detectStright());
        return c;
    }

    public ArrayList<Coordinate> detectStright() {
        ArrayList<Coordinate> c = new ArrayList<>();
        int i = 1;
        while (true) {
            Coordinate cc = new Coordinate(this.getX()+i++, this.getY(), this.getCamp());
            if (doJudge(c, cc)) {
                break;
            }
        }
        i = 1;
        while (true) {
            Coordinate cc = new Coordinate(this.getX()-i++, this.getY(), this.getCamp());
            if (doJudge(c, cc)) {
                break;
            }
        }
        i = 1;
        while (true) {
            Coordinate cc = new Coordinate(this.getX(), this.getY()+i++, this.getCamp());
            if (doJudge(c, cc)) {
                break;
            }
        }
        i = 1;
        while (true) {
            Coordinate cc = new Coordinate(this.getX(), this.getY()-i++, this.getCamp());
            if (doJudge(c, cc)) {
                break;
            }
        }

        return c;
    }

    public ArrayList<Coordinate> detectOblique() {
        ArrayList<Coordinate> c = new ArrayList<>();
        int i = 1;
        while (i > 0) {
            Coordinate ccc = new Coordinate(x+i, y+i, this.getCamp());
            if (doJudge(c, ccc)) {
                i = -1;
            }
        }
        i = 1;
        while (i > 0) {
            Coordinate ccc = new Coordinate(x-i, y-i, this.getCamp());
            if (doJudge(c, ccc)) {
                i = -1;
            }
        }
        i = 1;
        while (i > 0) {
            Coordinate ccc = new Coordinate(x+i, y-i, this.getCamp());
            if (doJudge(c, ccc)) {
                i = -1;
            }
        }
        i = 1;
        while (i > 0) {
            Coordinate ccc = new Coordinate(x-i, y+i, this.getCamp());
            if (doJudge(c, ccc)) {
                i = -1;
            }
        }
        return c;
    }

    private boolean doJudge(ArrayList<Coordinate> c ,Coordinate ccc) {
        if (ccc.judge() == 3) {
            c.add(ccc);
            return false;
        } else if(ccc.judge() == 2) {
            c.add(ccc);
            return true;
        } else {
            return true;
        }
    }
}
