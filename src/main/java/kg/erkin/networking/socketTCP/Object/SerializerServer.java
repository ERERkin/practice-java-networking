package kg.erkin.networking.socketTCP.Object;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializerServer {
    private static final Logger LOG = Logger.getLogger(SerializerServer.class.getName());

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(11111)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    serveClient(socket);
                }
            }
        }
    }

    private static void serveClient(Socket socket) throws Exception {
        LOG.info("Serving client " + socket.getInetAddress());

        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                Object object = inputStream.readObject();
                if (object instanceof SerializablePerson) {
                    SerializablePerson person = (SerializablePerson) object;
                    LOG.log(Level.INFO, "Received SerializablePerson: {0} {1}", new Object[]{
                            person.getName(), person.getAge()});
                } else if (object instanceof ExternalizableUser) {
                    ExternalizableUser user = (ExternalizableUser) object;
                    LOG.log(Level.INFO, "Received ExternalizableUser: {0} {1}", new Object[]{
                            user.getLogin(), user.getPassword()
                    });
                } else {
                    LOG.warning("Received unexpected " + object.getClass());
                }
            }
        } catch (EOFException e) {
            LOG.info("Connection closed");
        }
    }
}
