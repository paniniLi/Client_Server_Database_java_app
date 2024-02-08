package Commands;
import Exceprions.InvalidParams;
import Main.Collection;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.FabricOfServer.ERROR_MESSAGE;
import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class Info extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 10;

    @Override
    public Message execute() {
        return new Message(INTERFACE_MESSAGE+Collection.getInfoCollectionStructure()+ INTERFACE_MESSAGE);
    }
}
