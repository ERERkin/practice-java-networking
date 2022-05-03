package kg.erkin.networking.socketTCP.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketServerWithDataLayer {
    private static final Logger LOG = Logger.getLogger(SocketServerWithDataLayer.class.getName());

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(11111)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    serveClient(socket);
                }
            }
        }
    }

    private static void serveClient(Socket socket) throws IOException {
        LOG.info("Serving client " + socket.getInetAddress());

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        while (true) {
            String request = inputStream.readUTF();

            LOG.info("Request: " + request);
            outputStream.writeUTF("Hello " + request);
            outputStream.flush();
        }
    }
}
