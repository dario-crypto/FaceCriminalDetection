/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.opencv.core.Core;

/**
 *
 * @author user
 */
public class MainWindow extends JFrame {

    private final Dimension size = new Dimension(800, 700);
    private final WebcamViewerExample webcamViewer = new WebcamViewerExample();
    private final JTabbedPane tabPanel = new JTabbedPane();
    private final RegisterCriminalPanel registerPanel = new RegisterCriminalPanel();

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size);
        setVisible(true);
        
        tabPanel.add("Webcam Detection",webcamViewer);
        tabPanel.add("Crimanal Register",registerPanel);
        add(tabPanel);
        webcamViewer.run();
        
        
        

    }

    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }

}
