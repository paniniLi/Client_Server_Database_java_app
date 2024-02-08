package Commands;

import Exceprions.InvalidParams;
import Utils.TxtManager;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.main.ERROR_MESSAGE;

public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6;
    private ArrayList<Command> commands = new ArrayList<>();

    @Override
    public void setParamFromFile(ArrayList<String> paramString) throws InvalidParams {
        if (paramString.size()==0) throw new InvalidParams(ERROR_MESSAGE+"Wrong call of the сountGreaterThanNumberOfParticipants command."+ ERROR_MESSAGE);
        setParam(paramString.get(0).split(" "));
    }

    @Override
    public void setParam(String[] paramString) throws InvalidParams {
        commands.clear();
        int PARAM_COUNT = 1;
        if (paramString.length != PARAM_COUNT+1) throw new InvalidParams(ERROR_MESSAGE+"Invalid calling of the executeScript command. Type help to find out which commands are available to you."+ ERROR_MESSAGE);

        File file = new File(paramString[1]);
        try {
            ArrayList<ArrayList<String>> fileArray = TxtManager.readLines(file);
            for (ArrayList<String> commandAndParam : fileArray) {
                Command command = getCommand(commandAndParam);
                commands.add(command);
            }
        } catch (IOException|InvalidParams e) {
            System.out.println(e.getMessage());
        }
    }

    private Command getCommand(ArrayList<String> commandAndParam) throws InvalidParams, IOException {
        Command command = mapGetCommand(commandAndParam.get(0).split(" "));
            command.setParamFromFile(commandAndParam);
            return command;
    }

    private Command mapGetCommand(String[] queryLine) throws InvalidParams {
        if (queryLine.length<1 || !CommandBuilder.MapOfCommands.containsKey(queryLine[0])) throw new InvalidParams(ERROR_MESSAGE+"No such command founded. File was not fully read."+ ERROR_MESSAGE);
        try {
            return CommandBuilder.MapOfCommands.get(queryLine[0]);
        } catch (ClassCastException|NullPointerException e) {
            throw new InvalidParams(ERROR_MESSAGE+"No such command founded. File was not fully read."+ ERROR_MESSAGE);
        }
    }
}
