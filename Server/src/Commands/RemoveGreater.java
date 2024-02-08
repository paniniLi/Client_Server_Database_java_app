package Commands;

import Main.Collection;
import MusicBand.MusicBand;
import Utils.DataBaseManager;

import static Main.FabricOfServer.INTERFACE_MESSAGE;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

public class RemoveGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 13;
    MusicBand musicBand;

    @Override
    public Message execute() {
        boolean flag = false;
        long[] idGreaterArray = Collection.getGreater(musicBand);
        try {
            for (Long id : idGreaterArray) {
                if (DataBaseManager.removeById(id, username)) {
                    Collection.removeById(id); flag = true;
                }
            }

            if (flag) return new Message("MusicBands were successfully removed.");
            else return new Message("Nothing was removed.");
        } catch (SQLException e) {
            return new Message("There are problems in the database.");
        }
    }
}
