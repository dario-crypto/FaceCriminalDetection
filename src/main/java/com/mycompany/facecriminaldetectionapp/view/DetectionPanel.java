/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.opencv.core.Core;

/**
 *
 * @author user
 */
public class DetectionPanel extends JPanel {

    private final WebcamViewerExample webcamViewer = new WebcamViewerExample();
    private final AlbumPanel albumPanel = new AlbumPanel(150, 150, 6);
    private final JButton shootButton = new JButton();

    public DetectionPanel() {
        webcamViewer.run();
        setBackground(Color.BLACK);

        albumPanel.setPreferredSize(new Dimension(500, 600));
        JPanel helperPanel = new JPanel();
        // setBackground(Color.red);
        albumPanel.sethGap(5);
        albumPanel.setvGap(5);
        setLayout(new BorderLayout());

        helperPanel.setLayout(new BoxLayout(helperPanel, BoxLayout.Y_AXIS));
        helperPanel.add(webcamViewer);
        helperPanel.add(Box.createVerticalStrut(10));
        helperPanel.add(shootButton);
        helperPanel.add(Box.createVerticalStrut(10));
        webcamViewer.setPreferredSize(new Dimension(640, 480));
        // helperPanel.setVisible(true);
        helperPanel.setBackground(Color.BLACK);
        add(helperPanel, BorderLayout.WEST);
        add(albumPanel, BorderLayout.EAST);

        //add(webcamViewer);
        // setPreferredSize(new Dimension(800, 600));
        //add(albumPanel);
        //ImageIcon cameraIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\camera.png");
        //utilities.Utility.resizeImage(cameraIcon, 50, 50);
        //shootButton.setIcon(cameraIcon);
        shootButton.setIcon(new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\camera_resized.png"));
        shootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Image image = webcamViewer.getImage();
                if (image != null) {

                    albumPanel.addImage(image);
                }
            }

        });
    }

    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setSize(new Dimension(1600, 800));
            frame.setVisible(true);

            frame.add(new DetectionPanel());
            frame.setPreferredSize(new Dimension(1200, 600));

            frame.pack();
        });
    }
}
