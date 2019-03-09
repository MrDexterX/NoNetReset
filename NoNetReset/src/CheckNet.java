import java.net.InetSocketAddress;
import java.net.Socket;


public class CheckNet {

    private String host;
    private int port;
    private int timeOutInMilliSec;

    public CheckNet(String host, int port, int timeOutInMilliSec){
        this.host = host;
        this.port = port;
        this.timeOutInMilliSec = timeOutInMilliSec;
    }

    public boolean CheckConnection(){
        try{
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeOutInMilliSec);
            //System.out.println("Internet is Available");
            return true;
        }
        catch(Exception ex){
            //System.out.println("No Connectivity" + ex.getMessage());
            return false;
        }
    }
}
