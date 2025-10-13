import java.io.*;
import java.net.*;

public class Client {


    public static void main (String[] args) throws IOException {

        try (Socket socket = new Socket("localhost", 6767);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // keyboard input

            System.out.println("Connected to server! Type messages below:");

            

            // Thread to read messages from server
            new Thread(() -> {
                try {
                    String messageFromServer;
                    while ((messageFromServer = input.readLine()) != null) {
                        System.out.println("Server: " + messageFromServer);
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                }
            }).start();

            // Main thread to send messages
            String messageToServer;
            while ((messageToServer = keyboard.readLine()) != null) {
                output.println(messageToServer);
                if (messageToServer.equalsIgnoreCase("exit")) break;
            }

            System.out.println("Client closed.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
            
            
            
        //     output.println("Hi Server");

        //     String message = input.readLine(); // reads a line from the server
            
        //     System.out.println("Server writes: " + message); // prints read message inputted by server to client

        // } catch (IOException e) {
        //     System.err.println("Error connecting to the server: " + e.getMessage());
        // }
    }
}