package basicjava;

public class operators {
    public static void main(String[] args) {

        int num1 = 500;
        int num2 = 35;

        // Arithmetic Operators

        // Addition
        int numAdd = num1 + num2;
        System.out.println(numAdd); // 535

        // Subtraction
        int numSub = num1 - num2;
        System.out.println(numSub); // 465

        // Multiplication
        int numMult = num1 * num2;
        System.out.println(numMult); // 17500

        // Division
        int numDivInt = num1 / num2;
        System.out.println(numDivInt);

        // To divide two int value you need to cast them as float and then do the division.
        float numDiv = (float) num1 / (float) num2;
        System.out.println(numDiv);

        // Alternative is to define the original value as float
        float float1 = 500.0f;
        float float2 = 35.0f;

        float floatDiv = float1 / float2;
        System.out.println(floatDiv);

        // Modulus
        int x = 105;
        int y = 9;

        int remainder = x % y; // 6
        System.out.println(remainder);

        // Increment
        int val1 = 12;
        ++val1;
        System.out.println(val1); // 13

        // Decrement
        int val2 = 99;
        --val2;
        System.out.println(val2); // 98


        // Assignment Operators
        // Addition Assignment
        int val3 = 10;
        val3 += 15;
        System.out.println(val3); // 25

        // Subtraction Assignment
        int val4 = 50;
        val4 -= 15;
        System.out.println(val4); // 35

        // Multiplication Assignment
        int val5 = 10;
        val5 *= 5;
        System.out.println(val5); // 50

        // Division
        int val6 = 10;
        val6 /= 5;
        System.out.println(val6); // 2


        // Comparison Operators
        int num3 = 55;
        int num4 = 45;

        // If they are equal
        boolean result = (num3 == num4);
        System.out.println(result); // false

        // If they are not equal
        boolean result2 = (num3 != num4);
        System.out.println(result2); // true

        // If they are less than each other
        boolean result3 = (num3 < num4);
        System.out.println(result3); // false

        // If they are grater than each other
        boolean result4 = (num3 > num4);
        System.out.println(result4); // true

        // If they are less than or equal
        boolean result5 = (num3 <= num4); // equal is false, less than is false
        System.out.println(result5); // false

        // If they are grater than or equal
        boolean result6 = (num3 >= num4); // equal is false, grater than is true
        System.out.println(result6); // true

        // Logical Operator
        // first one is false, second is giving true. So the result is false;
        boolean result7 = (num3 == num4) && (num3 != num4);
        System.out.println(result7); // false

        // first one is false, second is giving true. So the result is true;
        boolean result8 = (num3 == num4) || (num3 != num4);
        System.out.println(result8); // true














    }
}
