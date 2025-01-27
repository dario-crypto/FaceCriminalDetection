/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp;

import com.mycompany.facecriminaldetectionapp.controllers.CriminalController;
import com.mycompany.facecriminaldetectionapp.model.Criminal;
import com.mycompany.facecriminaldetectionapp.repositories.CriminalRepository;
import com.mycompany.facecriminaldetectionapp.services.CriminalService;
import com.mycompany.facecriminaldetectionapp.view.MainWindow;
import java.awt.EventQueue;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Configuration
public class CriminalApplication {

    public static void main(String args[]) {

        //docker exec -it 564ab0f6c9e2365f4c4410e0920d35a3ce6916a4558c7cc33a7a7a72a6a23eb9 psql -U criminal -d criminaldb
        //criminaldb=# select * from criminals \g
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // ApplicationContext context = SpringApplication.run(CriminalApplication.class, args);
        //context.getBean(MainWindow.class);
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MainWindow.class)
                .headless(false).run(args);
        System.out.println("Bean names:");
        for(String name :ctx.getBeanDefinitionNames()){
            System.out.println(name);
        }


        EventQueue.invokeLater(() -> {
            MainWindow ex = ctx.getBean(MainWindow.class);

            ex.setVisible(true);
        });
        /*
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.pack();

            // ImageIcon img = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\cyber-criminal.png");
            // mainWindow.setIconImage(img.getImage());
        });
         */
    }

}
