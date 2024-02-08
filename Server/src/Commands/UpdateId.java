package Commands;

import Main.Collection;
import MusicBand.MusicBand;
import Utils.DataBaseManager;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static Main.FabricOfServer.ERROR_MESSAGE;
import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class UpdateId extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17;
    Long id;
    MusicBand musicBand;

    @Override
    public Message execute() {
        musicBand.setId(id);
        musicBand.setCreationDate(LocalDateTime.now());
        try {
            if (DataBaseManager.updateById(musicBand, username, id)) {
                Collection.removeById(id);
                Collection.add(musicBand);
                return new Message(INTERFACE_MESSAGE+"MusicBand №"+musicBand.getId()+" was successfully updated"+ INTERFACE_MESSAGE);
            } else return new Message(ERROR_MESSAGE+"No such id in collection"+ ERROR_MESSAGE);
        } catch (Exception e) {
            return new Message("There are problems in the database.");
        }
    }
}
