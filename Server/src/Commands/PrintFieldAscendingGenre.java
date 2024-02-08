package Commands;

import Exceprions.InvalidParams;
import Main.Collection;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.FabricOfServer.ERROR_MESSAGE;
import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class PrintFieldAscendingGenre extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 11;

    @Override
    public Message execute() {
        return new Message(INTERFACE_MESSAGE+Collection.fieldAscendingGenre()+ INTERFACE_MESSAGE);
    }
}
