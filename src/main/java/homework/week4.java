package homework;

import java.util.HashMap;
import java.util.HashSet;

public class week4 {
    public static void main(String[] args) {

//    1. Given there are following two arrays which contains employeeId and jobTitles.
        Integer[] employeeIds = {1001, 1002, 1003, 1004, 1005};
        String[] jobTitles = {"Software Engineer", "Web Designer", "Software Tester", "Scrum Master", "Engineering Manager"};
//
//        A. Write a hashmap to store the above employeeIds and employeeTitles as key-value.
//         For example, 1001 = Software Engineer (Use a loop to add them)

        HashMap<Integer, String> employeesInfo = new HashMap<Integer, String>();
        //employeesInfo.put(1001, "Software Engineer");
        System.out.println(employeesInfo);

        for(int i = 0; i < employeeIds.length;  i++){
            employeesInfo.put(employeeIds[i], jobTitles[i]);
            System.out.println(employeesInfo);
        }
        System.out.println(employeesInfo);


//        B. Print the jobTitle of the employeeId 1003
        System.out.println(employeesInfo.get(1003));

//        C. Remove the entry where employeeId is 1004
        employeesInfo.remove(1004);

//        D. Print the size of the hashmap
        System.out.println(employeesInfo.size());


//        E. Write a for-each loop to print all employeeIds and jobTitles

        for(Integer key : employeesInfo.keySet()){
            System.out.println("Key is: " + key);
            System.out.println("Value is: " + employeesInfo.get(key));
        }


//        F. Clear all data in hashmap

        employeesInfo.clear();
        System.out.println(employeesInfo);

//
//
//    2. Given there is following array which contains customerIds from an order table.
//    Some customers placed multiple orders that's why there are duplicate customerIds.
        Integer[] customerIds = {105, 102, 101, 101, 110, 105, 110, 101, 103, 109};
//
//        A. Write a hashset to store all of the customerIds to remove duplicates and save uniques customerIds
//        who placed order (use a loop to add them)
        HashSet<Integer> cIds = new HashSet<Integer>();

        for(int j = 0; j < customerIds.length; j++){
            cIds.add(customerIds[j]);
        }

        System.out.println(cIds);

//        B. If hashset contains customerId 115 then remove customerId 115, else remove customerId 101.

        if(cIds.contains(115)){   // If hashset contains customerId 115
            cIds.remove(115);  // then remove customerId 115
        }else {                   // else
            cIds.remove(101);  // remove customerId 101.
        }


//        C. Write a for-each loop to print all customerIds.

        for(Integer i : cIds){
            System.out.println(i);
        }

//
//    3. How do you create an object of another class?
        week3 myObj = new week3();


//    4. What is the rule to write a constructor and why do we use constructor?
//    5. What is encapsulation and why do use it?
//    6. What are the rules to write getter and setter method name?
//    7. What is Inheritance? What is the syntax to inherit class name "institution" into another class called "college"?
//    8. What is polymorphism? Give an example of polymorphism.
//    9. What is abstraction and how do you declare an abstract method?
//    10. What is Interface?
//    11. How do you declare an interface class? What is the syntax to implement the interface in another class?


    }

}
