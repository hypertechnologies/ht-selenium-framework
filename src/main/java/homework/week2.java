package homework;

public class week2 {
    public static void main(String[] args) {
//        1. Suppose there are 5 age groups - A, B, C, D, E. Based on the age group write a switch statement to print the following.
//                A = You are a baby!
//                B = You are a toddler!
//                C = You are a teen!
//                D = You are an adult!
//                E = You are old!

        char ageGroup = 'A';
        switch (ageGroup){
            case 'A':
                System.out.println("You are a baby!");
                break;
            case 'B':
                System.out.println("You are a toddler!");
                break;
            case 'C':
                System.out.println("You are a teen!");
                break;
            case 'D':
                System.out.println("You are an adult!");
                break;
            case 'E':
                System.out.println("You are old!");
                break;
            default:
                System.out.println("Age group must be between A to E!");
                break;
        }

//        2. Add 1,2,3... to 100 using a while loop. [The answer should be 5050]

        int i = 1;
        int sum = 0;
        while (i <= 100){
            sum = sum + i;
//            System.out.println(i);
            i++;
        }
        System.out.println("Sum is: " + sum);

//        3. Add 1,3,5... to 99 using a do-while loop. [The answer should be 2500]
        int j = 1;
        int sum2 = 0;

        do{
            sum2 = sum2 + j;
//            System.out.println(j);
            j += 2; // j = j + 2;
        }
        while (j <= 99);
        System.out.println("Sum2 is: " + sum2);


//        4. Write an array that contains 5 different city names. Write a for loop to print all of the city names.
        String[] cities = {"Montreal", "New York", "Dhaka", "London", "Texas"};
//        System.out.println(cities[0]);

        for(int k = 0; k < cities.length; k++){
            System.out.println("for loop: " + cities[k]);
        }


//        5. Also write a for-each loop to print all of the city names for the above array.

        for(String city: cities){
            System.out.println("for-each loop: " + city);
        }

//        6. Write a for loop to go through 1 to 20. Terminate the loop when the loop reaches 15 and print it. [Should print 15]
            for (int m = 1; m <=20; m++){
                if(m == 15){
                    System.out.println("15th value: " + m);
                    break;
                }
            }

//        7. Write a for loop to go through 1 to 5. Print all numbers but 3 (skip 3). [Should print 1,2,4,5]
        for(int n = 1; n <= 5; n++){
            if(n == 3){
                continue;
            }
            System.out.println(n);
        }

//
//        8. Write a for loop to go through 0 to 100 and add the numbers that are divisible by 5. [Should add 5+10+15...+95+100 and Answer should 1050]
//
        int sum3 = 0;

        for(int p = 0; p <=100; p++){
            if(p % 5 == 0){
                sum3 = sum3 + p;
            }
        }
        System.out.println("sum3 is: " + sum3);


        multiply();

        int result = sum( 500,250);

        System.out.println("sum result is: " + result);
        System.out.println("sum2 result is: " + sum2( 15,10));
    }

//        9. Write a method that can multiply two random numbers (between 0 to 100) and print the result.
    public static void multiply(){
        double num1 = Math.random() * 100;
        double num2 = Math.random() * 100;

        double result = num1 * num2;
        System.out.println("multiply method result is: " + result);
    }


//        10. Write a method that can take two integer values as parameters and return (output) their sum.
    public static int sum(int num3, int num4){
        int sum4 = num3 + num4;
        return sum4;
    }

    public static int sum2(int val1, int val2){
        return val1 + val2;
    }


}
