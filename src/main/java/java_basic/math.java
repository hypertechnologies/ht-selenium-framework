package java_basic;

public class math {
    public static void main(String[] args) {
        int val1 = 200;
        int val2 = 175;

        // Find max number
        int maxNum = Math.max(val1, val2);
        System.out.println("max number is: " + maxNum); // 200

        // Find min number
        int minNum = Math.min(val1, val2);
        System.out.println("min number is: " + minNum); // 175

        // Squire Root
        double sqrRoot = Math.sqrt(val1);
        System.out.println("Squire Root is: " + sqrRoot); // 14.14

        // Find absolute positive value
        int val3 = -500;
        int posVal = Math.abs(val3);
        System.out.println("Positive value is: " + posVal); // 500

        // Find random Number
        double randomNum = Math.random();
        System.out.println("Random Number is: " + randomNum);

        // find random number between 0 to 100
        double randomNum2 = Math.random() * 100;
        System.out.println("Random double Number between 0 to 100 is: " + randomNum2);
        int randomNum3 = (int) (Math.random() * 100);
        System.out.println("Random int Number between 0 to 100 is: " + randomNum3);



    }
}
