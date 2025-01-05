/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.model;

/**
 *
 * @author user
 */
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class OpencvTest {

  

    public static void main(String args[]) {

        // For proper execution of native libraries 
        // Core.NATIVE_LIBRARY_NAME must be loaded before 
        // calling any of the opencv methods 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Face detector creation by loading source cascade 
        // xml file using CascadeClassifier and must be 
        // placed in same directory of the source java file 
        // File is available here on git as mentioned above 
        // prior to code 
        CascadeClassifier faceDetector
                = new CascadeClassifier();
        faceDetector.load(
                "C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\haarcascade_frontalface_alt.xml");

        // Reading the input image 
        Mat image = Imgcodecs.imread("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\Brad_Pitt.jpg");
//Mat bgrImage = new Mat();

//Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2BGR);
        // Detecting faces 
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image,
                faceDetections);

        // Creating a rectangular box which represents for 
        // faces detected 
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                   image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width,
                            rect.y + rect.height),
                    new Scalar(0, 0, 255));
            System.out.println(rect);
        }
        

        // Saving the output image 
        String filename = "Ouput.jpg";

        Imgcodecs.imwrite("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\brad_bb.jpg", image);

        // Display message for successful execution of 
        // program 
        System.out.print("Face Detected");
    }
}
