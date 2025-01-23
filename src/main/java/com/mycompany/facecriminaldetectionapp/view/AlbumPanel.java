/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.view;

/**
 *
 * @author Dario
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author user
 */
public class AlbumPanel extends JPanel {

    private final PhotosPanel photosPanel = new PhotosPanel();
    private final OptionsPanel optionsPanel = new OptionsPanel();
    private final JLabel message = new JLabel();
    private final JPanel messagePanel = new JPanel();
    private final int capacity;
    //private static final Color backGroundColor = new Color(28, 46, 74);
    private static final Color backGroundColor = Color.BLACK;
    private final int photoWidth;
    private final int photoHeight;
    private final Color warningColor = new Color(255, 160, 0);
    private int hGap = 1;
    private int vGap = 1;

    private final Image backgroundImage;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("sfondooooo");
        g.drawImage(backgroundImage, 20, 20, null);

    }

    public void sethGap(int hGap) {
        this.hGap = hGap;
    }

    public void setvGap(int vGap) {
        this.vGap = vGap;
    }

    private List<CardPanel> cards;

    public void removeCards() {

        cards.clear();
        repaint();

    }

    public void removeCardById(int id) {

        cards = cards.stream().filter(c -> c.index != id).collect(Collectors.toList());
        /**
         * Se chiami repaint() su un pannello contenitore (come un JPanel che
         * contiene altri JPanel), tutti i pannelli figli verranno anch'essi
         * ridisegnati,
         */
        repaint();
    }

    public void clearMessage() {

        message.setText("");
    }

    public AlbumPanel(int photoWidth, int photoHeigth, int capacity) {
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeigth;
        this.capacity = capacity;

        backgroundImage = Toolkit.getDefaultToolkit().createImage("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\cyber-criminal.png");

        cards = new ArrayList(capacity);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 600));
        messagePanel.add(message);
        messagePanel.setBackground(backGroundColor);
        messagePanel.setPreferredSize(new Dimension(500, 30));
        add(messagePanel, BorderLayout.NORTH);
        add(photosPanel, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.SOUTH);
        setBackground(backGroundColor);
        message.setForeground(Color.WHITE);

    }

    /*
          @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println("Repaint AlbumPanel");
            photosPanel.repaint();
        }
     */
    public class PhotosPanel extends JPanel {

        private final FlowLayout flow = new FlowLayout();

        public PhotosPanel() {

            setBackground(backGroundColor);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // rimuove anche i parametri settati del layout
            removeAll();
            flow.setHgap(hGap);
            flow.setVgap(vGap);
            setLayout(flow);
            for (CardPanel card : cards) {
                add(card);
            }

            revalidate();

        }

    }

    public void addCard(CardPanel panel) {

        clearMessage();
        messagePanel.setBackground(Color.BLACK);
        if (cards.size() < capacity) {
            cards.add(panel);
            repaint();

        } else {

            message.setText("Hai superato il numero di foto consentite!");
            messagePanel.setBackground(warningColor);
        }
        photosPanel.add(panel);

    }

    public void addImage(Image image) {
        int maxId = 0;
        for (CardPanel card : cards) {
            if (card.index > maxId) {
                maxId = card.index;
            }
        }

        addCard(new CardPanel(image, maxId + 1));
    }

    public class OptionsPanel extends JPanel {

        //private JButton selectAllButton = new JButton("Select All");
        private final JButton removeAllButton = new JButton();
        //private JButton deselectAllButton = new JButton("Deselect All");

        private final JButton addPhotoButton = new JButton("Search");

        public OptionsPanel() {

            FlowLayout layout = new FlowLayout();
            setLayout(layout);
            removeAllButton.setBackground(Color.BLACK);
            setPreferredSize(new Dimension(100, 100));
            setBackground(Color.BLACK);

            // removeAllButton.setForeground(Color.WHITE);
            removeAllButton.setBorder(new LineBorder(Color.BLACK));

            removeAllButton.setIcon(new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\bin_resize.png"));

            /*
            selectAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (cards.size() <= 0) {
                        message.setText("Nessuna foto scattata!");
                        messagePanel.setBackground(warningColor);
                    } else {
                        for (CardPanel card : cards) {
                            card.box.setSelected(true);
                        }
                    }
                }

            });
             */
            removeAllButton.addActionListener((ActionEvent e) -> {
                if (cards.size() <= 0) {
                    message.setText("Nessuna foto scattata");
                    messagePanel.setBackground(warningColor);
                } else {
                    int input = JOptionPane.showConfirmDialog(null, "Sei sicuro?");
                    if (JOptionPane.YES_OPTION == input) {

                        clearMessage();
                        removeCards();
                    }
                }
            });

            /*
            deselectAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (cards.size() <= 0) {
                        message.setText("Nessuna foto scattata!");
                        messagePanel.setBackground(warningColor);

                    } else {
                        for (CardPanel card : cards) {
                            card.box.setSelected(false);
                        }
                    }
                }

            });

             */
            add(addPhotoButton);
            // add(selectAllButton);
            //add(deselectAllButton);
            add(removeAllButton);

            addPhotoButton.addActionListener((ActionEvent e) -> {
                String imagePath = "C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\Brad_Pitt.jpg";
                Image image = getImageByPath(imagePath);
                addImage(image);
                getSelectedImage();
            });

        }

    }

    public Image getSelectedImage() {
        for (CardPanel card : cards) {

            if (card.box.isSelected()) {
                return card.image;
            }
        }
        return null;
    }

    public class CardPanel extends JPanel {

        private final JButton photoButton = new JButton();
        private JCheckBox box = new JCheckBox();
        private final JButton deleteButton = new JButton();
        private final Image image;
        private final int index;

        public CardPanel(Image image, int index) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.image = image;
            this.index = index;
            ImageIcon icon = new ImageIcon(image);
            Image scaleImage = icon.getImage().getScaledInstance(photoWidth, photoHeight, Image.SCALE_AREA_AVERAGING);
            photoButton.setIcon(new ImageIcon(scaleImage));
            photoButton.setBackground(backGroundColor);
            photoButton.setForeground(backGroundColor);
            photoButton.setBorder(new LineBorder(backGroundColor));
            photoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteButton.setIcon(new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\images\\remove_resize.png"));

            box.setBackground(Color.BLACK);
            box.setText(String.valueOf(index));
            box.setForeground(Color.white);
            add(box);
            setBackground(backGroundColor);
            add(deleteButton);
            //FlowLayout flow = new FlowLayout();
            //flow.setHgap(30);
            JPanel helperPanel = new JPanel();
            helperPanel.add(box);

            helperPanel.add(deleteButton);
            helperPanel.setPreferredSize(new Dimension(photoWidth, 40));
            deleteButton.setBackground(Color.BLACK);
            deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //deleteButton.setForeground(Color.WHITE);
            helperPanel.setBackground(Color.BLACK);
            setBackground(backGroundColor);

            box.addActionListener((ActionEvent e) -> {
                for (CardPanel card : cards) {
                    if (card.box == box) {
                        card.box.setSelected(true);
                    } else {
                        card.box.setSelected(false);
                    }
                }
            });

            add(photoButton);
            add(helperPanel);
            deleteButton.addActionListener((ActionEvent e) -> {
                clearMessage();
                messagePanel.setBackground(backGroundColor);
                removeCardById(index);
            });
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
            //frame.setPreferredSize(new Dimension(500, 800));
            frame.setVisible(true);
            frame.setBackground(backGroundColor);
            AlbumPanel albumPanel = new AlbumPanel(150, 200, 6);
            albumPanel.sethGap(6);
            albumPanel.setvGap(6);

            frame.add(albumPanel);
            frame.pack();

            String imagePath = "C:\\Users\\Dario\\Documents\\NetBeansProjects\\FaceCriminalDetectionApp\\src\\main\\java\\com\\mycompany\\facecriminaldetectionapp\\Brad_Pitt.jpg";
            Image image1 = getImageByPath(imagePath);

            //albumPanel.repaint();
            System.out.println("Repaint");
        });
    }

}
