package fortune.cookie.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {

    public static void main(String[] args) {
        System.out.println("Cookie Client");
        String[] arr = args[0].split(":");
        
        try {
            Socket socket = new Socket(arr[0], Integer.parseInt(arr[1]));
            InputStream in = socket.getInputStream();
            DataInputStream din = new DataInputStream(in);

            OutputStream out = socket.getOutputStream();
            DataOutputStream dout = new DataOutputStream(out);
            
            Console cons = System.console();
            String input = cons.readLine("Send command to server > ");
            dout.writeUTF(input);
            dout.close();

            String sendBack = din.readUTF();
            if (sendBack.contains ("cookie-text")) {
                System.out.println(sendBack);
                String[] cookieValue = sendBack.split(" ");
                System.out.printf("Cookie from server >>%s\n", cookieValue[1]);
            }

            in.close();
            out.close();

            socket.close();
        } catch (NumberFormatException e) { 
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
