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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import wireworld.InputOutput;
import wireworld.Population;
import wireworld.WireWorld;

/**
 *
 * @author Piotrek
 */
public class GUI extends JFrame implements ActionListener {

    public WireWorld game = new WireWorld();
    public File inOut = null;

    private JPanel painter;

    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem openFileMenuItem,
            writeFileMenuItem,
            exitMenuItem,
            helpMenuItem,
            aboutMenuItem;
    private JFileChooser fileChooser;

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
            tailRadioButton;
    private JSlider delaySlider;

    public GUI() {
        game = new WireWorld();
        game.setPopulation(new Population(60, 60));
        fileChooser = new JFileChooser();

        initFrame();
        initUpMenu();
        initRightMenu();
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

        fileChooser = new JFileChooser();
        fileMenu = new JMenu("Plik");
        openFileMenuItem = new JMenuItem("Otwórz plik...");
        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    inOut = fileChooser.getSelectedFile();
                    game.setPopulation(InputOutput.readFromFile(inOut));
                }
            }
        });
        writeFileMenuItem = new JMenuItem("Zapisz plik...");
        writeFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File save = fileChooser.getSelectedFile();
                    InputOutput.writeToFile(game.getPopulation(), save);
                }
            }
        });
        exitMenuItem = new JMenuItem("Wyjście");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        helpMenu = new JMenu("Pomoc");
        helpMenuItem = new JMenuItem("Pomoc");
        helpMenuItem.addActionListener(this);
        aboutMenuItem = new JMenuItem("O programie");
        aboutMenuItem.addActionListener(this);

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        fileMenu.add(openFileMenuItem);
        fileMenu.add(writeFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);
    }

    private void initRightMenu() {
        startButton = new JButton("Start");
        startButton.setBounds(620, 0, 80, 30);
        startButton.addActionListener(this);
        startButton.setVisible(true);
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setBounds(700, 0, 80, 30);
        stopButton.setVisible(true);
        stopButton.addActionListener(this);
        add(stopButton);

        nextGenerationButton = new JButton("Następna generacja");
        nextGenerationButton.setBounds(620, 30, 160, 30);
        nextGenerationButton.setVisible(true);
        nextGenerationButton.addActionListener(this);
        add(nextGenerationButton);

        counterLabel = new JLabel("Ilość generacji");
        counterLabel.setBounds(630, 60, 90, 15);
        counterLabel.setVisible(true);
        add(counterLabel);

        counterTextField = new JTextField();
        counterTextField.setBounds(620, 120, 160, 29);
        counterTextField.setVisible(true);
        counterTextField.setText(String.valueOf(game.getNumberOfGenerations()));
        add(counterTextField);

        clearButton = new JButton("Wyczyść planszę");
        clearButton.setBounds(620, 350, 160, 29);
        clearButton.setVisible(true);
        clearButton.addActionListener(this);
        add(clearButton);

        delaySlider = new JSlider(100, 1000, 100);
        delaySlider.setBounds(610, 240, 190, 100);
        delaySlider.setMajorTickSpacing(200);
        delaySlider.setMinorTickSpacing(100);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setVisible(true);
        add(delaySlider);

        sliderLabel = new JLabel("Przerwa w ms");
        sliderLabel.setBounds(610, 220, 150, 30);
        sliderLabel.setVisible(true);
        add(sliderLabel);

        elementsLabel = new JLabel("Wybór elementu");
        elementsLabel.setBounds(605, 150, 200, 30);
        elementsLabel.setVisible(true);
        add(elementsLabel);

        diodeRadioButton = new JRadioButton("Dioda");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        GUI okno;
        okno = new GUI();
    }
}
