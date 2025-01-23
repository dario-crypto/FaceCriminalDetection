/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

import com.mycompany.facecriminaldetectionapp.controllers.CriminalController;
import com.mycompany.facecriminaldetectionapp.model.Criminal;
import com.mycompany.facecriminaldetectionapp.repositories.CriminalRepository;
import com.mycompany.facecriminaldetectionapp.services.CriminalService;
import com.mycompany.facecriminaldetectionapp.services.CriminalServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@SpringBootApplication(scanBasePackages = {"com.mycompany.facecriminaldetectionapp"})
@EnableJpaRepositories("com.mycompany.facecriminaldetectionapp.repositories")
@EntityScan(basePackages = "com.mycompany.facecriminaldetectionapp.model")

public class MainWindow extends JFrame {

    //@Autowired
    //private CriminalController cc;
    //private final Dimension size = new Dimension(800, 700);
    //private final WebcamViewerExample webcamViewer = new WebcamViewerExample();
    private final JTabbedPane tabPanel = new JTabbedPane();

    private final RegisterCriminalPanel registerPanel;
    private final DetectionPanel detectionPanel = new DetectionPanel();

    @Autowired
    public MainWindow(CriminalController criminalController) {
        registerPanel = new RegisterCriminalPanel(criminalController);

 
        ImageIcon img = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\cyber-criminal.png");
        this.setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(size);
        setVisible(true);

        tabPanel.add("Webcam Detection", detectionPanel);

        tabPanel.add("Criminal Register", registerPanel);
        add(tabPanel);
        this.pack();
        //webcamViewer.run();
    }

    public static void main(String args[]) {

//docker exec -it 564ab0f6c psql -U user -d criminaldb
        //criminaldb=# select * from criminals \g
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SpringApplication.run(MainWindow.class, args);
        /*
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.pack();

            // ImageIcon img = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\cyber-criminal.png");
            // mainWindow.setIconImage(img.getImage());
        });
         */
    }

    @Bean
    public CommandLineRunner testInsert() {

        return args -> {
            System.out.println("Command Line Runner");
            float embedding[] = {1.2f, 3.2f, 1f, 5f, 2.4f};
            Criminal criminal = new Criminal();
            criminal.setName("Dario");
            criminal.setEmbedding(embedding);
            //cc.registerCriminal(criminal);

        };
    }

}
