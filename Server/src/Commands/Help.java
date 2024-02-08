package Commands;

import java.io.Serial;
import java.io.Serializable;
import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class Help extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 9;

    @Override
    public Message execute() {
        return new Message(INTERFACE_MESSAGE+"help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : выводит в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "updateId {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "removeById {id} : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "executeScript {file_name} : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "addIfMin {element} : добавить новый элемент в коллекцию, если его значение меньше значения наименьшего элемента этой коллекции\n" +
                "removeGreater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "removeLower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "groupCountingByGenre : сгруппировать элементы коллекции по значению поля genre, вывести количество элементов в каждой группе\n" +
                "countGreaterThanNumberOfParticipants {numberOfParticipants} : вывести количество элементов, значение поля numberOfParticipants которых больше заданного\n" +
                "printFieldAscendingGenre : вывести значения поля genre всех элементов в порядке возрастания"+ INTERFACE_MESSAGE);
    }
}
