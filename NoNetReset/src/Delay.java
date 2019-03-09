
public class Delay {

    private int time;

    public Delay(int time){
        this.time = time;
    }

    public void DelayTime(){
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

}
