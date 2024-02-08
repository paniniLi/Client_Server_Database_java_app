package Commands;

import Main.Collection;
import Utils.DataBaseManager;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 4;

    @Override
    public Message execute() {
        try {
            DataBaseManager.clear();
        } catch (SQLException e) {
            return new Message("There are problems in the database.");
        }
        Collection.clear();
        return new Message("Collection is clear.");
    }
}
