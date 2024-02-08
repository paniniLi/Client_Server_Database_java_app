package Commands;

import Main.Collection;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class GroupCountingByGenre extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 8;

    @Override
    public Message execute() {
        StringBuilder stringBuilder = new StringBuilder();
        Collection.groupCountingByGenre().forEach((key, value) -> stringBuilder.append(INTERFACE_MESSAGE+key + ": " + value+ INTERFACE_MESSAGE+"\n"));

        if (stringBuilder.isEmpty()) return new Message("Collection is empty");
        return new Message(stringBuilder.deleteCharAt(stringBuilder.length()-1).toString());
    }
}
