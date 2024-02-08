package Commands;

import java.io.Serial;
import java.io.Serializable;

public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 7;

    @Override
    public Message execute() {
        return new Message("Exit");
    }
}
