package echoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echo = input.readLine();
                if(echo.equals("exit")) {
                    break;
                }

                try{
                    Thread.sleep(10000);
                } catch(InterruptedException e) {
                    e.getMessage();
                }
                output.println(echo);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }
}
