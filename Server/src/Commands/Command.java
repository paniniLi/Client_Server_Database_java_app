package Commands;

import java.io.Serial;
import java.io.Serializable;

abstract public class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    public String username;
    public String password;
    abstract public Message execute();
}
