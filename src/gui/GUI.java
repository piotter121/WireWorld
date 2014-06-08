/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import wireworld.Cell;
import wireworld.Conductor;
import wireworld.InputOutput;
import wireworld.Population;
import wireworld.WireWorld;

/**
 *
 * @author Piotrek
 */
public class GUI extends JFrame implements ActionListener {

    public WireWorld game = new WireWorld();

    private GraficBoardPanel grid;

    private JMenuBar menuBar;
    private JMenu fileMenu, infoMenu;
    private JMenuItem openFileMenuItem,
            saveFileMenuItem,
            exitMenuItem,
            aboutMenuItem;
    private JFileChooser fileChooser = new JFileChooser();

    private JButton startButton,
            nextGenerationButton,
            clearButton,
            stopButton;
    private JLabel counterLabel,
            sliderLabel,
            elementsLabel;
    private JTextField counterTextField;
    private ButtonGroup elementBox;
    private JRadioButton diodeRadioButton,
            conductorRadioButton,
            headRadioButton,
            tailRadioButton,
            insulatorRadioButton;
    private JSlider delaySlider;

    public GUI() {
        game = new WireWorld();
        game.setPopulation(new Population(60, 60));

        initFrame();
        initUpMenu();
        initRightMenu();

        grid = new GraficBoardPanel(game.getPopulation());
        grid.setBounds(0, 0, 600, 600);
        grid.addMouseListener(grid);
        grid.addMouseMotionListener(grid);
        grid.setElement(new Cell(new Conductor()));
        add(grid);
        grid.setVisible(true);

        setVisible(true);
    }

    private void initFrame() {
        setSize(800, 600);
        setResizable(false);
        setTitle("WireWorld");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(50, 50);
    }

    private void initUpMenu() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("Plik");
        infoMenu = new JMenu("Informacje");
        openFileMenuItem = new JMenuItem("Otwórz plik...");
        openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File opened = fileChooser.getSelectedFile();
                    game.setPopulation(InputOutput.readFromFile(opened));
                }
            }
        });
        saveFileMenuItem = new JMenuItem("Zapisz plik...");
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        saveFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File save = fileChooser.getSelectedFile();
                    InputOutput.writeToFile(game.getPopulation(), save);
                }
            }
        });
        exitMenuItem = new JMenuItem("Wyjście");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        aboutMenuItem = new JMenuItem("O programie");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Program symuluje działanie "
                        + "automatu komórkowego WireWorld\nz zastosowaniem sąsiedztwa "
                        + "Moore'a.", "O programie", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(infoMenu);
        fileMenu.add(openFileMenuItem);
        fileMenu.add(saveFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        infoMenu.add(aboutMenuItem);
    }

    private void initRightMenu() {
        startButton = new JButton("Start");
        startButton.setBounds(620, 0, 80, 30);
        startButton.addActionListener(this);
        startButton.setToolTipText("Naciśnij, aby uruchomić automat");
        startButton.setVisible(true);
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setBounds(700, 0, 80, 30);
        stopButton.addActionListener(this);
        stopButton.setToolTipText("Naciśnij, aby zatrzymać automat");
        stopButton.setVisible(true);
        add(stopButton);

        nextGenerationButton = new JButton("Następna generacja");
        nextGenerationButton.setBounds(620, 30, 160, 30);
        nextGenerationButton.addActionListener(this);
        nextGenerationButton.setToolTipText("Naciśnij, aby przejść jeden krok w "
                + "przód");
        nextGenerationButton.setVisible(true);
        add(nextGenerationButton);

        counterLabel = new JLabel("Ilość generacji");
        counterLabel.setBounds(655, 65, 90, 15);
        counterLabel.setVisible(true);
        add(counterLabel);

        counterTextField = new JTextField();
        counterTextField.setBounds(620, 80, 160, 30);
        counterTextField.setToolTipText("Wpisz tutaj liczbę generacji");
        counterTextField.setVisible(true);
        counterTextField.setText(String.valueOf(game.getNumberOfGenerations()));
        add(counterTextField);

        sliderLabel = new JLabel("Opóźnienie w ms");
        sliderLabel.setBounds(645, 125, 100, 15);
        sliderLabel.setVisible(true);
        add(sliderLabel);

        delaySlider = new JSlider(100, 1000, 100);
        delaySlider.setBounds(610, 140, 190, 60);
        delaySlider.setMajorTickSpacing(200);
        delaySlider.setMinorTickSpacing(100);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setToolTipText("Przeciągnij suwak, aby zmienić opóźnienie");
        delaySlider.setVisible(true);
        add(delaySlider);

        clearButton = new JButton("Wyczyść planszę");
        clearButton.setBounds(620, 210, 160, 30);
        clearButton.setToolTipText("Naciśnij, aby wyczyścić planszę z elementów");
        clearButton.setVisible(true);
        clearButton.addActionListener(this);
        add(clearButton);

        elementsLabel = new JLabel("Wybór elementu");
        elementsLabel.setBounds(640, 250, 120, 30);
        elementsLabel.setVisible(true);
        add(elementsLabel);

        diodeRadioButton = new JRadioButton("Dioda", true);
        diodeRadioButton.setBounds(600, 280, 100, 20);
        diodeRadioButton.setVisible(true);
        diodeRadioButton.addActionListener(this);
        add(diodeRadioButton);

        conductorRadioButton = new JRadioButton("Przewodnik", false);
        conductorRadioButton.setBounds(700, 280, 100, 20);
        conductorRadioButton.setVisible(true);
        conductorRadioButton.addActionListener(this);
        add(conductorRadioButton);

        headRadioButton = new JRadioButton("Głowa", false);
        headRadioButton.setBounds(600, 300, 100, 20);
        headRadioButton.setVisible(true);
        headRadioButton.addActionListener(this);
        add(headRadioButton);

        tailRadioButton = new JRadioButton("Ogon", false);
        tailRadioButton.setBounds(700, 300, 100, 20);
        tailRadioButton.setVisible(true);
        tailRadioButton.addActionListener(this);
        add(tailRadioButton);

        insulatorRadioButton = new JRadioButton("Izolator", false);
        insulatorRadioButton.setBounds(600, 320, 100, 20);
        insulatorRadioButton.setVisible(true);
        insulatorRadioButton.addActionListener(this);
        add(insulatorRadioButton);

        elementBox = new ButtonGroup();
        elementBox.add(diodeRadioButton);
        elementBox.add(conductorRadioButton);
        elementBox.add(headRadioButton);
        elementBox.add(tailRadioButton);
        elementBox.add(insulatorRadioButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
