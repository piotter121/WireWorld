/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import wireworld.*;

/**
 *
 * @author Piotrek
 */
public class GUI extends JFrame implements ActionListener {

    WireWorld game = null;

    GraficBoardPanel painter;
    JMenuBar upMenuBar;
    JMenu mainMenu;
    JMenuItem readFileMenuItem, writeFileMenuItem, helpMenuItem;
    JFileChooser fileChooser;

    JButton startButton;
    JButton nextGenerationButton;
    JButton clearButton;
    JButton stopButton;
    JLabel counterLabel;
    JTextField counterTextField;
    JRadioButton diodeButton;
    JSlider slider;
    JLabel sliderLabel;
    JLabel elementsLabel;

    public GUI() {
        game = new WireWorld();
        game.setPopulation(new Population(60, 60));
        fileChooser = new JFileChooser();

        initFrame();
        initUpMenu();
        initRightMenu();

    }

    private void initFrame() {
        setSize(1000, 800);
        setResizable(false);
        setTitle("WireWorld");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initUpMenu() {
        upMenuBar = new JMenuBar();
        mainMenu = new JMenu("Opcje");
        readFileMenuItem = new JMenuItem("Wczytaj plik ...");
        readFileMenuItem.addActionListener(this);
        writeFileMenuItem = new JMenuItem("Zapisz plik...");
        writeFileMenuItem.addActionListener(this);
        helpMenuItem = new JMenuItem("Pomoc");
        helpMenuItem.addActionListener(this);

        setJMenuBar(upMenuBar);
        upMenuBar.add(mainMenu);
        mainMenu.add(readFileMenuItem);
        mainMenu.add(writeFileMenuItem);
        mainMenu.add(helpMenuItem);
    }

    private void initRightMenu() {
        startButton = new JButton("Start");
        startButton.setBounds(800, 00, 200, 30);
        startButton.addActionListener(this);
        startButton.setVisible(true);
        add(startButton);

        counterLabel = new JLabel("Ilość generacji");
        counterLabel.setBounds(805, 90, 200, 30);
        counterLabel.setVisible(true);
        add(counterLabel);

        counterTextField = new JTextField();
        counterTextField.setBounds(800, 120, 200, 30);
        counterTextField.setVisible(true);
        counterTextField.setText("" + game.getNumberOfGenerations());
        add(counterTextField);

        nextGenerationButton = new JButton("Następna generacja");
        nextGenerationButton.setBounds(800, 60, 200, 30);
        nextGenerationButton.setVisible(true);
        nextGenerationButton.addActionListener(this);
        add(nextGenerationButton);

        stopButton = new JButton("Stop");
        stopButton.setBounds(800, 30, 200, 30);
        stopButton.setVisible(true);
        stopButton.addActionListener(this);
        add(stopButton);

        clearButton = new JButton("Wyczyść planszę");
        clearButton.setBounds(800, 350, 200, 30);
        clearButton.setVisible(true);
        clearButton.addActionListener(this);
        add(clearButton);

        slider = new JSlider(50, 1000, 100);
        slider.setBounds(810, 240, 190, 100);
        slider.setMajorTickSpacing(200);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setVisible(true);
        add(slider);

        sliderLabel = new JLabel("Przerwa w ms");
        sliderLabel.setBounds(805, 210, 200, 30);
        sliderLabel.setVisible(true);
        add(sliderLabel);

        elementsLabel = new JLabel("Wybór elementu");
        elementsLabel.setBounds(805, 150, 200, 30);
        elementsLabel.setVisible(true);
        add(elementsLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}