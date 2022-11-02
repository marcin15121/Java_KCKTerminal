public class Motorbike extends Vehicle {

    public Motorbike(State state,String engine){
        this.vehType = VehType.MOTORBIKE;
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
