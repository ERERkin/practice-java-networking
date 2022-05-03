package kg.erkin.networking.socketTCP.Simple;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleSocketClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 11111)) {

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Test".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            byte[] responseBytes = new byte[1024];
            int responseStatus = inputStream.read(responseBytes);

            String response = new String(responseBytes);
//            StringBuilder answer = new StringBuilder();
//
//            for(int i = 0; i < response.length(); i++){
//                if(response.charAt(i) == ' ') break;
//                answer.append(response.charAt(i));
//            }

            System.out.println(response);
        }
    }
}
