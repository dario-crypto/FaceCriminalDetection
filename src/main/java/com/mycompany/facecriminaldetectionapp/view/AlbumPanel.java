/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author user
 */
public class AlbumPanel extends JPanel {

    private List<Image> images = new ArrayList<>();
    private int hGap;
    private int vGap;
    private int nRows;
    private int nCols;
    private final int width;
    private final int height;
    private int xOffset = 0;
    private int yOffset = 0;
    private static final Color backGroundColor = new Color(28, 46, 74);
    private final GridLayout grid = new GridLayout();
    private List<Integer> selectedImageIndices = new ArrayList<>();

    public void addImage(Image image) {

        images.add(image);

        repaint();

    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public void setHGap(int hGap) {
        this.hGap = hGap;
    }

    public void setVGap(int vGap) {
        this.vGap = vGap;
    }

    public int getnRows() {
        return nRows;
    }

    public void setnRows(int nRows) {
        this.nRows = nRows;
    }

    public int getnCols() {
        return nCols;
    }

    public void setnCols(int nCols) {
        this.nCols = nCols;
    }

    public AlbumPanel(int width, int height) {
        this.width = width;
        this.height = height;

        setLayout(grid);
        setBackground(backGroundColor);
        //setPreferredSize(new Dimension(200, 800));
        setVisible(true);
    }

    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int xTempOffset = 0, yTempOffset = 0;
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
                try {
                    Image image = images.get(j * nCols + i);
                    System.out.println("DRAW");

                    g2d.drawImage(image, i * vGap + i * height, j * hGap + j * width, width, height, new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });

                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(ex);
                }
            }
        }

    }
     */
    public List<Image> getSelectedImages() {
        List<Image> imgs = new ArrayList<>();
        for (Integer index : selectedImageIndices) {
            imgs.add(images.get(index));

        }
        return imgs;
    }

    @Override
    protected void paintComponent(Graphics g) {

        grid.setRows(images.size());

        grid.setColumns(2);
        removeAll();
        for (int i = 0; i < images.size(); i++) {
            JButton button = new JButton();
            Image image = images.get(i);
            ImageIcon icon = new ImageIcon(image);
            Image scaleImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(scaleImage));
            button.setPreferredSize(new Dimension(50, 50));
            button.setBackground(backGroundColor);
            button.setBorder(new LineBorder(backGroundColor));
            JCheckBox box = new JCheckBox(String.valueOf(i));
            box.setForeground(Color.white);

            box.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ciaoo");

                    int index = Integer.parseInt(box.getText());
                    System.out.println(index);
                    if (box.isSelected()) {
                        System.out.println("Selezionato: " + index);
                        selectedImageIndices.add(index);
                    } else {
                        System.out.println("Deselezionato: " + index);
                        selectedImageIndices = selectedImageIndices.stream().filter(i -> i != index).collect(Collectors.toList());
                    }
                    System.out.println(selectedImageIndices);
                }

            });
            box.setBackground(backGroundColor);

            add(button);
            add(box);

        }

    }

    public static Image getImageByPath(String path) {
        Image awtImage = null;
        try {
            // Legge l'immagine come BufferedImage
            BufferedImage bufferedImage = ImageIO.read(new File(path));

            // Converte BufferedImage in Image
            awtImage = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());

            // Stampa informazioni sull'immagine
            System.out.println("Immagine caricata con successo!");
            System.out.println("Dimensioni: " + awtImage.getWidth(null) + "x" + awtImage.getHeight(null));
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'immagine: " + e.getMessage());
        }

        return awtImage;

    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(200,700));
            frame.setVisible(true);
            frame.setBackground(backGroundColor);
            AlbumPanel albumPanel = new AlbumPanel(80, 80);
            albumPanel.setHGap(10);
            albumPanel.setVGap(10);
            albumPanel.setnRows(2);
            albumPanel.setnCols(3);
            albumPanel.setxOffset(0);
            albumPanel.setyOffset(0);
            frame.add(albumPanel);
            frame.pack();

            String imagePath = "C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\model\\Brad_Pitt.jpg";
            Image image1 = getImageByPath(imagePath);

            albumPanel.addImage(image1);
            albumPanel.repaint();
            albumPanel.addImage(image1);

            albumPanel.addImage(image1);
            albumPanel.addImage(image1);
            albumPanel.repaint();
            albumPanel.addImage(image1);

            albumPanel.repaint();
            System.out.println("Repaint");
        });
    }
}
