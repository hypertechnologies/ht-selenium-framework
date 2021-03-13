package java_basic.polymorphism;

public class cat extends animal{

    public void animalSound(){
        System.out.println("Cat says mew mew!");
    }


    public static void main(String[] args) {
        cat myObj = new cat();

        myObj.animalSound();
        myObj.animalColor();

        animal animalObj = new animal();
        animalObj.animalSound();


    }
}
