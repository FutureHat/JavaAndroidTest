package com.stratpoint.javalib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WarmUpExercises {
    public static class WarmUpExercise {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            //Input of word/s to check if palindrome
            System.out.print("Input word/s to check if palindrome: ");
            StringBuilder input = new StringBuilder(scan.nextLine().replaceAll(" ", ""));
            //Calling the Palindrome Function and show it if it is a palindrome or not
            System.out.println(Palindrome(input));
            System.out.println();

            //Sorting the value of List by Name and Age
            ArrayList<Student> student = new ArrayList<>();
            student.add(new Student("Jonas", 26));
            student.add(new Student("Walter Van", 20));
            student.add(new Student("Rey", 40));
            student.add(new Student("Jasper", 55));
            student.add(new Student("Kel", 18));
            //Function to sort the student name
            sortStudentListByName(student);
            //Function to sort the student age
            sortStudentListByAge(student);

            //Input of number to check if prime or not prime
            System.out.print("Input number to check if prime or not prime: ");
            int number = scan.nextInt();
            //Calling the Prime Function to show if the number is prime or not a prime number
            System.out.println(Prime(number));
            System.out.println();


        }
        //Palindrome Function
        static String Palindrome(StringBuilder palindrome) {

            if(palindrome.toString().equalsIgnoreCase(palindrome.reverse().toString())){
                return "It is a palindrome";
            }
            else return "Not a palindrome";

        }

        //Prime Function
        static String Prime(int number){
            boolean prime = false;
            if(number > 0) {
                for (int x = 2; x <= number; x++) {
                    if (number % x == 0 && number != x) {
                        prime = true;
                        break;
                    }
                }
            }
            else{
                prime = true;
            }

            if(!prime)
                return number+" is a prime number";
            else
                return number+" is not a prime number";


        }

        //Function to sort Students by name
        public static void sortStudentListByName(ArrayList<Student> students){
            Collections.sort(students, Student.studentName);
            System.out.println("Students sorted by name: ");
            //Printing the records
            for(Student student: students){
                System.out.println(student);
            }
            System.out.println();


        }

        //Function to sort Students by age
        public static void sortStudentListByAge(ArrayList<Student> students){
            Collections.sort(students, Student.studentAge);
            System.out.println("Students sorted by age: ");
            //Printing the records
            for(Student student: students){
                System.out.println(student);

            }
            System.out.println();
        }

    }
}
