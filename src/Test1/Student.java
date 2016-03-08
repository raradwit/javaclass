package Test1;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: SACHIN
 * Date: 3/8/2016.
 */
public class Student {

    public Student() {
    }

    public enum Gender {
        MALE, FEMALE
    }

    private String name;
    private int age;
    private Marks marks;

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

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public Student(String name, int age, Marks marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public static List<Student> studentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Sachin Aryal",21,new Marks(48.0,75.5,85.5,56.8)));
        studentList.add(new Student("Sumit Shrestha",19,new Marks(44.0,35.5,45.5,56.8)));
        studentList.add(new Student("Sameer Koirala",25,new Marks(40.0,35.5,85.5,56.8)));
        return studentList;
    }
}