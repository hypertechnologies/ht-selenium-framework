package java_basic;

public class switch_statement {
    public static void main(String[] args){
        /*
        White a switch statement for the following -

        Print name of the day depending the day code.
         */

        int dayCode = 7;

        switch (dayCode){
            case 1:
                System.out.println("This is Monday");
                break;
            case 2:
                System.out.println("This is Tuesday");
                break;
            case 3:
                System.out.println("This is Wednesday");
                break;
            case 4:
                System.out.println("This is Thursday");
                break;
            case 5:
                System.out.println("This is Friday");
                break;
            case 6:
                System.out.println("This is Saturday");
                break;
            case 7:
                System.out.println("This is Sunday");
                break;
            default:
                System.out.println("dayCode must be between 1 to 7");
                break;
        }

    }


    }
