package basicjava.java_encapsulation;

public class main2 {
    public static void main(String[] args) {
        main obj = new main();
//        System.out.println(obj.creditCardNumber); // Can not be directly accessed
        System.out.println(obj.getCreditCardNumber());

        obj.setCreditCardNumber();
        System.out.println(obj.getCreditCardNumber());

        // Passing credit card number from another class
        obj.setCreditCardNumber("500060000");
        System.out.println(obj.getCreditCardNumber());


    }

    }
