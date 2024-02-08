package Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReaderOfQueries implements Runnable{
    private final ExecutorService executorFixed = Executors.newFixedThreadPool(10);
    private final SocketChannel client;

    public ReaderOfQueries(SocketChannel client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (client.isOpen()) {
                byte[] commandBytes = this.readObject();
                executorFixed.execute(new RequestHandler(commandBytes, client));
            }
            executorFixed.shutdown();
        } catch (IOException e) {
            System.out.println("Client is disconnected.");
        }
    }

    private byte[] readObject() throws IOException{
        ByteBuffer len = ByteBuffer.allocate(6);
        while (len.hasRemaining()) {
            client.read(len);
        }
        len.flip();
        int length = len.getInt();
        ByteBuffer get = ByteBuffer.allocate(length);
        while (get.hasRemaining()) {
            client.read(get);
        }
        return get.array();
    }
}
