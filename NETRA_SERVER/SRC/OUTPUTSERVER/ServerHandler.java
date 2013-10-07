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
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;


class ServerHandler extends Thread {

    private JDesktopPane desktop = null;
    private Socket cSocket = null;
    private JInternalFrame interFrame = new JInternalFrame("Client Screen",true, true, true);
    private JPanel cPanel = new JPanel();
    
    public ServerHandler(Socket cSocket, JDesktopPane desktop) {
        this.cSocket = cSocket;
        this.desktop = desktop;
        start();
    }

    /*
     * Draw GUI per each connected client
     */
    public void drawGUI(){
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
        interFrame.setSize(100,100);
        desktop.add(interFrame);
        try {
            //Initially show the internal frame maximized
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        //this allows to handle KeyListener events
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

    public void run(){

        //used to represent client screen size
        Rectangle serverScreenDim = null;
        //Used to read screenshots and client screen dimension
        ObjectInputStream ois = null;
        //start drawing GUI
        drawGUI();

        try{
            //Read server screen dimension
            ois = new ObjectInputStream(cSocket.getInputStream());
            serverScreenDim =(Rectangle) ois.readObject();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        //Start recieveing screenshots
        new ServerScreenReciever(ois,cPanel);
        //Start sending events to the client
        new ServerCommandsSender(cSocket,cPanel,serverScreenDim);
    }

}
