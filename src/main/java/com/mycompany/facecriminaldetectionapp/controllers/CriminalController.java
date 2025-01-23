/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.controllers;

import com.mycompany.facecriminaldetectionapp.model.Criminal;
import com.mycompany.facecriminaldetectionapp.model.FaceDetector;
import com.mycompany.facecriminaldetectionapp.services.CriminalService;
import java.awt.image.BufferedImage;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import utilities.Utility;

/**
 *
 * @author user
 */
@Component
public class CriminalController {

    private final FaceDetector faceDetector = new FaceDetector();
    @Autowired
    private CriminalService criminalService;

 

    public List<Rect> getBoundingBoxes(BufferedImage image) {

        Mat mat = Utility.bufferedImageToMat(image);

        return faceDetector.getBoundingBoxes(mat);

    }

    public void registerCriminal(Criminal criminal) {
        System.out.println("metodo del controller");
        criminalService.saveCriminal(criminal);
    }

}
