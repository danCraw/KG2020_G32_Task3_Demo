package com.company;

import com.company.line.Line;
import com.company.line.LineDrawer;

import java.awt.*;
import java.text.DecimalFormat;

public class Background {

    private Font font;
    private double realWidth, realHeight, cornerX, cornerY;
    private double stepX = 1;
    private double stepY = 1;
    int width;
    int height;

    private ScreenConverter sc;

    public Background(ScreenConverter sc, LineDrawer ld, int width, int height) {
        this.sc = sc;
        drawCoordinateAxis(ld);
        drawMarks(ld);
    }

    public  void drawMarks(LineDrawer ld) {
        for (int i = Convert_X(-sc.getRealWidth() + sc.getCornerX()); i < Convert_X(sc.getRealWidth() + sc.getCornerX()); i++) {
            Line xAxis = new Line(i, -100, i, 100);
            ld.drawLine(sc.r2s(xAxis.getP1()), sc.r2s(xAxis.getP2()));
        }
        Line yAxis = new Line(0, -sc.getRealHeight() + sc.getCornerY(), 0, sc.getRealHeight() + sc.getCornerY());
        ld.drawLine(sc.r2s(yAxis.getP1()), sc.r2s(yAxis.getP2()));

    }

    public  void drawCoordinateAxis(LineDrawer ld) {
        Line xAxis = new Line(-sc.getRealWidth() + sc.getCornerX(), 0, sc.getRealWidth() + sc.getCornerX(), 0);
        Line yAxis = new Line(0, -sc.getRealHeight() + sc.getCornerY(), 0, sc.getRealHeight() + sc.getCornerY());
        ld.drawLine(sc.r2s(xAxis.getP1()), sc.r2s(xAxis.getP2()));
        ld.drawLine(sc.r2s(yAxis.getP1()), sc.r2s(yAxis.getP2()));

    }

    private String getSignature(double signature) {
        DecimalFormat decimalFormat = new DecimalFormat();
        return decimalFormat.format(signature);
    }

    private int Convert_X(double x) {
        return (int) ( ((width) / 2) + (x / (5 * Math.PI / 2)) * (width() / 2));
    }

    private int Convert_Y(double x) {
        return (int) ((height() / 2) - (x / (1.5)) * (height() / 2));
    }
    private double width() {
        return width;
    }

    private double height() {
        return height;
    }
}
