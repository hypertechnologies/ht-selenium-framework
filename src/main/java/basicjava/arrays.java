package basicjava;

public class arrays {
    public static void main(String[] args){
        String fruit = "apple";
        String[] fruits = {"apple", "banana", "orange"};

        int age = 40;
        int[] ages = {14, 22, 35, 40, 65, 80, 90};

        // Accessing individual value of array by index number
        System.out.println(age);
        System.out.println("First value of array: " + ages[0]);
        System.out.println("5th value of array: " + ages[4]);
        System.out.println("3th value of fruits array : " + fruits[2]);

        // Run time error - Index Out of Bound
        // This happens if you try to access index value inside array that does not exist.
        // System.out.println("4th value of fruits array : " + fruits[3]);


        //Length of an array
        int fruitsLength = fruits.length;
        System.out.println("Length of fruits array: " + fruitsLength);

        int agesLength = ages.length;
        System.out.println("Length of ages array: " + agesLength);

        // Print al the values inside array
//        String[] fruits = {"apple", "banana", "orange"};

        System.out.println("Printing all the elements of fruits");
        for(int i = 0; i < fruits.length; i++){
            System.out.println(fruits[i]);
        }

        System.out.println("Printing all the elements of ages");
//        int[] ages = {14, 22, 35, 40, 65, 80, 90};

        for(int j = 0; j < ages.length; j++){
            System.out.println(ages[j]);
        }

        System.out.println("Printing all the elements of fruits using for-each loop");
        //        String[] fruits = {"apple", "banana", "orange"};
        for(String fr : fruits){
            System.out.println(fr);
        }


        int i = 1;
        int sum = 0;

        while (i <= 100) {
            sum = sum+i;
            sum += i;
            System.out.println(i);
            i++;
        }


    }


    }
