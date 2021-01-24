package basicjava;

public class casting {

    public static void main(String[] args) {

        int val1 = 15;
        System.out.println("int val1 is: " + val1); // 15

        // Automatic Casting
        double myDouble = val1;
        System.out.println("automatic double val1 is: " + myDouble); // 15.0

        // Manual Casting
        double myDouble2 = (double) val1;
        System.out.println("manual double val1 is: " + myDouble2); // 15.0

        // Double to int
        double double1 = 25.75;

        // Manual Casting
        int myInt = (int) double1; // 25
        System.out.println("manual int myInt is: " + myInt); // 25



    }
}
