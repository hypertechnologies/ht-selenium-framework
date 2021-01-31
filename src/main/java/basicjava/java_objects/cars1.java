package basicjava.java_objects;

public class cars1 {

    String name = "Toyota";
    String color = "Black";
    String x;

    public void myMethod(){
        System.out.println("I am a method from cars1");
    }
    public static void myMethod2(){
        System.out.println("This should not be able accessed from another class");
    }

}
