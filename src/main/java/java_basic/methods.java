package java_basic;

public class methods {
    public static void main(String[] args){
        System.out.println("I am inside the main method");
        myName("Nazmul", "Islam");
        myAge(60);
        System.out.println(fruit());
        System.out.println(fruit2("Orange"));
        System.out.println(fruit2("Mango", "Sour"));
    }

    // void methods
    public static void myName(String firstName, String lastName){
        System.out.println("My name is: " + firstName + " " + lastName);
    }

    public static void myAge(int age){
        System.out.println("My age is: " + age);
    }

    // String method
    public static String fruit(){
        System.out.println("I am returning a fruit");
        return "Banana";
    }

    public static String fruit2(String fruit){
        return fruit + " is sweet!";
    }

    // Method overloading
    public static String fruit2(String fruit, String taste){
        return fruit + " is " + taste;
    }


}
