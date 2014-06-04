/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
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
    private int scale;
    private int paneSize = 600;

    public GraficBoardPanel(Population p) {
        this.p = p;
        scale = paneSize / p.getHeight();
    }

    public int getPaneSize() {
        return paneSize;
    }
    
    public void setPaneSize(int i) {
        paneSize = i;
    }
    
    public void setPopulation(Population p) {
        this.p = p;
        scale = paneSize / p.getHeight();
    }

    public Population getPopulation() {
        return p;
    }

    public void setElement(Element element) {
        elem = element;
    }

    public int getScale() {
        return scale;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < p.getWidth(); i++) {
            for (int j = 0; j < p.getHeight(); j++) {
                g.setColor(p.getCell(i, j).getColor());
                g.fillRect(i * scale, j * scale, scale, scale);
            }
        }

        g.setColor(Color.darkGray);
        for (int i = 0; i < p.getWidth(); i++) {
            g.fillRect(i * scale, 0, 1, paneSize);
        }
        for (int i = 0; i < p.getWidth(); i++) {
            g.fillRect(0, i * scale, paneSize, 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
            isClicked = true;
        } else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
            isClicked = false;
        }
        int x = e.getX();
        int y = e.getY();
        elem.setElementOnBoard((int) x / scale, (int) y / scale, p);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
            elem.setElementOnBoard((int) x / scale, (int) y / scale, p);
        }
    }

}
