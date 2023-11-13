import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agclass {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);

            System.out.println("Waiting for AgFibo or AgSqr to connect...");

            Socket fiboSocket = null;
            BufferedReader fiboIn = null;
            Socket sqrSocket = null;
            BufferedReader sqrIn = null;

            List<Integer> fiboList = new ArrayList<>();
            List<Integer> sqrList = new ArrayList<>();
            boolean fiboFinished = false;
            boolean sqrFinished = false;

            while (!(fiboFinished && sqrFinished)) {
                if (fiboSocket == null || sqrSocket == null) {
                    Socket incomingSocket = serverSocket.accept();
                    BufferedReader incomingIn = new BufferedReader(new InputStreamReader(incomingSocket.getInputStream()));
                    String data = incomingIn.readLine();

                    if (data.startsWith("fibo")) {
                        if (fiboSocket == null) {
                            fiboSocket = incomingSocket;
                            fiboIn = incomingIn;
                            System.out.println("AgFibo connected.");
                        }
                    } else if (data.startsWith("sqr")) {
                        if (sqrSocket == null) {
                            sqrSocket = incomingSocket;
                            sqrIn = incomingIn;
                            System.out.println("AgSqr connected.");
                        }
                    }
                }

                if (fiboSocket != null) {
                    String fiboInput = fiboIn.readLine();
                    if (fiboInput != null && fiboInput.startsWith("fibo")) {
                        int fiboNumber = Integer.parseInt(fiboInput.substring(4));
                        fiboList.add(fiboNumber);
                    } else {
                        fiboFinished = true;
                    }
                }

                if (sqrSocket != null) {
                    String sqrInput = sqrIn.readLine();
                    if (sqrInput != null && sqrInput.startsWith("sqr")) {
                        int sqrNumber = Integer.parseInt(sqrInput.substring(3));
                        sqrList.add(sqrNumber);
                    } else {
                        sqrFinished = true;
                    }
                }

                System.out.println("Squares = " + sqrList);
                System.out.println("Fibonacci = " + fiboList);

                List<Integer> sortedList = new ArrayList<>();
                sortedList.addAll(fiboList);
                sortedList.addAll(sqrList);
                Collections.sort(sortedList);

                System.out.println("Sorted = " + sortedList);
                System.out.println();

                Thread.sleep(1000); // Adjust the delay as needed
            }

            if (fiboSocket != null) {
                fiboSocket.close();
            }
            if (sqrSocket != null) {
                sqrSocket.close();
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
