// Holds runtime race state for one car.
// Works with the provided Car.java (no dependencies).

public class CarAgent {
    public String id;
    public Car car;
    public Position pos;
    public int velRow = 1;
    public int velCol = 1;
    public boolean finished = false;

    public CarAgent(String id) {
        this.id = id;
        try {
            this.car = new Car(id);
        } catch (Exception e) {
            this.car = new Car();
        }
    }
}
