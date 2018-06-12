/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minorcataract;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author dell
 */
public class MinorCataract extends JFrame {
    Connection conn = null;
    Statement stmt = null;
    
    
    JButton button, buttontest, button1;
    public JLabel label, labelout1, labelout2, labelout3, labelout4, labelresult, l1, l2, l3, l4;
         
    JTextField t1, t2, t3, t4;
    

    JProgressBar jb;
    int i = 0;
   public String query, s;

    MinorCataract() {
        super("Get image for processing");
        conn = DatabaseConnect.ConnectDb();
        
        

        button = new JButton("Browse");
        button.setBounds(80, 300, 100, 40);
        
        button1 = new JButton("Save");
        button1.setBounds(600, 300, 100, 40);

        buttontest = new JButton("Test Cataract");
        buttontest.setBounds(300, 300, 200, 40);

        label = new JLabel();
        label.setBounds(10, 10, 400, 250);

        labelresult = new JLabel("TEST RESULT:-");
        labelresult.setHorizontalAlignment(JLabel.CENTER);
        labelresult.setBackground(Color.GRAY);
        labelresult.setBounds(10, 400, 670, 20);

        labelout1 = new JLabel();
        //labelout1.setHorizontalAlignment(JLabel.CENTER);
        labelout1.setBackground(Color.GRAY);
        labelout1.setBounds(270, 430, 670, 20);

        labelout2 = new JLabel();
        //labelout2.setHorizontalAlignment(JLabel.CENTER);
        labelout2.setBackground(Color.GRAY);
        labelout2.setBounds(270, 450, 670, 20);
        
        labelout3 = new JLabel();
        //labelout3.setHorizontalAlignment(JLabel.CENTER);
        labelout3.setBackground(Color.GRAY);
        labelout3.setBounds(270, 470, 670, 20);

        labelout4 = new JLabel();
        //labelout4.setHorizontalAlignment(JLabel.CENTER);
        labelout4.setBackground(Color.GRAY);
        labelout4.setBounds(270, 490, 670, 20);

        
        jb = new JProgressBar(0, 100);
        jb.setBounds(25, 350, 735, 30);
        
          l1=new JLabel("Name:-");          
          l1.setBounds(440,20, 100,30);      
          l2=new JLabel("Address:-");       
          l2.setBounds(440,80, 100,30); 
          l3=new JLabel("Age:-");          
          l3.setBounds(440,140, 100,30);      
          l4=new JLabel("Phone NO:-");       
          l4.setBounds(440,200, 100,30); 
        
         t1=new JTextField();    
         t1.setBounds(510,20, 240,30);                   
         t2=new JTextField();                   
         t2.setBounds(510,80, 240,30);   
         t3=new JTextField();    
         t3.setBounds(510,140, 240,30);                   
         t4=new JTextField();                   
         t4.setBounds(510,200, 240,30);  
         
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\dell\\Desktop\\eyee.jpg");            
            setIconImage(icon); 


        jb.setValue(0);
        jb.setStringPainted(true);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
       
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(jb);
        add(buttontest);
        add(button);
        add(button1);
        
        add(label);
        add(labelout1);
        add(labelout2);
        add(labelout3);
        add(labelout4);
        add(labelresult);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    label.setIcon(ResizeImage(path));
                    s = path;

                } else if (result == JFileChooser.CANCEL_OPTION) {
                     JFrame f = new JFrame(); 
                     JOptionPane.showMessageDialog(f,"No fie selected.","Alert",JOptionPane.WARNING_MESSAGE);       

                }
            }
        });

        buttontest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           
            if (s != null) {
                    new ImageCropper(s);
                    setVisible(true);
                    iterate();
                    
                    String value1 = t1.getText();
                    String value2 = t2.getText();
                    String value3 = t3.getText();
                    String value4 = t4.getText();
                     
                     labelout1.setText("Name:- "+ value1);
                     labelout2.setText("Address:- "+ value2);
                     labelout3.setText("Age:- "+ value3);
                     labelout4.setText("Phone No:- "+ value4);
                     
                     
                    
                  
                } 
            else  {
                    JFrame f = new JFrame(); 
                    JOptionPane.showMessageDialog(f,"No fie selected.","Alert",JOptionPane.WARNING_MESSAGE);       
            }
          }

        });

         button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(s != null){
             try {
              String value1 = t1.getText();
              String value2 = t2.getText();
              String value3 = t3.getText();
              String value4 = t4.getText();
               
              InputStream is = new FileInputStream(new File(s));
               ImageIcon MyImage = new ImageIcon(s);
               Image img = MyImage.getImage();
               
          query = "INSERT INTO data (Name, Address, Age, Phone_No, Image) VALUES ('"+value1+"', '"+value2+"', '"+value3+"', '"+value4+"','img')";  
                
                    stmt = conn.createStatement();
                    stmt.executeUpdate(query);
      
             }catch (SQLException ex) {
                    Logger.getLogger(MinorCataract.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MinorCataract.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                else
                {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f,"No fie selected.","Alert",JOptionPane.WARNING_MESSAGE);  
                }
                
            }

        });
        
         setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(780, 600);
        setVisible(true);

    }

    public void iterate() {
        while (i < 101) {
            jb.setValue(i);
            i = i + 1;
            try {
                Thread.sleep(150);
            } catch (Exception e) {

            }

        }
    }
   

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    
    public static void main(String[] args) {
       
        new MinorCataract();

    }

}
