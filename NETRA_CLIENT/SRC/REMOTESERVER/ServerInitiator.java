
/*
 * Author Ahmed Abdelhalim - 2009
 * Email: englemo@hotmail.com
 * 
 * Modified by:- Rajiv Singh - 2012
 * r4jiv007@gmail.com
 * Please do not remove the above lines
 */

package remoteserver;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * This class is responsible for connecting to the server
 * and starting ScreenSpyer and ServerDelegate classes
 */
public class ServerInitiator extends Thread {

     public static ServerSocket clsocket = null;
    
    
    public   void run(){
        try {
          
      
         //  String ip = JOptionPane.showInputDialog("Please enter server IP");
          // String port = JOptionPane.showInputDialog("Please enter server port");
                   clsocket= new ServerSocket(2220);
           while (true){
               try {
                  Socket socket=clsocket.accept();
                   new ServerInitiator().initialize( socket);
               } catch (IOException ex) {
                   System.out.println(ex.getMessage());
               }
       
           }
        } catch (IOException ex) {
            Logger.getLogger(ServerInitiator.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void initialize(Socket socket){

        Robot robot = null; //Used to capture the screen
        Rectangle rectangle = null; //Used to represent screen dimensions

        try {
            //System.out.println("Connecting to server ..........");
            
            
           // System.out.println("Connection Established.");

            //Get default screen device
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();

            //Get screen dimensions
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            //Prepare Robot object
            robot = new Robot(gDev);

            //draw client gui
            //drawGUI();
            //ScreenSpyer sends screenshots of the client screen
            new ScreenSpyer(socket,robot,rectangle);
            //ServerDelegate recieves server commands and execute them
            new ClientDelegate(socket,robot);
        } catch (Exception ex) {
            ex.printStackTrace();
       
       }
    }

    private void drawGUI() {
        final JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("Terminate");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        }
      );
      frame.setVisible(true);
    }
}
