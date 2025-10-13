import java.io.*;
import java.net.*;

public class Server {


    public static void main (String[] args) throws IOException {


        ServerSocket middleman = new ServerSocket(6767);
        System.out.println("Server running... waiting for client connection.");

        Socket socket = middleman.accept(); // rerturns a new socket object that represents the client
        System.out.println("CLient connected");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        String message = input.readLine(); // reads a line from the client

        System.out.println("Client writes: " + message); // prints read message inputed by cluent to server

        output.println("I have read your message: "+ message);

        socket.close();
        middleman.close();
    }








 }