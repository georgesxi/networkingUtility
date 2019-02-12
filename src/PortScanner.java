import javax.swing.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScanner {

    public static String PortScanner(String ip, int port, int timeout) throws Exception {

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            String result = "Connection successful on " + ip + " and port " + port + " is open!";
            socket.close();
            return result;
        } catch (Exception ex) {
            String fail = "Port is probably closed, the request timed-out or error occurred!";
            return fail;
        }

    }
}
