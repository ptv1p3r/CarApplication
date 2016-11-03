import java.awt.Color;
import static java.lang.System.out;
import java.util.Timer;

/**
 * @author Pedro Roldan
 * @version 0.0
 */
public class Car {

    public enum FuelType {PETROL, DIESEL, GAS, ELECTRIC, ALCOHOOL};
    public enum State {SHUTDOWN, READY, ENGINEWORKING, MOVING };
            
    final static float MIN_TANK_CAPACITY = 1.0f; // minimium is 1 letter
    final static float MAX_TANK_CAPACITY = 120.0f; /// maximum should not be more than 120 letter

    private long id;
    private Color color;
    private String brand;
    private String model;
    private FuelType fuelType;
    private short enginePower, numberOfDoors, numberOfSeats, numberOfCylinders;
    private String plateNumber;
    private State state;
    private float tankCapacity, maximumSpeed, speed, fuelLevel, rateOfConsumptionPerCylinder;
    
    private Timer moving = new Timer();


    @Override
    /**
     * Override da funcao toString do objecto
     * @return strDashBoard
     */
    public String toString(){
        String strDashBoard = "Speed: " + this.speed + "\t Fuel: " + this.fuelLevel + "\t State: " + this.state + "\t Consuption Rate: ";
        return strDashBoard;
    }


    /**
     * construtor de classe por omissao
     */
    public Car(){
    }

    /**
     * construtor de classe
     * @param id
     * @param tankCapacity
     * @param color
     * @param fuelType
     * @param maximumSpeed
     * @param numberOfCylinders
     * @param rate
     */
    public Car(long id, float tankCapacity, Color color, FuelType fuelType, float maximumSpeed, short numberOfCylinders, float rate) {
        
        // valida id
        if(id < 0){
           System.err.println("Can not create a car object with a negative id!");
           return;
        }

        // valida capacidade do tanque de combustivel
        if(tankCapacity < MIN_TANK_CAPACITY || tankCapacity > MAX_TANK_CAPACITY){ // minimum is 1 letter
            System.err.println("Tank Capacity should be between " + MIN_TANK_CAPACITY + " and " + MAX_TANK_CAPACITY);
           return;
        }

        // valida velocidade maxima
        if (maximumSpeed <= 0) {
            System.err.println("Maximum speed is a positive number");
            return;
        }

        // valida numero de cilindros
        if ( numberOfCylinders <=0 || numberOfCylinders > 12) {
            System.err.println("Number of cylinders: Between 0 and 12");
            return;
        }

        // valida rate
        if (!(rate > 0 && rate <=1)) {
            System.err.println("rate: Between 0 and 1");
            return;
        }
        
        // assigna
        this.tankCapacity = tankCapacity;
        this.id = id;
        this.color = color;
        this.state = State.SHUTDOWN;
        this.fuelType = fuelType;
        this.maximumSpeed = maximumSpeed;
        this.numberOfCylinders = numberOfCylinders;
        this.rateOfConsumptionPerCylinder = rate;

    }

    /**
     * retorna estado
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * define estado
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * retorna nivel de combustivel
     * @return fuelLevel
     */
    public float getFuelLevel() {
        return fuelLevel;
    }

    /**
     * define velocidade
     * @param speed
     * @return boolean
     */
    public boolean setSpeed(float speed){

        // valida estados
        if (this.state == State.SHUTDOWN || this.state == State.READY) {
            System.err.println("The car is not currently working");
            return false;
        }

        // valida velocidade maxima
        if (speed > maximumSpeed) {
            System.err.println("Cannot surpass maximum speed");
            return false;
        }

        // valida velocidade minima
        if (speed < 0) {
            System.err.println("Speed has to be not negative");
            return false;
        }

        //everything went fine, speed up and use fuel

        SpeedCycle cycle = new SpeedCycle(this);
        this.speed = speed;
        this.moving.schedule(cycle, 1, (long)(1E3/speed));

        return true;

    }

    /**
     * retorna velocidade
     * @return speed
     */
    float getSpeed() {
        return speed;
    }

    /**
     * Usa combustivel
     * @param fuel
     * @return boolean
     */
    public boolean useFuel(float fuel){
        if(fuelLevel> 0){
            this.fuelLevel = this.fuelLevel - fuel;
            if(this.fuelLevel<0){
                this.fuelLevel = 0.0f;
            }
            return true;
        }
        
        return false;
    }

    /**
     * Adiciona combustivel ao tanque
     * @param fuel
     * @return boolean
     */
    public boolean addFuel(float fuel) {

        // valida quantidade de combustivel
        if(fuel <= 0){
            System.err.println("Added fuel should be > 0");
            return false;
        }

        // valida quantidade de combustivel no deposito
        if(fuel + this.fuelLevel > this.tankCapacity){
            System.err.println("You can not exceed the tank capacity");
            return false;
        }
        
        // adiciona novo combustivel
        this.fuelLevel = fuelLevel + fuel;
        return true;
        
    }

    /**
     * inicia o carro
     * @return boolean
     */
    public boolean start(){
            
        if(this.state == State.SHUTDOWN){
            System.out.print("Ready...");
            this.state = state.ENGINEWORKING;
            System.out.println("Working");
            return true;
        }
        
        if(this.state == State.READY){
            this.state = State.ENGINEWORKING;
            System.out.println("Working");
            return true;
        }
        
        System.err.println("The car is already working!");
        return false;
    }

    /**
     *
     * @return boolean
     */
    public boolean stop(){
        if (this.state == State.ENGINEWORKING || this.state == State.READY) {
            out.println("shutting down");
            return true;
        }
        System.err.println("The car is already off");
        return false;
    }

    /**
     * retorna id
     * @return id
     */
    public long getId() {
        return id;
    }

    /***
     * define o id
     * @param id
     */
    public void setId(long id) {
       if( id < 0 ){
           System.err.println("Car id can not negative");
           return;
       }
        this.id = id;
    }

    /**
     * Calcula o ConsumptionRate
     * @return <code>rateOfConsumptionPerCylinder*numberOfCylinders</code>
     */
    public float calcConsumptionRate() {
        return rateOfConsumptionPerCylinder * (float) numberOfCylinders;
    }

    /**
     * retorna o ConsumptionRate
     * @return rateOfConsumptionPerCylinder
     */
    public float getConsumptionRate() {
        return rateOfConsumptionPerCylinder;
    }

    /**
     * retorna o NumberOfCylinders
     * @return numberOfCylinders
     */
    public short getNumberOfCylinders() {
        return numberOfCylinders;
    }

    /**
     * define o NumberOfCylinders
     * @param numberOfCylinders
     */
    public void setNumberOfCylinders(short numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    /**
     *
     * @return boolean
     */
    public boolean breakCar(){
        this.speed = 0;

        this.moving.purge();

        this.state =  state.ENGINEWORKING;

        return true;
    }

}
