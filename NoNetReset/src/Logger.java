import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public void WriteLog(String input) {
        try {
            FileWriter writer = new FileWriter("/home/pi/Desktop/log.txt", true);
            writer.write(input);
            writer.write("\r\n");   // write new line
            writer.write("---------------------------------------------------");
            writer.write("\r\n");   // write new line
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
