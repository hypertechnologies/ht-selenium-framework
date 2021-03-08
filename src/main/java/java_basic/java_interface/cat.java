package java_basic.java_interface;

public class cat implements animal{
    public void animalSound(){
        System.out.println("Cat Says mew mew!");
    }

    public void goToAWebsite(){
        System.out.println("This should go to a website");
    }


    public static void main(String[] args) {
        cat myObj = new cat();
        myObj.animalSound();
        myObj.goToAWebsite();
    }

}
