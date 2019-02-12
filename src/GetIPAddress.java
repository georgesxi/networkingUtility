import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class GetIPAddress {

    public static String getLocalIP() throws Exception {
        String localIP = "";
        try {
            //Get Local IP
            InetAddress localhost = InetAddress.getLocalHost();
            localIP = localhost.getHostAddress().trim();
            System.out.println("System IP Address : " + localIP);
        } catch (Exception e) {
            localIP = "Cannot Execute Properly";

        }
        return localIP;
    }

    public static String getExternalIP() throws Exception {

        //Get public IP
        String systemIPAddress = "";

        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemIPAddress = sc.readLine().trim();
        } catch (Exception e) {
            systemIPAddress = "Cannot Execute Properly";

        }
        System.out.println("Public IP Address: " + systemIPAddress + "\n");
        return systemIPAddress;
    }
}