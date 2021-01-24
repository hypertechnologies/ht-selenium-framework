package basicjava;

public class ifelse {
    public static void main(String[] args) {

        int val1 = 500;
        int val2 = 300;

        if(val1 < val2){
            System.out.println("val1 is less than val2");
        }else {
            System.out.println("val1 is grater than val2");
        }


        int time = 12;

        if(time < 10){
            System.out.println("Good Morning!");
        }else if(time < 20){
            System.out.println("Good day!");
        }else {
            System.out.println("Good night!");
        }


    }

}
