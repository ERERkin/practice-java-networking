package kg.erkin.networking.socketTCP.Object;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class SerializerClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 11111)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(new SerializablePerson("Test", 20));
            outputStream.writeObject(new ExternalizableUser("Test", "Password"));
            outputStream.flush();
        }
    }
}
