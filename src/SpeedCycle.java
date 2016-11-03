import java.util.TimerTask;

/**
 * @author Pedro Roldan.
 * @version 0.0
 */
public class SpeedCycle extends TimerTask {
    private Car car;

    /**
     *
     * @param car
     */
    public SpeedCycle(Car car){
        super();

        this.car = car;

    }

    @Override
    public void run() {

        if(car.useFuel(car.getSpeed() * car.calcConsumptionRate())){
            System.out.println(car);
        }else{
            System.err.println("No enough fuel, car is stoping...");
            car.setState(Car.State.SHUTDOWN);
            this.cancel();

        }
    }

}
