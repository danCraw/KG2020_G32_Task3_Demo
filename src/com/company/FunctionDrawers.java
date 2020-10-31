package com.company;

import com.company.line.Line;
import com.company.line.LineDrawer;

public class FunctionDrawers {
    private int prevX, prevY, curX, curY;
    private static final double Pi = Math.PI;

    private int width;
    private int height;
    private ScreenConverter sc;
    private LineDrawer ld;
//    private TextField tf = new TextField();

//    private int A = tf.getA();
//    private int B = tf.getB();
//    private int C = tf.getC();
//    private int D = tf.getD();

//    private int A = 1;
//    private int B = 2;
//    private int C = 3;
//    private int D = 4;

    public FunctionDrawers(int width, int height, ScreenConverter sc, LineDrawer ld) {
        this.width = width;
        this.height = height;
        this.sc = sc;
        this.ld = ld;
    }

    public void drawSin(LineDrawer ld) {
        prevX = Convert_X(-sc.getRealWidth());
        prevY = Convert_Y(Math.sin(prevX));

        for (double i = -sc.getRealWidth(); i <= sc.getRealWidth(); i += (0.01)) {
            curX = Convert_X(i) ;
            curY = Convert_Y(Math.sin(i)) - 250;

            drawLine(ld, new Line(prevX, prevY, curX, curY));

            prevX = curX;
            prevY = curY;
        }
    }

    public void drawCos(LineDrawer ld) {
        prevX = Convert_X(-sc.getRealWidth());
        prevY = Convert_Y(Math.sin(-sc.getRealWidth()));

        for (double i = -sc.getRealWidth(); i <= sc.getRealWidth(); i += (0.01)) {
            curX = Convert_X(i) ;
            curY = Convert_Y(Math.cos(i)) - 250;

            drawLine(ld, new Line(prevX, prevY, curX, curY));

            prevX = curX;
            prevY = curY;
        }
    }

    public void drawTg(LineDrawer ld) {
        prevX = Convert_X(-sc.getRealWidth());
        prevY = Convert_Y(Math.sin(-sc.getRealWidth()));

        for (double i = -sc.getRealWidth(); i <= sc.getRealWidth(); i += (0.01)) {
            curX = Convert_X(i) ;
            curY = Convert_Y(Math.tan(i)) - 250;

            drawLine(ld, new Line(prevX, prevY, curX, curY));

            prevX = curX;
            prevY = curY;
        }
    }

    public void drawExp(LineDrawer ld) {
        prevX = Convert_X(-sc.getRealWidth());
        prevY = Convert_Y(Math.sin(-sc.getRealWidth()));

        for (double i = -sc.getRealWidth(); i <= sc.getRealWidth(); i ++) {
            curX = Convert_X(i) ;
            curY = Convert_Y(-Math.exp(i)) - 280;

            drawLine(ld, new Line(prevX, prevY, curX, curY));

            prevX = curX;
            prevY = curY;
        }
    }

    public void drawCustomFunction(LineDrawer ld) {
        prevX = Convert_X(-sc.getRealWidth());
        prevY = Convert_Y( 1 / (2*prevX+4) + 3);

        for (double i = -sc.getRealWidth(); i <= sc.getRealWidth(); i++) {
            curX = Convert_X(i) ;
            curY = Convert_Y(1 / (2*curX+4) + 3) - 250;

            drawLine(ld, new Line(prevX, prevY, curX, curY));

            prevX = curX;
            prevY = curY;
        }
    }

    private int Convert_X(double x) {
        return (int) ((width() / 2) + (x / (5 * Pi / 2)) * (width() / 2));
    }

    private int Convert_Y(double x) {
        return (int) ((height() / 2) - (x / (1.5)) * (height() / 2));
    }


    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()));
    }

    private double width() {
        return width;
    }

    private double height() {
        return height;
    }


}
