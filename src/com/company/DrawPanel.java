package com.company;

import com.company.line.DDALineDrawer;
import com.company.line.Line;
import com.company.line.LineDrawer;
import com.company.pixel.BufferedImagePixelDrawer;
import com.company.pixel.PixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    private ScreenPoint lastPosition = null;
    private ScreenConverter sc = new ScreenConverter(-500, 500, 1000, 1000, 800, 800);

    private ArrayList<Line> allLines = new ArrayList<>();

    private Line currentNewLine = null;

    public DrawPanel() {
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenWidth(getWidth());
        sc.setScreenHeight(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();

        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer ld = new DDALineDrawer(pd);
        FunctionDrawers fd = new FunctionDrawers(getWidth(), getHeight(), sc, ld);
        Background bg = new Background(sc, ld, getWidth(), getHeight());
//        fd.drawSin(ld);
//        fd.drawCos(ld);
//        fd.drawExp(ld);
//        fd.drawTg(ld);
        fd.drawCustomFunction(ld);

        for (Line l : allLines) {
            drawLine(ld, l);
        }

        if (currentNewLine != null){
            drawLine(ld, currentNewLine);
        }

        g.drawImage(bi, 0, 0, null);

    }

    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()));
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint currentPosition = new ScreenPoint(e.getX(), e.getY());
        if (lastPosition != null) {
            ScreenPoint deltaScreen = new ScreenPoint(currentPosition.getX() - lastPosition.getX(), currentPosition.getY() - lastPosition.getY());
            RealPoint deltaReal = sc.s2r(deltaScreen);
            RealPoint zeroReal = sc.s2r(new ScreenPoint(0, 0));
            RealPoint vector = new RealPoint(deltaReal.getX() - zeroReal.getX(), deltaReal.getY() - zeroReal.getY());
            sc.setCornerX(sc.getCornerX() - vector.getX());
            sc.setCornerY(sc.getCornerY() - vector.getY());
            lastPosition = currentPosition;
        }
        if(currentNewLine != null){
            currentNewLine.setP2(sc.s2r(currentPosition));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            lastPosition = new ScreenPoint(e.getX(), e.getY());
        } else if(e.getButton() == MouseEvent.BUTTON1) {
            currentNewLine = new Line(sc.s2r(new ScreenPoint(e.getX(), e.getY())), sc.s2r(new ScreenPoint(e.getX(), e.getY())));
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            lastPosition = null;
        } else if (e.getButton() == MouseEvent.BUTTON1){
            allLines.add(currentNewLine);
            currentNewLine = null;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double coef = clicks < 0 ? 1.05 : 0.95;
        for (int i = 0; i < Math.abs(clicks); i++){
            scale *= coef;
        }
        sc.setRealWidth(sc.getRealWidth() * scale);
        sc.setRealHeight(sc.getRealHeight() * scale);
        repaint();
    }
}