package Server;

import Commands.Command;
import Commands.Message;
import Commands.Register;
import Utils.DeserializeManager;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;

import static Utils.DataBaseManager.isExistUser;

public class RequestHandler implements Runnable {
    private final SocketChannel client;
    private final byte[] commandBytes;
    private final ForkJoinPool executorFork = new ForkJoinPool();
    public Message message;

    public RequestHandler(byte [] commandBytes, SocketChannel client) {
        this.commandBytes = commandBytes;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Command command = (Command) DeserializeManager.deserialize(commandBytes);
            if (command.getClass().equals(Register.class) || isExistUser(command.username, command.password)) {
                message = command.execute();
                System.out.println("Operation is completed by "+command.username);
            } else {
                message = new Message("No such user in the system.");
                System.out.println("Unknown user is connected.");
            }
            executorFork.invoke(new SenderOfResponses(message, client));
            executorFork.shutdown();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
