package cn.yang.Chess.Controller;

import cn.yang.Chess.Model.Black.*;
import cn.yang.Chess.Model.Coordinate;
import cn.yang.Chess.Model.White.*;
import cn.yang.Chess.View.Draw;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import static cn.yang.Chess.Main.*;

public class Action {
    private Draw draw;
    private Canvas canvas;
    public static int stepAmount = 1;

    public Action(Draw draw, Canvas canvas){
        this.draw = draw;
        this.canvas = canvas;
        initPieces();
    }

    public void initPieces() {
        for (int i = 0; i < 8; i++) {
            pieces[i][1] = new Black_Pawn(i, 1);
            pieces[i][6] = new White_Pawn(i, 6);
        }
        pieces[4][0] = new Black_King(4, 0);
        pieces[0][0] = new Black_Rook(0, 0);
        pieces[7][0] = new Black_Rook(7, 0);
        pieces[1][0] =  new Black_Knight(1, 0);
        pieces[6][0] =  new Black_Knight(6, 0);
        pieces[2][0] = new Black_Bishop(2, 0);
        pieces[5][0] = new Black_Bishop(5, 0);
        pieces[3][0] = new Black_Queen(3, 0);
        pieces[4][7] = new White_King(4, 7);
        pieces[0][7] = new White_Rook(0, 7);
        pieces[7][7] = new White_Rook(7, 7);
        pieces[1][7] = new White_Knight(1, 7);
        pieces[6][7] = new White_Knight(6, 7);
        pieces[2][7] = new White_Bishop(2, 7);
        pieces[5][7] = new White_Bishop(5, 7);
        pieces[3][7] = new White_Queen(3, 7);
    }

    public boolean judgeWiner() {
        String str1 = "Black Win!";
        String str2 = "White Win!";
        Black_King black_king = null;
        White_King white_king = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if (pieces[i][j] != null) {
                    if (pieces[i][j] instanceof White_King) {
                        white_king = (White_King) pieces[i][j];
                    }
                    if (pieces[i][j] instanceof Black_King) {
                        black_king = (Black_King) pieces[i][j];
                    }
                }
            }
        }
        if (black_king == null) {
            new MyAlert(str2);
            return true;
        }
        if (white_king == null) {
            new MyAlert(str1);
            return true;
        } else {
            return false;
        }
    }

    public void exchangeSide() {
        if (judgeWiner()) {
            System.out.println("结束了。。");
        } else {
            if ( stepAmount % 2 == 1 ) {
                System.out.println("白方下子");
                setPieceWhite();
            } else if (stepAmount % 2 == 0) {
                System.out.println("黑方下子");
                setPieceBlack();
            }
        }
    }

    public void setPieceWhite() {
        draw.refreshPic(canvas.getGraphicsContext2D());
        canvas.setOnMouseClicked(e->{
            int x = (int) e.getX()/60;
            int y = (int) e.getY()/60;
            showSelfPiont(x, y);
            System.out.println("当前位置("+x+","+y+")");
            if (pieces[x][y] != null) {
                if (pieces[x][y].getCamp() == 2) {
                    click(x, y);
                } else {
                    new MyAlert("请重新选择棋子!");
                    setPieceWhite();
                }
            } else {
                System.out.println("null");
            }
        });
    }

    public void setPieceBlack() {
        draw.refreshPic(canvas.getGraphicsContext2D());
        canvas.setOnMouseClicked(e->{
            int x = (int) e.getX()/60;
            int y = (int) e.getY()/60;
            showSelfPiont(x, y);
            System.out.println("当前位置("+x+","+y+")");
            if (pieces[x][y] != null) {
                if (pieces[x][y].getCamp() == 1) {
                    click(x, y);
                } else {
                    new MyAlert("请重新选择棋子!");
                    setPieceBlack();
                }
            } else {
                System.out.println("null");
            }
        });
    }

    public void click(int x, int y) {
        ArrayList<Coordinate> c = pieces[x][y].detectRoute();
        showDetectRoute(c);
        canvas.setOnMouseClicked(ee->{
            int xx = (int) ee.getX()/60;
            int yy = (int) ee.getY()/60;
            System.out.println("当前位置("+xx+","+yy+")");
            if (xx==x && yy==y) {
                if (pieces[x][y].getCamp() == 1) {
                    setPieceBlack();
                }
                if (pieces[x][y].getCamp() == 2) {
                    setPieceWhite();
                }
            } else {
                move(x, y, xx, yy, moveType(xx, yy, c));
            }
        });
    }

    public void drawCell(int xx, int yy, Color color) {
        int x = xx*60;
        int y = yy*60;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setLineWidth(5);
        gc.strokeLine(x, y, x+60, y);
        gc.strokeLine(x, y, x, y+60);
        gc.strokeLine(x+60, y, x+60, y+60);
        gc.strokeLine(x, y+60, x+60, y+60);
    }

    /**
     * 显示选中的棋子
     * @param xx 该棋子的x坐标
     * @param yy 该棋子的y坐标
     */
    public void showSelfPiont(int xx, int yy) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawCell(xx, yy, Color.YELLOW);
    }

    /**
     * 显示该棋子可走的路径
     * @param c 可到达的点的集合
     */
    public void showDetectRoute(ArrayList<Coordinate> c){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (Coordinate t : c) {
            int xx = t.getX();
            int yy = t.getY();

            if (t.judge() == 3) {
                drawCell(xx, yy, Color.GREEN);
            } else if (t.judge() == 2) {
                drawCell(xx, yy, Color.RED);
            }
        }
    }

    /**
     * 判断要移动到位置是否可达
     * @param x toX
     * @param y toY
     * @param c 可达点的集合
     * @return 0->不可达   1->可以移动到    2->可以吃子
     */
    public int moveType(int x, int y, ArrayList<Coordinate> c){
        int s = 0;
        for (Coordinate t : c) {
            if (x == t.getX() && y == t.getY()) {
                if (t.judge() == 2) {
                    return  2;
                } else if (t.judge() == 3) {
                    return 1;
                }
            }
        }
        return s;
    }

    /**
     * 移动棋子
     * @param x x
     * @param y y
     * @param toX toX
     * @param toY toY
     */
    public void move(int x, int y, int toX, int toY, int moveType) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (moveType == 1 || moveType == 2) {
            pieces[x][y].setX(toX);
            pieces[x][y].setY(toY);
            pieces[x][y].setStepCount(pieces[x][y].getStepCount()+1);
            pieces[toX][toY] = pieces[x][y];
            pieces[x][y] = null;
            stepAmount++;
            draw.refreshPic(gc);
            exchangeSide();
        } else {
            click(x, y);
        }
    }
}
