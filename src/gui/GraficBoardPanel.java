/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import wireworld.Element;
import wireworld.Population;

/**
 *
 * @author Piotrek
 */
public class GraficBoardPanel extends JPanel
        implements MouseListener, MouseMotionListener {

    private Population p;
    private Element elem;
    private boolean isClicked;
    private int squareSize = 10;
    private final int panelHeight = 600;
    private final int panelWidth = 600;
    private boolean ifDrawGrid = true;

    public GraficBoardPanel(Population p) {
        this.p = p;
        calculateSquareSize();
    }

    private void calculateSquareSize() {
        int panelBiggerDemension
                = panelHeight > panelWidth ? panelHeight : panelWidth;
        int populationBiggerDemension
                = p.getHeight() > p.getWidth() ? p.getHeight() : p.getWidth();
        squareSize = panelBiggerDemension / populationBiggerDemension;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public void setPopulation(Population p) {
        this.p = p;
        calculateSquareSize();
    }

    public Population getPopulation() {
        return p;
    }

    public void setElement(Element element) {
        elem = element;
    }

    public Element getElement() {
        return elem;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void setIfDrawGrid(boolean b) {
        ifDrawGrid = b;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < p.getHeight(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                g.setColor(p.getCell(i, j).getColor());
                g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);
            }
        }
        if (ifDrawGrid) {
            g.setColor(Color.darkGray);
            for (int i = 0; i < p.getWidth(); i++) {
                g.fillRect(i * squareSize, 0, 1, p.getHeight() * squareSize);
            }
            for (int i = 0; i < p.getHeight(); i++) {
                g.fillRect(0, i * squareSize, p.getWidth() * squareSize, 1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        elem.setElementOnBoard((int) y / squareSize, (int) x / squareSize, p);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isClicked = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isClicked) {
            int x = e.getX();
            int y = e.getY();
            elem.setElementOnBoard((int) y / squareSize, (int) x / squareSize, p);
        }
        repaint();
    }

}
