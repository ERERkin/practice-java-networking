package kg.erkin.networking.socketTCP.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClientWithDataLayer {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 11111)) {

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF("Test");
            outputStream.flush();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String response = inputStream.readUTF();

            System.out.println(response);
        }
    }
}
