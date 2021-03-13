package java_basic;

public class recursion {
    public static void main(String[] args) {

        // Write a recursion method to add 1 to 100. Result should be 5050
        int result = sum(100);
        System.out.println("recursion sum is: " + result);


    }

    public static int sum(int num){
        if (num > 0){
            return num + sum(num - 1);
        }else {
            return 0;
        }

    }


}
