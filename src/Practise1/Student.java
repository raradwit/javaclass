package Practise1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sachin on 2/28/2016.
 */
public class Student {
    public enum Gender {
        MALE, FEMALE
    }

    String name;
    int semester;
    Gender gender;
    String emailAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Student(String nameArg, int semester,
            Gender genderArg, String emailArg) {
        name = nameArg;
        this.semester = semester;
        gender = genderArg;
        emailAddress = emailArg;
    }

    public static List<Student> populateList() {

        List<Student> stdCollection = new ArrayList<>();
        stdCollection.add(
                new Student(
                        "Practise2.Student one",
                        1,
                        Gender.MALE,
                        "std1@deerwalk.com"));
        stdCollection.add(
                new Student(
                        "Practise2.Student two",
                        2,
                        Gender.FEMALE, "std2@deerwalk.com"));
        stdCollection.add(
                new Student(
                        "Practise2.Student three",
                        3,
                        Gender.MALE, "std3@deerwalk.com"));
        stdCollection.add(
                new Student(
                        "Practise2.Student four",
                        4,
                        Gender.MALE, "stdthree@deerwalk.com"));

        stdCollection.add(
                new Student(
                        "Practise2.Student five",
                        5,
                        Gender.FEMALE, "std4@deerwalk.com"));


        return stdCollection;
    }





    public void printStudent(){
        System.out.println("Name: "+name+"\n"+"Semester: "+semester+"\n"+"Gender: "+gender+"\n"+"Email: "+emailAddress);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

}
