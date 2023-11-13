import java.io.*;
import java.net.*;

public class Agsqr {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int number = 0;
            int MAX = 0;

            while (MAX < 20) { // Adjust the number of iterations as needed
                out.writeBytes("sqr" + (number * number) + "\n");

                number++;
                MAX++;

                Thread.sleep(1000);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
