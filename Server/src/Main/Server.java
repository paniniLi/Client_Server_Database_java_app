package Main;

import Commands.Message;
import Utils.DeserializeManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private static SocketChannel clientSocket;

    private static ServerSocketChannel server;

    public static void connectToClient() throws IOException {
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(6789));
        clientSocket = server.accept();
        clientSocket.configureBlocking(false);
    }

    public static void disconnectToClient() throws IOException {
        server.close();
    }

    public static byte[] readObject() throws IOException{
        ByteBuffer len = ByteBuffer.allocate(6);
        while (len.hasRemaining()) {
            clientSocket.read(len);
        }
        len.flip();
        int length = len.getInt();
        ByteBuffer get = ByteBuffer.allocate(length);
        while (get.hasRemaining()) {
            clientSocket.read(get);
        }
        return get.array();
    }

    public static void sendMessage(Message message) throws  IOException{
        byte[] send = DeserializeManager.serialize(message);
        ByteBuffer len = ByteBuffer.allocate(6).putInt(send.length);
        len.flip();
        clientSocket.write(len);
        clientSocket.write(ByteBuffer.wrap(send));
    }

}
