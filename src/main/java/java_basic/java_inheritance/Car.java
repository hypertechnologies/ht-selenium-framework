package java_basic.java_inheritance;

public class Car extends vehicle{
    String model = "Camry";

    public static void main(String[] args) {

        //regular object
//        vehicle myVehicle = new vehicle();


        Car myCar = new Car();
        myCar.wheels();
        System.out.println(myCar.model);

    }

}
