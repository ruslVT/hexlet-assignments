package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection implements Connection {
    private String ipAddress;
    private int port;
    private Connection connection;
    private List<String> dataBuffer = new ArrayList<>();

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void setState(Connection newConnection) {
        this.connection = newConnection;
    }

    @Override
    public String getCurrentState() {
        return this.connection.getCurrentState();
    }

    @Override
    public void write(String data) {
        if (getCurrentState().equals("disconnected")) {
            System.out.println("Error, connection is disconnected!!!");
        } else {
            dataBuffer.add(data);
        }
    }

    @Override
    public void connect() {
        this.connection.connect();
    }

    @Override
    public void disconnect() {
        this.connection.disconnect();
    }
}
// END
