/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

/**
 *
 * @author user
 */
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import com.mycompany.facecriminaldetectionapp.controllers.CriminalController;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import utilities.Utility;

/*
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
 */
/**
 * Proof of concept of how to handle webcam video stream from Java
 *
 * @author Bartosz Firyn (SarXos)
 */
public class WebcamViewerExample extends JPanel implements Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener, WebcamDiscoveryListener {

    private static final long serialVersionUID = 1L;

    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private WebcamPicker picker = null;
    private BoundingBox boundingBoxPanel = new BoundingBox();
    private CriminalController controller = new CriminalController();

    public class ThreadWriter implements Runnable {

        @Override
        public void run() {

        }

    }

    // Funzione per convertire un BufferedImage in Mat (OpenCV)
    /*
    private static Mat webcamImageToMat(java.awt.image.BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Creazione di una Mat vuota
        Mat mat = new Mat(height, width, org.opencv.core.CvType.CV_8UC3);

        // Copia dei dati dal BufferedImage alla Mat
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = image.getRGB(x, y);
                byte b = (byte) (color & 0xFF);        // Blue
                byte g = (byte) ((color >> 8) & 0xFF); // Green
                byte r = (byte) ((color >> 16) & 0xFF); // Red

                mat.put(y, x, new byte[]{b, g, r}); // Imposta il colore nel Mat
            }
        }

        return mat;
    }
     */
    @Override
    public void run() {

        // Seleziona la prima webcam disponibile
        Webcam.addDiscoveryListener(this);
        //setTitle("Java Webcam Capture POC");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //addWindowListener(this);
        picker = new WebcamPicker();
        picker.addItemListener(this);

        webcam = picker.getSelectedWebcam();

        if (webcam == null) {
            System.out.println("No webcams found...");
            System.exit(1);
        }

        webcam.setViewSize(WebcamResolution.VGA.getSize());

        webcam.addWebcamListener(WebcamViewerExample.this);

        panel = new WebcamPanel(webcam, false);
    

        panel.setFPSDisplayed(true);
        JButton detectButton = new JButton("Detect");

        JPanel optionsPanel = new JPanel();
        optionsPanel.add(detectButton);

        //utilizzo di jLayeredPane per sovrapporre il flusso della webcam con il rettangolo
        JLayeredPane layeredPane = new JLayeredPane();
        panel.setBounds(0, 0, 800, 600);
        boundingBoxPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(boundingBoxPanel, JLayeredPane.PALETTE_LAYER);

        add(picker, BorderLayout.NORTH);
        add(layeredPane, BorderLayout.CENTER);
        //add(boundingBoxPanel,BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.SOUTH);

        detectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Detection in corso...");
                //boundingBoxPanel.repaint();
            }

        });

        //pack();
        setVisible(
                true);

        Thread t = new Thread() {

            @Override
            public void run() {
                panel.start();
            }
        };
      
        t.setName(
                "example-starter");
        t.setDaemon(
                true);
        t.setUncaughtExceptionHandler(
                this);
        t.start();
    }

    public class BoundingBox extends JPanel {

        public BoundingBox() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // viene richiamato ogni volta dal webCamPanel automaticamente
            Graphics2D g2d = (Graphics2D) g;
            Mat im = null;
            if (panel.getImage() != null) {
                List<Rect> rects = controller.getBoundingBoxes(panel.getImage());

                im = utilities.Utility.bufferedImageToMat(panel.getImage());
                
                //System.out.println("Larghezza " + panel.getImage().getWidth());
                //System.out.println("Altezza " + panel.getImage().getHeight());
                int imgWidth = panel.getImage().getWidth();
                int imgHeight = panel.getImage().getHeight();

                // Ottieni le dimensioni del pannello
                int panelWidth = panel.getWidth();
                int panelHeight = panel.getHeight();

                // Calcola i fattori di scala
                double scaleX = (double) panelWidth / (double) imgWidth;
                double scaleY = (double) panelHeight / (double) imgHeight;
               
                for (Rect rect : rects) {

                    int newX = (int) (rect.x * scaleX);
                    int newY = (int) (rect.y * scaleY);
                    int newWidth = (int) (rect.width * scaleX);
                    int newHeight = (int) (rect.height * scaleY);
                    g2d.setColor(new Color(255, 0, 0, 128)); // Rosso semi-trasparente
                    g2d.setStroke(new BasicStroke(5));      // Spessore bordo
                    g2d.drawRect(newX, newY, newWidth, newHeight);
                    /*
                    Imgproc.rectangle(
                            im, new Point(rect.x, rect.y),
                            new Point(rect.x + rect.width,
                                    rect.y + rect.height),
                            new Scalar(0, 0, 255),5);
                    System.out.println(rect);
                     */
                }

                //Imgcodecs.imwrite("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\dario_output.jpg", im);
            }
        }
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SwingUtilities.invokeLater(() -> {
            WebcamViewerExample webcamViewerExample = new WebcamViewerExample();
            JFrame frame = new JFrame("Webcam Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(webcamViewerExample, BorderLayout.CENTER);
            //perfetto con 640 e 480
            frame.setSize(800, 600); // Imposta dimensioni iniziali
            frame.setVisible(true);
            webcamViewerExample.run(); // Avvia il pannello webcam
        });
    }

    @Override
    public void webcamOpen(WebcamEvent we) {
        System.out.println("webcam open");
    }

    @Override
    public void webcamClosed(WebcamEvent we) {
        System.out.println("webcam closed");
    }

    @Override
    public void webcamDisposed(WebcamEvent we) {
        System.out.println("webcam disposed");
    }

    @Override
    public void webcamImageObtained(WebcamEvent we) {
        // do nothing
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        webcam.close();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("webcam viewer resumed");
        panel.resume();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("webcam viewer paused");
        panel.pause();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println(String.format("Exception in thread %s", t.getName()));
        e.printStackTrace();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() != webcam) {
            if (webcam != null) {

                panel.stop();

                remove(panel);

                webcam.removeWebcamListener(this);
                webcam.close();

                webcam = (Webcam) e.getItem();
                webcam.setViewSize(WebcamResolution.VGA.getSize());
                webcam.addWebcamListener(this);

                System.out.println("selected " + webcam.getName());

                panel = new WebcamPanel(webcam, false);
                panel.setFPSDisplayed(true);

                add(panel, BorderLayout.CENTER);
                // pack();

                Thread t = new Thread() {

                    @Override
                    public void run() {
                        panel.start();
                    }
                };
                t.setName("example-stoper");
                t.setDaemon(true);
                t.setUncaughtExceptionHandler(this);
                t.start();
            }
        }
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent event) {
        if (picker != null) {
            picker.addItem(event.getWebcam());
        }
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent event) {
        if (picker != null) {
            picker.removeItem(event.getWebcam());
        }
    }
}
