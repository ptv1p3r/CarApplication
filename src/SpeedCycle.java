import java.util.TimerTask;

/**
 * @author Pedro Roldan.
 * @version 0.0
 */
public class SpeedCycle extends TimerTask {
    
    private Car myCar;

    /**
     *
      * @param mycar
     */
    public SpeedCycle(Car myCar){
        super();
        
        this.myCar = myCar;
        
    }

    /**
     * Override do metodo run default do timer
     */
    @Override
            public void run() {
                // consumo de combustivel
                if( myCar.useFuel(myCar.getSpeed() * myCar.calcConsumptionRate()) ) {
                    System.out.println("Current fuel: " + myCar.getFuelLevel()+"\t speed " + myCar.getSpeed() + " Km/h");
                } else { // sem combustivel
                    System.out.println("Not enough fuel, ");
                    myCar.setState(Car.State.SHUTDOWN);
                    this.cancel();
                }
            }
}
