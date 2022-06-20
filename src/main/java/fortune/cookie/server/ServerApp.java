package fortune.cookie.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) {
        String serverPort = args[0];//12345;
        String cookieFilePath = args[1];///Users/jeremy/Desktop/practice/luckylucky/cookie_file.txt;

        try {
            System.out.printf("Cookie server started at %s", serverPort);

            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));
            Socket sock = server.accept();
            
            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            String requestFromClient = dis.readUTF();
            System.out.printf("Received request from client : %s", requestFromClient);

            if (requestFromClient.equals("get-cookie")) {
                System.out.printf("file -> %s\n",cookieFilePath);

                String randomCookie = App.getRandomCookie(cookieFilePath);
                System.out.println(randomCookie);
                dos.writeUTF("Cookie text " + randomCookie);
            } else {
                dos.writeUTF("Invaild command!");
            }
            
            is.close();
            os.close();

            sock.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
