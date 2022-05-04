package kg.erkin.networking.socketTCP.Object;

import java.io.Serializable;

public class SerializablePerson implements Serializable {

    private static final long serialVersionUID = 11111;

    private String name;
    private Integer age;

    public SerializablePerson(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
