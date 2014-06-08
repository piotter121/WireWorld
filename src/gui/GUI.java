/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import wireworld.*;

/**
 *
 * @author Piotrek
 */
public class GUI extends JFrame implements ActionListener {

    private WireWorld game = new WireWorld();
    private boolean isStoped = true;

    private GraficBoardPanel grid;

    private JMenuBar menuBar;
    private JMenu fileMenu, infoMenu;
    private JMenuItem openFileMenuItem,
            saveFileMenuItem,
            exitMenuItem,
            aboutMenuItem;
    private final JFileChooser fileChooser = new JFileChooser();

    private JButton startButton,
            nextGenerationButton,
            clearButton,
            stopButton,
            defaultSettingsButton;
    private JLabel counterLabel,
            sliderLabel,
            elementsLabel,
            optionsLabel;
    private JTextField counterTextField;
    private ButtonGroup elementBox;
    private JRadioButton diodeRadioButton,
            conductorRadioButton,
            headRadioButton,
            tailRadioButton,
            insulatorRadioButton;
    private JSlider delaySlider;
    private JCheckBox checkGrid;

    public GUI() {
        game = new WireWorld();
        game.setPopulation(new Population(60, 60));

        initFrame();
        initUpMenu();
        initRightMenu();

        grid = new GraficBoardPanel(game.getPopulation());
        grid.setBounds(0, 0, grid.getPanelHeight(), grid.getPanelWidth());
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
                isStoped = true;
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File opened = fileChooser.getSelectedFile();
                    game.setPopulation(InputOutput.readFromFile(opened));
                    setUpGrid();
                }
            }
        });
        saveFileMenuItem = new JMenuItem("Zapisz plik...");
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        saveFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStoped = true;
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
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setBounds(700, 0, 80, 30);
        stopButton.addActionListener(this);
        stopButton.setToolTipText("Naciśnij, aby zatrzymać automat");
        add(stopButton);

        nextGenerationButton = new JButton("Następna generacja");
        nextGenerationButton.setBounds(620, 30, 160, 30);
        nextGenerationButton.addActionListener(this);
        nextGenerationButton.setToolTipText("Naciśnij, aby przejść jeden krok w "
                + "przód");
        add(nextGenerationButton);

        counterLabel = new JLabel("Ilość generacji");
        counterLabel.setBounds(655, 65, 90, 15);
        add(counterLabel);

        counterTextField = new JTextField();
        counterTextField.setBounds(620, 80, 160, 30);
        counterTextField.setToolTipText("Wpisz tutaj liczbę generacji");
        counterTextField.setText(String.valueOf(game.getNumberOfGenerations()));
        counterTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setNumberOfGenerations(Integer.parseInt(counterTextField.getText()));
            }
        });
        add(counterTextField);

        sliderLabel = new JLabel("Opóźnienie w ms");
        sliderLabel.setBounds(645, 125, 100, 15);
        add(sliderLabel);

        delaySlider = new JSlider(100, 1000, 100);
        delaySlider.setBounds(620, 140, 160, 60);
        delaySlider.setMajorTickSpacing(200);
        delaySlider.setMinorTickSpacing(100);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setToolTipText("Przeciągnij suwak, aby zmienić opóźnienie");
        add(delaySlider);

        clearButton = new JButton("Wyczyść planszę");
        clearButton.setBounds(620, 210, 160, 30);
        clearButton.setToolTipText("Naciśnij, aby wyczyścić planszę z elementów");
        clearButton.addActionListener(this);
        add(clearButton);

        elementsLabel = new JLabel("Wybór elementu");
        elementsLabel.setBounds(640, 250, 120, 30);
        add(elementsLabel);

        diodeRadioButton = new JRadioButton("Dioda", false);
        diodeRadioButton.setBounds(600, 280, 100, 20);
        diodeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setElement(new Diode());
            }
        });
        add(diodeRadioButton);

        conductorRadioButton = new JRadioButton("Przewodnik", true);
        conductorRadioButton.setBounds(700, 280, 100, 20);
        conductorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setElement(new Cell(new Conductor()));
            }
        });
        add(conductorRadioButton);

        headRadioButton = new JRadioButton("Głowa", false);
        headRadioButton.setBounds(600, 300, 100, 20);
        headRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setElement(new Cell(new Head()));
            }
        });
        add(headRadioButton);

        tailRadioButton = new JRadioButton("Ogon", false);
        tailRadioButton.setBounds(700, 300, 100, 20);
        tailRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setElement(new Cell(new Tail()));
            }
        });
        add(tailRadioButton);

        insulatorRadioButton = new JRadioButton("Izolator", false);
        insulatorRadioButton.setBounds(600, 320, 100, 20);
        insulatorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setElement(new Cell(new Insulator()));
            }
        });
        add(insulatorRadioButton);

        elementBox = new ButtonGroup();
        elementBox.add(diodeRadioButton);
        elementBox.add(conductorRadioButton);
        elementBox.add(headRadioButton);
        elementBox.add(tailRadioButton);
        elementBox.add(insulatorRadioButton);

        optionsLabel = new JLabel("Opcje");
        optionsLabel.setBounds(670, 345, 60, 30);
        add(optionsLabel);

        checkGrid = new JCheckBox("Widoczna siatka", true);
        checkGrid.setBounds(640, 375, 120, 30);
        checkGrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setIfDrawGrid(checkGrid.isSelected());
                grid.repaint();
            }
        });
        add(checkGrid);

        defaultSettingsButton = new JButton("Przywróć domyślne");
        defaultSettingsButton.setBounds(620, 405, 160, 30);
        defaultSettingsButton.addActionListener(this);
        defaultSettingsButton.setToolTipText("Naciśnij, aby zresetować ustawienia");
        add(defaultSettingsButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startButton) {
            startClick();
        } else if (source == stopButton) {
            stopClick();
        } else if (source == clearButton) {
            clearClick();
        } else if (source == nextGenerationButton) {
            nextGenerationClick();
        } else if (source == defaultSettingsButton) {
            defaultSettingsClick();
        }
    }

    private void startClick() {
        isStoped = false;
        new Thread() {
            @Override
            public void run() {
                game.setNumberOfGenerations(Integer.parseInt(counterTextField.getText()));
                while (game.getNumberOfGenerations() > 0 && isStoped == false) {
                    game.nextStep();
                    counterTextField.setText(String.valueOf(game.getNumberOfGenerations()));
                    setUpGrid();
                    try {
                        Thread.sleep(delaySlider.getValue());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    private void stopClick() {
        isStoped = true;
    }

    private void nextGenerationClick() {
        game.nextStep();
        counterTextField.setText(String.valueOf(game.getNumberOfGenerations()));
        setUpGrid();
    }

    private void clearClick() {
        game.getPopulation().clear();
        setUpGrid();
    }

    private void defaultSettingsClick() {
        isStoped = true;
        game.setNumberOfGenerations(1000);
        game.setPopulation(new Population(60, 60));
        counterTextField.setText(String.valueOf(game.getNumberOfGenerations()));
        setUpGrid();
    }

    private void setUpGrid() {
        grid.setPopulation(game.getPopulation());
        grid.repaint();
    }
}
