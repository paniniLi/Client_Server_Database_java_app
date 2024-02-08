package Server;

import Commands.Message;
import Utils.DeserializeManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.RecursiveAction;

public class SenderOfResponses extends RecursiveAction {
    private final Message message;
    private final SocketChannel client;

    public SenderOfResponses(Message message, SocketChannel client) {
        this.message = message;
        this.client = client;
    }

    @Override
    public void compute() {
        try {
            this.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Message message) throws  IOException{
        byte[] send = DeserializeManager.serialize(message);
        ByteBuffer len = ByteBuffer.allocate(6).putInt(send.length);
        len.flip();
        client.write(len);
        client.write(ByteBuffer.wrap(send));
    }
}
