package Commands;

import Main.Collection;
import MusicBand.MusicBand;
import Utils.DataBaseManager;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class AddIfMin extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3;
    MusicBand musicBand;

    @Override
    public Message execute() {
        MusicBand minMusicBand = Collection.getMinMusicBand();
        if (minMusicBand!= null && musicBand.compareTo(minMusicBand)<0) {
            try {
                DataBaseManager.insertIntoTheCollection(musicBand);
                musicBand.setId(DataBaseManager.getId(musicBand, username));
                musicBand.setCreationDate(LocalDateTime.now());
            } catch (Exception e) {
                return new Message("There are problems in the database.");
            }
            Collection.add(musicBand);
            return new Message(INTERFACE_MESSAGE+"MusicBand was successfully added"+ INTERFACE_MESSAGE);
        } else return new Message(INTERFACE_MESSAGE+"MusicBand was not added"+ INTERFACE_MESSAGE);
    }
}
