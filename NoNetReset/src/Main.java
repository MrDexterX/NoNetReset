import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args){

        Delay d0 = new Delay(10000);

        CheckNet nn = new CheckNet("google.com", 80, 7000);

        Relay r1 = new Relay();

        Delay d1 = new Delay(3000);

        Logger l1 = new Logger();

        int waitCount = 0;

        int noConnCount = 0;

        int relayActiveCount = 0;

        d0.DelayTime();

        while(true){

            for(int i = 0; i < 4; i++){

                d1.DelayTime();

                if(!nn.CheckConnection()){

                    // Write log
                    l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : No connection! " + (noConnCount + 1) +  " - Checking again..." );

                    noConnCount++;
                }

                System.out.println(nn.CheckConnection() + " - " + (i + 1));

                if(noConnCount > 4 && relayActiveCount < 3) {

                    // Write log
                    l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : No connection! - Try to reset device " + (i + 1) + " !");

                    r1.ActivateRelay();

                    // Write log
                    l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : Power is back. Checking...");

                    relayActiveCount++;

                    if(nn.CheckConnection()){

                        // Write log
                        l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : Success!");

                        relayActiveCount = 0;

                    }else{

                        // Write log
                        l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : No luck.");

                    }

                    noConnCount = 0;
                }

                /*
                if(i == 3){
                    System.out.println("------------------------------------------");
                }
                */
            }

            if(relayActiveCount == 3){
                while(!nn.CheckConnection()){

                    //System.out.println("No connection! Waiting...");

                    // Write log
                    if(waitCount < 1){
                        l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : Reset wont help. Waiting for maintenance. Checking...");
                        waitCount++;
                    }

                    d1.DelayTime();
                }
                if (nn.CheckConnection()){

                    // Write log
                    l1.WriteLog(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) + " : Connection is back... Back to work mode!");

                    relayActiveCount = 0;
                    waitCount = 0;

                }
            }
        }
    }
}
