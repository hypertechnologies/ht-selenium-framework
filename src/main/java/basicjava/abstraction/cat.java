package basicjava.abstraction;

public class cat extends animal{
    // adding body to the abstract method
    public void animalSound(){
        System.out.println("Cat says mew mew!");
    }

    public void goToAWebsite(){
        System.out.println("This should go to a website");
    }

    public static void main(String[] args) {
        cat myObj = new cat();
        myObj.animalSound();
        myObj.goToAWebsite();
        myObj.anotherMethod();
    }

}
