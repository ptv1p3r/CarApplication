import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pedro Roldan.
 * @version 0.0
 */

public class CarApplication {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Car pedroCar = new Car(2345553, 120, new Color(0,0,0), Car.FuelType.PETROL, 220, (short)4, 0.2f);
        pedroCar.addFuel(120.0f);
        pedroCar.start();
        pedroCar.setSpeed(5);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

        pedroCar.setSpeed(30);

        pedroCar.stop();

    }
    
}
