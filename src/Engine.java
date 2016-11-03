import java.util.Timer;

/**
 * @author Pedro Roldan
 * @version 0.0
 * @since 11/3/16
 */
public class Engine extends Timer {
    private SpeedCycle speedCycle;

    public SpeedCycle getSpeedCycle() {
        return speedCycle;
    }

    public void setSpeedCycle(SpeedCycle speedCycle) {
        this.speedCycle = speedCycle;
    }

    public void schedule(long delay, long period) {
        super.schedule(this.speedCycle, delay, period); //To change body of generated methods, choose Tools | Templates.
    }
}
