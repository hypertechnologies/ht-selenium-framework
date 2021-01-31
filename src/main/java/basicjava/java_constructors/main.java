package basicjava.java_constructors;

public class main {
    int x;
    String name;
    int age;

    // Constructor without parameter
    public main(){
        x = 5;
        name = "Tom";
    }

    // Constructor with parameter
    public main(String myName, int myAge){
        x = 5;
        name = myName;
        age = myAge;
    }


    public static void main(String[] args) {
        main obj = new main();

        int y = 1;
        System.out.println(obj.x);
        System.out.println(obj.name);
        System.out.println(y);


        main obj2 = new main("Nazmul", 30);
        System.out.println(obj2.name);
        System.out.println(obj2.age);
        System.out.println("My name is: " + obj2.name + " and my age is: " + obj2.age);

    }

}
