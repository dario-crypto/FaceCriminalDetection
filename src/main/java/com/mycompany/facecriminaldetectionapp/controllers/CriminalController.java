/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.controllers;

import com.mycompany.facecriminaldetectionapp.model.Criminal;
import com.mycompany.facecriminaldetectionapp.model.FaceDetector;
import java.awt.image.BufferedImage;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import utilities.Utility;

/**
 *
 * @author user
 */
public class CriminalController {
    
    private final FaceDetector faceDetector = new FaceDetector();
    
    public void saveCriminal(Criminal criminal) {
        System.out.println("Criminale salvato");
        
    }
    
    public List<Rect> getBoundingBoxes(BufferedImage image) {
        
        Mat mat = Utility.bufferedImageToMat(image);
        
        return faceDetector.getBoundingBoxes(mat);
        
    }
    
}
