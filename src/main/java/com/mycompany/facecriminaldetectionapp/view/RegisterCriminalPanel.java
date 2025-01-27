/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

import com.mycompany.facecriminaldetectionapp.controllers.CriminalController;
import com.mycompany.facecriminaldetectionapp.model.Criminal;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.TextAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */

public class RegisterCriminalPanel extends JPanel {

    private CriminalController cc;

    private final JTextField nameField = new JTextField();
    private final JTextField surnameField = new JTextField();
    //private final CriminalController criminalController = new CriminalController();
    private JDateChooser dateChooser = new JDateChooser();
    private final ButtonGroup genderGroup = new ButtonGroup();
    private final JTextField nationalityField = new JTextField();
    
    private final JTextField heigthField = new JTextField();
    private final JTextField weightField = new JTextField();
    private final JTextField eyesColorField = new JTextField();
    private final JTextField hairColorField = new JTextField();
    private final JTextField distinctiveSignsField = new JTextField();
    private final JRadioButton maleButton = new JRadioButton("Male");
    private final JRadioButton femaleButton = new JRadioButton("Female");
    private final JButton saveButton = new JButton("Save");
    private final JButton loadImageButton = new JButton("Load Image");
    private final JPanel fileChooserPanel = new JPanel();
    private final Dimension textFieldSize = new Dimension(200, 20);
    private final Color backGroundColor = Color.BLACK;
    private final JPanel savePanel = new JPanel();
    private final JPanel credentialsPanel = new JPanel();
    private final JPanel credentialPanelWrapper = new JPanel();
    private final JPanel gridPanel = new JPanel();
    private final JLabel imagesLoadLabel = new JLabel("Nessuna immagine caricata");
    private final Set<String> pathImages = new HashSet<>();
    private final Image backgroundImage;
    private String pathImage;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.err.println("sfondooooo");
        g.drawImage(backgroundImage, 0, 0, null);

    }

    public RegisterCriminalPanel(CriminalController cc) {
        this.cc = cc;
        System.out.println("Creato da srping");
        backgroundImage = Toolkit.getDefaultToolkit().createImage("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\cyber-criminal.png");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(backGroundColor);

        savePanel.setBackground(backGroundColor);

        credentialsPanel.setBackground(backGroundColor);
        credentialPanelWrapper.setBackground(backGroundColor);
        gridPanel.setBackground(backGroundColor);

        GridLayout grid = new GridLayout(18, 2);
        grid.setVgap(5);

        gridPanel.setLayout(grid);

        //credentialsPanel.setLayout(grid);
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));

        // p1.setLayout(new BorderLayout());
        //p1.add(new JLabel("Name"), BorderLayout.WEST);
        //p1.add(nameField, BorderLayout.EAST);
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBackground(Color.WHITE);
        //nameLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        nameLabel.setForeground(Color.white);
        gridPanel.add(nameLabel);
        gridPanel.add(nameField);
        
        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setForeground(Color.WHITE);
        gridPanel.add(surnameLabel);
        gridPanel.add(surnameField);
        
        //credentialsPanel.add(p1)

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setForeground(Color.WHITE);
        gridPanel.add(dobLabel);

        gridPanel.add(dateChooser);

        maleButton.setBackground(backGroundColor);
        femaleButton.setBackground(backGroundColor);
        maleButton.setForeground(Color.white);
        femaleButton.setForeground(Color.WHITE);
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        JLabel nationalityLabel = new JLabel("Nationality");
        nationalityLabel.setForeground(Color.WHITE);
        gridPanel.add(nationalityLabel);
        gridPanel.add(nationalityField);

        JLabel hairColorLabel = new JLabel("Hair color");
        hairColorLabel.setForeground(Color.WHITE);
        gridPanel.add(hairColorLabel);

        gridPanel.add(hairColorField);

        JLabel eyesColorLabel = new JLabel("Eyes color");
        eyesColorLabel.setForeground(Color.WHITE);
        gridPanel.add(eyesColorLabel);
        gridPanel.add(eyesColorField);

        JLabel heightLabel = new JLabel("Height(cm)");
        heightLabel.setForeground(Color.WHITE);
        gridPanel.add(heightLabel);
        gridPanel.add(heigthField);

        JLabel weightLabel = new JLabel("Weight(cm)");
        weightLabel.setForeground(Color.WHITE);
        gridPanel.add(weightLabel);
        gridPanel.add(weightField);

        JLabel signsLabel = new JLabel("Distinctive signs");
        signsLabel.setForeground(Color.WHITE);
        gridPanel.add(signsLabel);
        gridPanel.add(distinctiveSignsField);

       
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setForeground(Color.WHITE);
        //credentialsPanel.add(genderLabel);

        credentialsPanel.add(gridPanel);
        credentialsPanel.add(Box.createHorizontalGlue());
        credentialsPanel.add(genderLabel);
        credentialsPanel.add(maleButton);
        credentialsPanel.add(femaleButton);

        imagesLoadLabel.setForeground(Color.WHITE);
        credentialsPanel.add(loadImageButton);
        credentialsPanel.add(imagesLoadLabel);
        //building savePanel
        savePanel.add(saveButton);

        credentialPanelWrapper.add(Box.createHorizontalGlue());
        credentialPanelWrapper.add(credentialsPanel);

        credentialPanelWrapper.add(Box.createHorizontalGlue());

        //add(credentialsPanel);
        add(credentialPanelWrapper);

        //add(savePanel);
        add(savePanel);

        //add(p1, BorderLayout.EAST);
        nameField.setPreferredSize(textFieldSize);
        nationalityField.setPreferredSize(textFieldSize);
        hairColorField.setPreferredSize(textFieldSize);
        eyesColorField.setPreferredSize(textFieldSize);
        distinctiveSignsField.setPreferredSize(textFieldSize);
        dateChooser.setPreferredSize(textFieldSize);
        heigthField.setPreferredSize(textFieldSize);
        weightField.setPreferredSize(textFieldSize);

        maleButton.setActionCommand("male");
        femaleButton.setActionCommand("female");
        loadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & PNG Images", "jpg", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(fileChooserPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Percorso selezionato: "
                            + chooser.getSelectedFile().getAbsolutePath());
                    pathImage = chooser.getSelectedFile().getAbsolutePath();
                    pathImages.add(pathImage);
                    imagesLoadLabel.setText(getImageNames());

                }

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Criminal c = new Criminal();

                String name = nameField.getText();

                if (name.isEmpty() || name.isBlank()) {
                    System.out.println("Campo Name e obbligatorio");
                    return;
                }

                c.setName(name);

                Date selectedDate = dateChooser.getDate();

                // LocalDate date = LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.get);
                if (selectedDate != null) {

                    LocalDate date = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    System.out.println(date);
                    c.setDob(date);
                }
                if (genderGroup.getSelection() != null) {
                    String genderSelected = genderGroup.getSelection().getActionCommand();
                    c.setGender(name);
                    System.out.println(genderSelected);
                }
                if (pathImage != null) {

                    if (!pathImage.isEmpty() && !pathImage.isBlank()) {
                        c.setImageName(pathImage);
                    }
                }

                cc.registerCriminal(c);
            }
        });

    }

    public String getImageNames() {
        StringBuilder sb = new StringBuilder();
        for (String path : pathImages) {
            String[] sp = path.split("/");
            String name = sp[sp.length - 1];
            sb.append(name);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }
    /*
    public static void main(String args[]) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.add(new RegisterCriminalPanel());
        frame.setVisible(true);
    }
     */
}
