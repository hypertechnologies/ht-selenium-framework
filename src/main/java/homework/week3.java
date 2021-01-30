package homework;

import java.util.ArrayList;
import java.util.LinkedList;

public class week3 {
    public static void main(String[] args) {

//        1. Write an arrayList to -
//            A. store number between 1 to 20 (use a loop to add all numbers)
        ArrayList<Integer> nums = new ArrayList<Integer>();

        for (int i = 1; i <= 20; i++){
            nums.add(i);
        }

//            B. print the size of the arrayList
        int size = nums.size();
        System.out.println("Size of nums is: " + size);


//            C. print number 15 from the arrayList
        System.out.println("Should print Number 15: " + nums.get(14));

//            D. update number 13 to 85
        nums.set(12, 85);

//            E. remove number 11 from the arrayList
        nums.remove(10);

//            F. print all numbers using a for loop
        for(int j = 0; j < nums.size(); j++){
            System.out.println(nums.get(j));
        }

//            G. clear the arrayList
        nums.clear();
//
//        2. Write an linkedList that contains chapter name of a book (ex, Chapter 1, Chapter 2, Chapter 3 etc.) -
//            A. add 10 chapter names (use a loop to add them)
        LinkedList<String> book = new LinkedList<String>();

        for(int k = 1; k<= 10; k++){
            book.add("Chapter " + k);
        }


//            B. Add "Introduction" at the beginning of the linkedList
        book.addFirst("Introduction");

//            C. Add "Conclusion" at the end of the linkedList
        book.addLast("Conclusion");

//            D. print all chapter names using a for-each loop
        for(String chapterName : book){
            System.out.println(chapterName);
        }


//            E. print the first chapter name
        System.out.println("First Chapter name is: " + book.getFirst());

//            F. print the last chapter name
        System.out.println("Last Chapter name is: " + book.getLast());

//            G. print chapter 5
        System.out.println("Fifth Chapter name is: " + book.get(4));

//            H. remove last chapter name
        book.removeLast();

//            I. remove first chapter name
        book.removeFirst();


    }




}
