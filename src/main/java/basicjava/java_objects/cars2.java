package basicjava.java_objects;

public class cars2 {

    public static void main(String[] args) {
        cars1 myObj = new cars1();
        System.out.println(myObj.name);
        System.out.println(myObj.color);

        myObj.myMethod();
        method1();
    }

    public static void method1(){
        System.out.println("I am in cars2 class");
    }

}


