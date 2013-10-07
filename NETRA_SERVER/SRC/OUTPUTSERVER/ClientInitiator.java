/*
 * Author Ahmed Abdelhalim - 2009
 * Email: englemo@hotmail.com
 * 
 * Modified by:- Rajiv Singh - 2012
 * r4jiv007@gmail.com
 * Please do not remove the above lines
 */
package outputserver;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the entry class of the server
 */
public class ClientInitiator {
    //Main server frame
    private JFrame frame = new JFrame();
    //JDesktopPane represents the main container that will contain all
    //connected clients' screens
    private JDesktopPane desktop = new JDesktopPane();
    public Socket client;
    public static void main(String args){
       // String ip = JOptionPane.showInputDialog("Please enter server IP");
       // String port = JOptionPane.showInputDialog("Please enter Server port");
        new ClientInitiator().initialize(args,2220);
    }

    public void initialize(String ip,int port){

        try {
          //  ServerSocket sc = new ServerSocket(port);
            //Show Server GUI
            drawGUI();
           client = new Socket(ip,port);
            new ServerHandler(client,desktop);
            //Listen to server port and accept clients connections
          /*  while(true){
                Socket client = sc.accept();
                System.out.println("New client Connected to the server");
                //Per each client create a ClientHandler
                new ClientHandler(client,desktop);
            }*/
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Draws the main server GUI
     */
    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //Show the frame in a maximized state
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
