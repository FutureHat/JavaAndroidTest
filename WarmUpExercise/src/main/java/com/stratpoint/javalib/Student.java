package com.stratpoint.javalib;
import java.util.Comparator;
public class Student {
    String name;
    int age;


    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Comparator<Student> studentName = new Comparator<Student>() {

        public int compare(Student student, Student t1) {
            String studentName1 = student.getName().toUpperCase();
            String studentName2 = t1.getName().toUpperCase();

            return studentName1.compareTo(studentName2);
        }
    };

    public static Comparator<Student> studentAge = new Comparator<Student>() {

        public int compare(Student student, Student t1) {
            int age1 = student.getAge();
            int age2 = t1.getAge();
            return age1-age2;
        }
    };

    @Override
    public String toString(){
        return "Student name: "+name+", Student age: "+age;
    }
}
