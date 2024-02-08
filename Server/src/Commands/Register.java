package Commands;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

import static Utils.DataBaseManager.insertIntoUsers;
import static Utils.DataBaseManager.isExistUsername;

public class Register extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 19;

    @Override
    public Message execute() {
        try {
            if (isExistUsername(username)) return new Message("Such username already exists.");
            else {
                insertIntoUsers(username, password);
                return new Message("Success authorization.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Message("There are problems in the database.");
        }
    }
}
