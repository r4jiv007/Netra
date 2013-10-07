/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outputserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajiv Singh
 * r4jiv007@gmail.com
 */
public class MultiClient extends Thread {
        Socket socket=null;
        
        public MultiClient(Socket socket) {
	super("MultiClient");
	this.socket = socket;
    }
    
   
	 public void run() {
      
            try {
            /*    //ServerOP.op.append("connected with "+socket.getInetAddress().toString());
//                TableLog.rcdTable.
                String clientSentence;
                //Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                   new BufferedReader(new InputStreamReader(socket.getInputStream()));
               // DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                while ((clientSentence = inFromClient.readLine())!=null){
               // ServerOP.op.append(clientSentence+"\n");  
                //}
               
        } */
            
            ObjectInputStream nsIN = new ObjectInputStream(socket.getInputStream()); 
                String submit[]=new String[5];
                System.arraycopy((String[]) nsIN.readObject(),0, submit, 0, 5);
                 submit[2]=socket.getInetAddress().toString().substring(1);
               //  for(int i=0;i<5;i++)
                 //    System.out.println(submit[i]);
                TableLog.model.insertRow(0,submit);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
               // Logger.getLogger(ServerOP.class.getName()).log(Level.SEVERE, null, ex);
            }
   
    
    
    
}
}
