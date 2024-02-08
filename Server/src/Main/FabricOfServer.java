package Main;

import Server.ReaderOfQueries;
import Utils.DataBaseManager;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FabricOfServer {

    public static final String ERROR_MESSAGE = "\u001B[31m";
    public static final String INTERFACE_MESSAGE = "\u001B[0m";
    private final static ExecutorService executorFixed = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {

        try (ServerSocketChannel server = ServerSocketChannel.open().bind(new InetSocketAddress(6789))) {
            DataBaseManager.connectToDataBase();
            Collection.initialize();
            DataBaseManager.fillTheMemory();
            while (server.isOpen()) {
                SocketChannel client = server.accept();
                client.configureBlocking(false);
                System.out.println("Create thread for client");
                executorFixed.execute(new ReaderOfQueries(client));
            }
            executorFixed.shutdown();
        } catch (IOException | SQLException | ParseException e) {
            e.printStackTrace();
            System.out.println("Server is closed.");
        }
    }
}
