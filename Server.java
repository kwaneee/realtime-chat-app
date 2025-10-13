import java.io.*;
import java.net.*;

public class Server {


    public static void main (String[] args) throws IOException {
        ServerSocket middleman = new ServerSocket(6767);
        System.out.println("Server running... waiting for client connection.");

        Socket socket = middleman.accept(); //rerturns a new socket object that represents the client
        System.out.println("CLient connected");


        //Input and output streams
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // keyboard input


          // Thread for reading messages from client
          new Thread(() -> {
            try {
                String messageFromClient;
                while ((messageFromClient = input.readLine()) != null) {
                    System.out.println("Client: " + messageFromClient);
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        }).start();

        //  thread for sending messages to client
        String messageToClient;
        while ((messageToClient = keyboard.readLine()) != null) {
            output.println(messageToClient);
            if (messageToClient.equalsIgnoreCase("exit")) break;
        }


        // String message = input.readLine(); //reads a line from the client

        // System.out.println("Client writes: " + message); // prints read message inputed by cluent to server

        // output.println("I have read your message: "+ message);

        socket.close();
        middleman.close();
        System.out.println("Server closed");
    }








 }