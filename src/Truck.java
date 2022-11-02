public class Truck extends Vehicle {

    public Truck(State state,String engine){
        this.vehType = VehType.TRUCK;
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
