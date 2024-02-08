package Commands;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

import static Utils.DataBaseManager.isExistUser;

public class Authorization extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 18;

    @Override
    public Message execute() {
        try {
            if (isExistUser(username, password)) return new Message("Success authorization.");
            else return new Message("No such user in system.");
        } catch (SQLException e) {
            return new Message("There are problems in the database.");
        }
    }
}
