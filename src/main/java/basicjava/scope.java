package basicjava;

public class scope {
    public static void main(String[] args){

        // age can not be accessed here

        int age = 30;

        // age can be accessed here
        System.out.println(age);

        myAge(age);

    }
    // age can not be accessed from here


    public static void myAge(){
        // age can not be accessed from here directly
//        System.out.println("My age is: " + age);
    }

    public static void myAge(int age){
        // age can be accessed from here through parameters
        System.out.println("My age is: " + age);
    }

}
