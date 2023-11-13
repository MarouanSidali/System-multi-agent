
import java.io.*;
import java.net.*;

public class Agfib {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int previousNumber = 0;
            int nextNumber = 1;
            int MAX = 20; // Sending 10 numbers

            for (int i = 0; i < MAX; i++) {
                out.writeBytes("fibo" + previousNumber + "\n");
                int sum = previousNumber + nextNumber;
                previousNumber = nextNumber;
                nextNumber = sum;
                Thread.sleep(1000);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
