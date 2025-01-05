/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.model;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author user
 */
public class FaceDetector {
    // prior to code 

    private final CascadeClassifier faceDetector;

    public FaceDetector() {

        faceDetector = new CascadeClassifier();
        faceDetector.load(
                "C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\haarcascade_frontalface_alt.xml");

    }

    public List<Rect> getBoundingBoxes(Mat image) {
    
        List<Rect> rects = new ArrayList<>();

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image,
                faceDetections);

        // Creating a rectangular box which represents for 
        // faces detected 
        for (Rect rect : faceDetections.toArray()) {
            rects.add(rect);
        }
        return rects;
    }

    public static void main(String args[]) {

        Mat image = Imgcodecs.imread("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\Brad_Pitt.jpg");
        FaceDetector faceDetector1 = new FaceDetector();
        List<Rect> rects = faceDetector1.getBoundingBoxes(image);
        rects.forEach(r -> System.out.println(r));
    }
}
