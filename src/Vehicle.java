import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    protected VehType vehType;
    protected State state;


    protected String  engine;




    @Override
    public String toString() {
        return
                "vehType=" + vehType +
                ", state=" + state +
                ", engine='" + engine + '\'' +
                '}';
    }
}
