package java_basic;

import java.util.ArrayList;

public class arraylist {
    public static void main(String[] args) {

        // Simple array
        String[] arr = {"apple", "banana", "orange"};

        // String Arraylist
        ArrayList<String> cars = new ArrayList<String>();
        System.out.println(cars);

        // Add item to arraylist
        cars.add("Toyota");
        System.out.println("added Toyota: " + cars);

        cars.add("BMW");
        cars.add("Mazda");
        System.out.println("added BMW & Mazda: " + cars);

        // Trying to add a integer value
//        cars.add(200); // It will not store int

        // find the size of the arraylist
        int count = cars.size();
        System.out.println("Total element is: " + count);

        // Remove item from arraylist
        cars.remove(1);
        System.out.println("removed BMW: " + cars);

        // Update an item
        cars.set(1, "Volvo");
        System.out.println("Updated Mazda to Volvo: " + cars);

        // access a value from arraylist
        String result = cars.get(0);
        System.out.println("Should get Toyota: " + result);

        // Clear all items from arraylist
        System.out.println("Before clearing all items: " + cars);
        cars.clear();
        System.out.println("After clearing all items: " + cars);



        // Arraylist without any data type
        ArrayList arrList = new ArrayList();
        arrList.add("this is a string");
        arrList.add(200);
        arrList.add(300.25);

        System.out.println("new arraylist" + arrList);



    }


    }
