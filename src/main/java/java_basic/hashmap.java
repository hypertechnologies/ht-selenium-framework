package java_basic;

import java.util.HashMap;

public class hashmap {

    public static void main(String[] args) {

        HashMap<String, String> carColor = new HashMap<String, String>();
        carColor.put("Volvo", "Black");
        carColor.put("Toyota", "Red");
        carColor.put("BMW", "White");

        // print color of Toyota
        String color = carColor.get("Toyota");
        System.out.println("Color of Toyota is: " + color);

        // size of the carColor
        int size = carColor.size();
        System.out.println("size of carColor is: " + size);

        // print all carColor
        for(String i : carColor.keySet()){
            String key = i;
            String value = carColor.get(key);
            System.out.println("Key is: " + key + " value is: " + value);
        }

        // remove BMW
        carColor.remove("BMW");
        System.out.println("size after removing BMW is: " + carColor.size());

        // clear everything
        carColor.clear();
        System.out.println("size after clearing everything is: " + carColor.size());



    }

}
