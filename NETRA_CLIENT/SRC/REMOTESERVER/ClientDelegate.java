/*
 * Author Ahmed Abdelhalim - 2009
 * Email: englemo@hotmail.com
 * 
 * modified by:- Rajiv Singh - 2012
 * r4jiv007@gmail.com
 * Please do not remove the above lines
 */
package remoteserver;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 * Used to recieve client commands then execute them at the client side
 */
class ClientDelegate extends Thread {

    Socket socket = null;
    Robot robot = null;
    boolean continueLoop = true;

    public ClientDelegate(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
        start(); //Start the thread and hence calling run method
    }

    public void run(){
        Scanner scanner = null;
        try {
            //prepare Scanner object
            System.out.println("Preparing InputStream");
            scanner = new Scanner(socket.getInputStream());

            while(continueLoop){
                //recieve commands and respond accordingly
                System.out.println("Waiting for command");
                int command = scanner.nextInt();
                System.out.println("New command: " + command);
                switch(command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                    break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                    break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                    break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                    break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
