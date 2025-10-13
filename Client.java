import java.io.*;
import java.net.*;

public class Client {


    public static void main (String[] args) throws IOException {


        try (Socket socket = new Socket("localhost", 6767);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            output.println("Hi Server");

            String message = input.readLine(); // reads a line from the server
            
            System.out.println("Server writes: " + message); // prints read message inputted by server to client

        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        }
    }
}