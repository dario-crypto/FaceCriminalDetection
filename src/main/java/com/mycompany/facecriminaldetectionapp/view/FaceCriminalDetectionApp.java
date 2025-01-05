/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.facecriminaldetectionapp.view;

import com.github.sarxos.webcam.Webcam;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.CvType;
import org.opencv.core.MatOfRect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author user
 */
public class FaceCriminalDetectionApp {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Carica il file dell'immagine
        String imagePath = "C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images";
        Mat image = Imgcodecs.imread(imagePath);

        // Carica il classificatore Haar per il rilevamento facciale
        // CascadeClassifier faceCascade = new CascadeClassifier("haarcascade_frontalface_default.xml");
        // Converte l'immagine in scala di grigi
    }
}
