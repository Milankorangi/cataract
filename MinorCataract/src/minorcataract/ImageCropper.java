
package minorcataract;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class ImageCropper {

    int x, y;
    JFrame frame, f1, f2, f3;
    JLabel lab;
    int max = 0;

    public ImageCropper(String path) {
        frame = new JFrame();
        lab = new JLabel();
        lab.setBounds(10, 10, 40, 40);
        frame.add(lab);
        frame.setSize(100, 100);
        frame.setVisible(true);
        frame.setLayout(null);

        try {
            BufferedImage originalImage = ImageIO.read(new File(path));

            System.out.println("Original Image Dimension: " + originalImage.getWidth() + "x" + originalImage.getHeight());

            x = (originalImage.getWidth()) / 2;
            y = (originalImage.getHeight()) / 2;

            BufferedImage SubImage = originalImage.getSubimage(x - 10, y - 10, 20, 20);

            System.out.println("Cropped Image Dimension: " + SubImage.getWidth() + "x" + SubImage.getHeight());

            int width = SubImage.getWidth();
            int height = SubImage.getHeight();

            
            int gray = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int p = SubImage.getRGB(x, y);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    int avg = (r + g + b) / 3;
                    gray = SubImage.getRGB(x, y) & 0xFF;
                    p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                    SubImage.setRGB(x, y, p);
                    System.out.println("The value is " + gray);

                    if (gray > max) {
                        max = gray;
                    }
                    

                }
            }
            
         if(max < 120){
                  JFrame f1 = new JFrame();
                    JOptionPane.showMessageDialog(f1,"You have healthy eyes. Do eye exercises daily to maintain good vison  ","Test Result ",JOptionPane.OK_OPTION); 
            }
            else if(max > 120 && max < 160){
              JFrame f2 = new JFrame();
                    JOptionPane.showMessageDialog(f2,"Inital symptom's have started to devlope . Seek medical attention.  ","Test Result",JOptionPane.WARNING_MESSAGE); 
        }
            else{
              JFrame f3 = new JFrame();
                    JOptionPane.showMessageDialog(f3," Critical stage of Cataract. Seek immediate medical attention!!!!!!","Test Result",JOptionPane.WARNING_MESSAGE); 
        }
            
            
            
            System.out.println("The maximum value is" + max);
            
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\dell\\Desktop\\eyee.jpg");            
            frame.setIconImage(icon); 
            lab.setIcon(new ImageIcon(SubImage));

        
        } catch (IOException e) {
        }
    
    }
}
