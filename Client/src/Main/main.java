package Main;

import Commands.CommandBuilder;
import Commands.Message;
import Exceprions.InvalidParams;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static final String ERROR_MESSAGE = "\u001B[31m";
    public static final String INTERFACE_MESSAGE = "\u001B[0m";

    public static final Scanner scan = new Scanner(System.in);

    public static void main (String[] args) {
        try {
            Client.connectToServer();
            System.out.println(INTERFACE_MESSAGE+"The connection is established."+INTERFACE_MESSAGE);
            Client.enter();

            CommandBuilder commandBuilder = new CommandBuilder();
            while (true) {
                commandBuilder.setCommand();
                Client.sendObject(commandBuilder.build());
                Message message = Client.readMessage();
                if (message.message.equals("Exit")) System.exit(0);
                else System.out.println(message.message);
            }
        } catch (IOException | ClassNotFoundException | InvalidParams e) {
            try {
                System.out.println(main.ERROR_MESSAGE+"Waiting for connection..."+main.ERROR_MESSAGE);
                Thread.sleep(2000);
            } catch (InterruptedException ignore) {}
            main(new String[1]);
        }
    }
}
