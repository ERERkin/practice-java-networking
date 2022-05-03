package kg.erkin.networking.socketTCP.Simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class SimpleSocketServer {
    private static final Logger LOG = Logger.getLogger(SimpleSocketServer.class.getName());

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

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        while (true) {
            byte[] responseBytes = new byte[1024];
            int request = inputStream.read(responseBytes);

            if (request == -1) {
                break;
            }

            String response = new String(responseBytes);

            LOG.info("Request: " + request);
            outputStream.write(("Hello, " + response).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}
