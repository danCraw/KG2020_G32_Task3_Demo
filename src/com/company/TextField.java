package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextField extends JFrame {

    private int A = 1;
    private int B = 2;
    private int C = 2;
    private int D = 2;

    TextField() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание текстовых полей
        JButton build = new JButton("Построить");

        JTextField smallField = new JTextField(10);

        JTextField parametrA = new JTextField(10);
        parametrA.setText("1");
        JTextField parametrB = new JTextField(10);
        parametrB.setText("2");
        JTextField parametrC = new JTextField(10);
        parametrC.setText("3");
        JTextField parametrD = new JTextField(10);
        parametrD.setText("4");
        // Слушатель окончания ввода

        parametrA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                setA(Integer.parseInt(parametrA.getText()));
                JOptionPane.showMessageDialog(TextField.this,
                        "слово: " + parametrA.getText());
            }
        });
        parametrB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                setB(Integer.parseInt(parametrB.getText()));
                JOptionPane.showMessageDialog(TextField.this,
                        "слово: " + parametrB.getText());
            }
        });
        parametrC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                setC(Integer.parseInt(parametrC.getText()));
                JOptionPane.showMessageDialog(TextField.this,
                        "слово: " + parametrC.getText());
            }
        });
        parametrD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                setD(Integer.parseInt(parametrD.getText()));
                JOptionPane.showMessageDialog(TextField.this,
                        "слово: " + parametrD.getText());
            }
        });

        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setA(Integer.parseInt(parametrA.getText()));
                setB(Integer.parseInt(parametrB.getText()));
                setC(Integer.parseInt(parametrC.getText()));
                setD(Integer.parseInt(parametrD.getText()));
                System.out.println(getA() + " " + getB() + " " + getC() + " " + getD());
            }
        });

        // Создание панели с текстовыми полями
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contents.add(build);
        contents.add(parametrA);
        contents.add(parametrB);
        contents.add(parametrC);
        contents.add(parametrD);
        setContentPane(contents);
        // Определяем размер окна и выводим его на экран
        setSize(400, 100);
        setVisible(true);
    }

    public void setA(int a) {
        A = a;
    }

    public void setB(int b) {
        B = b;
    }

    public void setC(int c) {
        C = c;
    }

    public void setD(int d) {
        D = d;
    }

    public  int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public int getC() {
        return C;
    }

    public int getD() {
        return D;
    }
}
