package Commands;

import Exceprions.InvalidParams;
import Main.Collection;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class CountGreaterThanNumberOfParticipants extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 5;
    Integer numberOfParticipants;

    @Override
    public Message execute() {
        return new Message(INTERFACE_MESSAGE+Collection.countGreaterThanNumberOfParticipants(numberOfParticipants)+ INTERFACE_MESSAGE);
    }
}
