public class Car extends Vehicle {

    public Car(State state,String engine){
        this.vehType = VehType.CAR;
        this.state = state;


        this.engine = engine;
    }

    @Override
    public String toString() {
        return
                " VehType=" + vehType +
                ", state=" + state +
                ", engine='" + engine + '\'' +
                '}';
    }
}
