package basicjava.java_encapsulation;

public class main {
    private String creditCardNumber = "123456789";

    //Getter
    // method name should start with "get" followed by
    // name of the private variable and first letter capital
    public String getCreditCardNumber(){
        return creditCardNumber;
    }

    //Setter
    // to update a private variable
    public void setCreditCardNumber(){
        this.creditCardNumber = "987654321";
    }

    // Setter with parameters
    public void setCreditCardNumber(String anotherNumber){
        this.creditCardNumber = anotherNumber;
    }


    public static void main(String[] args) {
        main obj = new main();
        System.out.println(obj.creditCardNumber);
    }
}
