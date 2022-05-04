package kg.erkin.networking.socketTCP.Object;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Base64;

public class ExternalizableUser implements Externalizable {

    private static final long serialVersionUID = 11111;

    private String login;
    private String password;

    public ExternalizableUser() {
    }

    public ExternalizableUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getLogin());
        out.writeObject(encryptString(getPassword()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setLogin((String) in.readObject());
        setPassword(decryptString((String) in.readObject()));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String encryptString(String data) {
        String encryptedData = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(encryptedData);
        return encryptedData;
    }

    private String decryptString(String data) {
        String decrypted = new String(Base64.getDecoder().decode(data));
        System.out.println(decrypted);
        return decrypted;
    }
}
