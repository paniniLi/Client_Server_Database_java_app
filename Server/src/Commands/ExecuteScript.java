package Commands;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6;
    private ArrayList<Command> commands = new ArrayList<>();

    @Override
    public Message execute() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command: commands) {
            stringBuilder.append(command.execute().message+"\n");
        }

        if (stringBuilder.isEmpty()) return new Message("No commands for executing");
        return new Message(stringBuilder.deleteCharAt(stringBuilder.length()-1).toString());
    }
}
