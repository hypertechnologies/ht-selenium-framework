package basicjava;

public class while_loop {
    public static void main(String[] args){
        // While loop


        int abc = 5;
        while(abc <= 10){
            System.out.println(abc); // 5 6 7 8 9 10
            ++abc;
        }

        int abc2 = 5;
        while(abc2 <= 50){
            System.out.println(abc2); //
            abc2 += 5;
        }


        // Do-While loop
        System.out.println("== Do-While ==");

        int abc3 = 11;
        do{
            System.out.println("do-while: " + abc3);
            ++abc3;
        }
        while (abc3 <= 10);

        // While
        int abc4 = 3;
        while(abc4 <= 10){
            System.out.println("while: " +abc4); //
            ++abc3;
        }

    }

    }
