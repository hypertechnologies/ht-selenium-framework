package basicjava;

import java.util.ArrayList;
import java.util.LinkedList;

public class linkedlist {
    public static void main(String[] args) {
        // String Arraylist
//        ArrayList<String> cars = new ArrayList<String>();

        LinkedList<String> cars = new LinkedList<String>();

        cars.add("Volvo");
        cars.add("Toyota");
        cars.add("Mazda");

        System.out.println(cars);

        // Check size
        int size = cars.size();
        System.out.println("size is: " + size);

        // Remove
        cars.remove(1);
        System.out.println(cars);

        // Update
        cars.set(0, "Ford");
        System.out.println(cars);

        // Get a specific element
        String mazda = cars.get(1);
        System.out.println("Should get mazda: " + mazda);

        // Clear
//        cars.clear();
        System.out.println(cars);

        // Another linkedlist
        LinkedList<String> cars2 = new LinkedList<String>();
        cars2.add("BMW");
        cars2.add("Honda");
        cars2.add("Tesla");

        // Adding cars2 elements into cars
        cars.addAll(cars2);
        System.out.println("cars should have elements from cars2: " + cars);

        // Add an element to the beginning of the list
        cars.addFirst("Kia");
        System.out.println("kia should be at the beginning of list: " + cars);

        // Add an element to the end of the list
        cars.addLast("Nissan");
        System.out.println("Nissan should be at the end of list: " + cars);

        // Get the first element of the list
        String firstElement = cars.getFirst();
        System.out.println("First element in the list: " + firstElement);

        // Get the last element of the list
        String lastElement = cars.getLast();
        System.out.println("Last element in the list: " + lastElement);

        // Remove the first item from the list
        cars.removeFirst();
        System.out.println("kia should be removed from the list: " + cars);

        // Remove the last item from the list
        cars.removeLast();
        System.out.println("Nissan should be removed from the list: " + cars);











    }

    }
