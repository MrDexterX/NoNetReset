import com.pi4j.io.gpio.*;
import com.pi4j.util.CommandArgumentParser;

public class Relay {

    Delay d1 = new Delay(30000);

    Delay d2 = new Delay(240000);

    final GpioController gpio = GpioFactory.getInstance();

    Pin pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_00);

    GpioPinDigitalOutput output = gpio.provisionDigitalOutputPin(pin, "My Output", PinState.HIGH);

    public void ActivateRelay(){

        System.out.println("Relay active! - 30 sec with no power supply.");
        output.low();
        d1.DelayTime();

        System.out.println("Relay deactivated! - 4 min before start checking connection.");
        output.high();
        d2.DelayTime();
    }

}
