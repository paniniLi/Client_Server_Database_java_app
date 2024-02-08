package Main;

import Commands.*;
import Exceprions.InvalidParams;
import Utils.DeserializeManager;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {
    private static Socket clientSocket;
    private static InputStream in;
    private static OutputStream out;

    public static String username;

    public static String password;

    public static void connectToServer() throws IOException{
        clientSocket = new Socket("localhost", 6789);
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
    }

    public static void sendObject(Object command) {
        try {
            byte[] byteArrayObject = DeserializeManager.serialize(command);
            byte[] len = ByteBuffer.allocate(6).putInt(byteArrayObject.length).array();
            out.write(len);
            out.write(byteArrayObject);
        } catch (IOException e) {
            System.out.println(main.ERROR_MESSAGE+"Connection lost."+main.ERROR_MESSAGE);
        }
    }
    public static Message readMessage() throws IOException, ClassNotFoundException{
        byte[] arrLengthServer = new byte[6];
        in.read(arrLengthServer);

        byte[] serializeObject = new byte[ByteBuffer.wrap(arrLengthServer).getInt()];

        in.read(serializeObject);
        return (Message) DeserializeManager.deserialize(serializeObject);
    }

    public static void enter() throws IOException, ClassNotFoundException, InvalidParams {
        Message message;
        CommandBuilder commandBuilder = new CommandBuilder();
        do {
            if (!commandBuilder.setEntryCommand()) System.exit(0);
            Client.sendObject(commandBuilder.build());
            message = Client.readMessage();
            System.out.println(message.message);
            if (message.message.equals("Exit")) System.exit(0);
        } while (!message.message.equals("Success authorization."));
    }

}
