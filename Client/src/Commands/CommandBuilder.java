package Commands;

import Exceprions.InvalidParams;
import Main.Client;

import java.util.HashMap;
import java.util.Map;

import static Main.main.ERROR_MESSAGE;
import static Main.main.scan;

public class CommandBuilder {
    Command command;
    public static Map<String, Command> MapOfCommands = new HashMap<>();
    static {
        MapOfCommands.put("add", new Add());
        MapOfCommands.put("addIfMin", new AddIfMin());
        MapOfCommands.put("clear", new Clear());
        MapOfCommands.put("countGreaterThanNumberOfParticipants", new CountGreaterThanNumberOfParticipants());
        MapOfCommands.put("executeScript", new ExecuteScript());
        MapOfCommands.put("exit", new Exit());
        MapOfCommands.put("groupCountingByGenre", new GroupCountingByGenre());
        MapOfCommands.put("help", new Help());
        MapOfCommands.put("info", new Info());
        MapOfCommands.put("printFieldAscendingGenre", new PrintFieldAscendingGenre());
        MapOfCommands.put("removeById", new RemoveById());
        MapOfCommands.put("removeGreater", new RemoveGreater());
        MapOfCommands.put("removeLower", new RemoveLower());
        MapOfCommands.put("show", new Show());
        MapOfCommands.put("updateId", new UpdateId());
        MapOfCommands.put("authorization", new Authorization());
        MapOfCommands.put("register", new Register());
    }

    public CommandBuilder setCommand(String[] queryLine) throws InvalidParams {
        if (queryLine.length<1 || !MapOfCommands.containsKey(queryLine[0])) throw new InvalidParams(ERROR_MESSAGE+"No such command founded. Type help to find out which commands are available to you."+ ERROR_MESSAGE);
        try {
            this.command = MapOfCommands.get(queryLine[0]);
            return this;
        } catch (ClassCastException|NullPointerException e) {
            throw new InvalidParams(ERROR_MESSAGE+"No such command founded. Type help to find out which commands are available to you."+ ERROR_MESSAGE);
        }
    }

    public void setCommand() throws InvalidParams {
        try {
            String[] queryLine = scan.nextLine().split(" ");
            Command command = mapGetCommand(queryLine);
            command.setParam(queryLine);
            this.command = command;
        } catch (InvalidParams e) {
            System.out.println(e.getMessage());
            setCommand();
        }
    }

    public boolean setEntryCommand() throws InvalidParams {
        System.out.print("Do you want to register? (0 - yes, 1 - no, i'm already register, exit - close app)  ");
        switch (scan.nextLine()) {
            case "0" -> this.command = new Register();
            case "1" -> this.command = new Authorization();
            case "exit" -> {
                return false;
            }
            default -> {
                System.out.println("Invalid data format. Try it again...");
                return setEntryCommand();
            }
        }
        this.command.setParam(null); return true;
    }

    public Command build() throws IllegalStateException {
        command.username = Client.username;
        command.password = Client.password;
        if (command == null)
            throw new IllegalStateException(ERROR_MESSAGE + "No such command founded. Type help to find out which commands are available to you." + ERROR_MESSAGE);
        return command;
    }

    private Command mapGetCommand(String[] queryLine) throws InvalidParams {
        if (queryLine.length<1 || !MapOfCommands.containsKey(queryLine[0])) throw new InvalidParams(ERROR_MESSAGE+"No such command founded. Type help to find out which commands are available to you."+ ERROR_MESSAGE);
        try {
            return MapOfCommands.get(queryLine[0]);
        } catch (ClassCastException|NullPointerException e) {
            throw new InvalidParams(ERROR_MESSAGE+"No such command founded. Type help to find out which commands are available to you."+ ERROR_MESSAGE);
        }
    }

}
