package homework;

public class week1 {

    public static void main(String[] args){

//        1. Write a COMMENT “This is Java Homework”? (C)
        // This is Java Homework
        /*
        This is Java Homework
        This is Java Homework
        This is Java Homework
        This is Java Homework
         */
        int age = 50; // This is for age

//        2. Write  the code to output “I like Java” (C)
        System.out.println("I like Java");

//        3. Which data type is used to create a variable that should store text?
        // Ans is String
        String x = "I like Java 123";
        String y = "123";

//        4. How do you create a variable with the numeric value 15? (C)
        int val1 = 15;

//        5. How do you create a variable with the floating number 32.8? (C)
        float val2 = 32.8f;

//        6. How do you create a variable with the long number 958796543? (C)
        long long1 = 958796543L;

//        7. True or False? The value of a String variable can be surrounded by single quotes.
        // False

//        8. True or False? The value of a char variable can be surrounded by single quotes.
        // True
        char c = 'F';

//        9. What is the difference between = (single equal) and == (double equal)?
        // Single equal is to assign the value and double equal is to compare between two values

//        10. Cast/convert int value of 55 to double. (C)
        int val3 = 15;
        double d1 = val3;
        System.out.println(d1);


//        11. Cast/convert float value 20.55f to int. (C)
        float f1 = 20.55f;
        int x2 = (int)f1;
        System.out.println(x2);

//        12. Divide int value of 55 by int value of 6. (C)
        int val4 = 55;
        int val5 = 6;

        double d3 = (double) val4 / (double) val5;
        System.out.println(d3);

//        13. Decrement int value of 34. (C)
        int val6 = 34;
        --val6;
        System.out.println(val6);


//        14. What does != and <= means?
        // != is opposite of ==, means not equal, <= means less than or equal

//        15. What does && and || means?
        // && means logical AND, || means logical OR operator

//        16. Return boolean (True or False) if int value of 55 is less than int value of 44. (C)
        int val7 = 55;
        int val8 = 44;
        boolean bool1 = val7 < val8;
        System.out.println(bool1);

//        17. Find Squire Root for int value of 500. (C)
        int val9 = 500;
        double d2 = Math.sqrt(val9);
        System.out.println(d2);

//        18. Find max value from two int values 300 and 400. (C)
        int val10 = 300;
        int val11 = 400;
        int max = Math.max(val10,val11);
        System.out.println(max);

//        19. Find random number between 0 to 50 (C)
        double d4 = Math.random() * 50;
        int val12 = (int)(Math.random() * 50);
        System.out.println(d4);
        System.out.println(val12);


//        20. White an if-else statement for the following - (C)
//
//        Assign grades (A, B, C, F) based on percentage obtained by a student.
//        * if the percentage is 90 or above, assign grade A
//        * if the percentage is above 75, assign grade B
//        * if the percentage is above 65, assign grade C
//        * if the percentage is 65 or below, assign grade F

        int score = 75;
        char grade = 'F';

        if (score >= 90){
            grade = 'A';
        }else if(score > 75){
            grade = 'B';
        }else if(score > 65){
            grade = 'C';
        }else{
            grade = 'F';
        }

        System.out.println(grade);




    }
}
