package basicjava;

import java.util.HashSet;
import java.util.LinkedList;

public class hashset {
    public static void main(String[] args) {

        // linkedlist and arraylist can have multiple of the same value
        LinkedList<String> cars = new LinkedList<String>();
        cars.add("BMW");
        cars.add("Honda");
        cars.add("Tesla");
        cars.add("BMW");
        cars.add("Honda");
        cars.add("Honda");

        System.out.println(cars);
        System.out.println("Size of cars: " + cars.size());


        //use HashSet to remove duplicates and just keep the unique values
        HashSet<String> cars2 = new HashSet<String>();
        cars2.add("BMW");
        cars2.add("Honda");
        cars2.add("Tesla");
        cars2.add("BMW");
        cars2.add("Honda");
        cars2.add("Honda");
        System.out.println("Size of cars2: " + cars2.size());
        System.out.println(cars2);

        // check if cars2 contains a value
        boolean isTeslaExist = cars2.contains("Tesla");
        System.out.println(isTeslaExist);
        boolean isToyotaExist = cars2.contains("Toyota");
        System.out.println(isToyotaExist);

        //remove
        cars2.remove("Tesla");
        System.out.println("cars2 after removing Tesla: " + cars2);

        // Clear
        cars2.clear();
        System.out.println("cars2 after clearing everything: " + cars2);








    }


    }
