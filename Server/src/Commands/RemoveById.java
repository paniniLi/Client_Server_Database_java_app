package Commands;

import Main.Collection;
import Utils.DataBaseManager;

import java.io.Serial;
import java.io.Serializable;

import static Main.FabricOfServer.ERROR_MESSAGE;
import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class RemoveById extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 12;
    Long id;

    @Override
    public Message execute() {
        try {
            if (DataBaseManager.removeById(id, username)) {
                Collection.removeById(id);
                return new Message(INTERFACE_MESSAGE+"MusicBand №"+id+" was successfully removed"+ INTERFACE_MESSAGE);
            } else return new Message(ERROR_MESSAGE+"No such id in collection"+ ERROR_MESSAGE);
        } catch (Exception e) {
            return new Message("There are problems in the database.");
        }
    }
}
