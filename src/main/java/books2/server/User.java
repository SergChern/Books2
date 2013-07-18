package books2.server;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8266525488057072269L;
    private String username;
    private String password;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
