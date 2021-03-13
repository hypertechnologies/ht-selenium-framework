package java_basic;

public class for_loop {
    public static void main(String[] args){

        /*
        int abc = 5;
        while(abc <= 10){
            System.out.println(abc); // 5 6 7 8 9 10
            ++abc;
        }
         */

        for(int abc = 5; abc <= 10; ++abc){
            System.out.println(abc); // 5 6 7 8 9 10
        }

        for(int i = 0; i < 5; i++){
            System.out.println("printing it 5 times " + i);
        }

        // Break and continue

        for(int i = 0; i < 5; i++){
            if(i == 3){
                break;
            }
            System.out.println("Break printing it at 3rd times " + i);
        }

        for(int i = 0; i < 5; i++){
            if(i == 3){
                continue;
            }
            System.out.println("Continue printing it 5 times " + i);
        }

    }

    }
