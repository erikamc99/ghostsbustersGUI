package dev.celia.ghostbustersgui.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.plaf.ColorUIResource;
import dev.celia.ghostbustersgui.controller.UserController;
import dev.celia.ghostbustersgui.view.utils.ButtonUtils;

public class HomeScreen extends JFrame{
    private final UserController userController;
    public HomeScreen(UserController userController) {
        this.userController = userController;

        Font customFont = utils.loadCustomFont("/fonts/font.ttf");
        utils.setUIFont(customFont);
        
                JFrame frame = new JFrame ("Ghostbusters");
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setLayout(null);
                frame.setUndecorated(true);
        
                java.net.URL imageURL = HomeScreen.class.getClassLoader().getResource("images/home.png");
        
                ImageIcon background = new ImageIcon(imageURL);
                JLabel backgroundLabel = new JLabel(background);
                backgroundLabel.setBounds(0,0,800,600);
        
                JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>Bienvenido a la central<br>Cazafantasmas</div></html>", SwingConstants.CENTER);
                titleLabel.setBounds(10, 0, 800, 130);
                titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD,35f));
                titleLabel.setForeground(new ColorUIResource(234,0,0));
                titleLabel.setOpaque(true); 
                titleLabel.setBackground(new Color(0, 0, 0, 200));
        
                JButton goMenu = new JButton("<html><div style='text-align: center;'>Acceder al<br>contenedor de almacenamiento</div><html>");
                goMenu.setBounds(275, 250, 250, 100);
                ButtonUtils.applyHoverEffect(goMenu);
                goMenu.setFont(goMenu.getFont().deriveFont(15f));
        
                JProgressBar progressBar = new JProgressBar();
                progressBar.setBounds(250, 480, 300, 50);
                progressBar.setForeground(new javax.swing.plaf.ColorUIResource(204, 70, 50)); 
                progressBar.setBackground(new javax.swing.plaf.ColorUIResource(200, 200, 200)); 
                progressBar.setStringPainted(true);
                progressBar.setVisible(false);
        
                JLabel loadingLabel = new JLabel("Accediendo al contenedor de almacenamiento de fantasmas...", SwingConstants.CENTER);
                loadingLabel.setBounds(160, 430, 475, 40);
                loadingLabel.setVisible(false);
                loadingLabel.setFont(loadingLabel.getFont().deriveFont(Font.BOLD, 11f));
                loadingLabel.setForeground(new ColorUIResource(234,0,0));
                loadingLabel.setOpaque(true); 
                loadingLabel.setBackground(new Color(0, 0, 0, 230));
        
                goMenu.addActionListener(e -> {
                    goMenu.setEnabled(false); 
                    loadingLabel.setVisible(true);
                    progressBar.setVisible(true);

                    SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() throws InterruptedException {
                            for (int i = 0; i <= 100; i += 5) {
                                progressBar.setValue(i);
                                Thread.sleep(80);
                            }
                            return null;
                        }
        
                        @Override
                        protected void done() {
                            frame.dispose(); 
                            userController.openMenuView();
                        }
                    };
        
                    worker.execute();
                });
        
                frame.add(titleLabel);
                frame.add(goMenu);
                frame.add(progressBar);
                frame.add(loadingLabel);
                frame.add(backgroundLabel);
                frame.setVisible(true);
            }
}