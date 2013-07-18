package books2.server;

import java.io.Serializable;

public class Snake implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String name_action;
    
    public Snake() {
    }

    public Snake(String name) {
        int i = name.indexOf("**");
        this.name_action = name.substring(0, i);
        this.name = name.substring(i+2);
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setName_action(String name_action) {
        this.name_action = name_action;
    }

    public String getName_action() {
        return name_action;
    }

}
