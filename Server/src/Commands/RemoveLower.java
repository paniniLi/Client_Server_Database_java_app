package Commands;

import Exceprions.InvalidParams;
import Main.Collection;
import MusicBand.MusicBand;
import MusicBand.MusicBandBuilder;
import Utils.DataBaseManager;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import static Main.FabricOfServer.ERROR_MESSAGE;
import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class RemoveLower extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 14;
    MusicBand musicBand;

    @Override
    public Message execute() {
        boolean flag = false;
        long[] idLowerArray = Collection.getLower(musicBand);
        try {
            for (Long id : idLowerArray) {
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
