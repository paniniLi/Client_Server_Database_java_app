package Commands;

import Main.Collection;
import MusicBand.MusicBand;
import MusicBand.MusicBandBuilder;
import Utils.DataBaseManager;

import javax.xml.crypto.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2;
    MusicBand musicBand;
    @Override
    public Message execute() {
        musicBand.setCreationDate(LocalDateTime.now());
        try {
            DataBaseManager.insertIntoTheCollection(musicBand);
            musicBand.setId(DataBaseManager.getId(musicBand, username));
            musicBand.setCreationDate(LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("There are problems in the database.");
        }
        Collection.add(musicBand);
        return new Message("MusicBand №"+musicBand.getId()+" successfully added.");
    }
}
