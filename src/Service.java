import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Service {


    private static List<Vehicle> vehicleList = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);


    public void addVeh1(VehType vehType, State state, String engine) {

        try {
            switch (vehType) {
                case CAR:

                    vehicleList.add(new Car(state, engine));
                    break;
                case TRUCK:

                    vehicleList.add(new Truck(state, engine));
                    break;
                case MOTORBIKE:

                    vehicleList.add(new Motorbike(state, engine));
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Choose correct values");
        }
    }


    public void removeVeh() {
        for (Vehicle c : vehicleList)
            vehicleList.remove(c);
    }


    public List<String> showVehicle() {
        List<String> showVehicle = new LinkedList<>();
        for (Vehicle c : vehicleList) {
            showVehicle.add(c.toString());
        }
        return showVehicle;
    }


    public void repairVeh() {
        for (int i = 0; i < vehicleList.size(); i++) {

            for (Vehicle c : vehicleList) {
                c.state = State.REPAIRED;
                vehicleList.set(i, c);
            }

        }

    }
}