package Practise2; /**
 * Created by spandey on 2/22/16.
 */
import java.util.ArrayList;
import java.util.List;

public class Student {

    public enum Gender {
        MALE, FEMALE
    }

    String name;
    String semester;
    Gender gender;
    String emailAddress;

    Student(String nameArg, String semester,
           Gender genderArg, String emailArg) {
        name = nameArg;
        this.semester = semester;
        gender = genderArg;
        emailAddress = emailArg;
    }

    

    public void printStudent() {
        System.out.println(name + ", " + semester);
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }



    public static List<Student> populateList() {

        List<Student> stdCollection = new ArrayList<Student>();
        stdCollection.add(
                new Student(
                        "Practise2.Student one",
                        "1st",
                        Gender.MALE,
                        "std1@deerwalk.com"));
        stdCollection.add(
                new Student(
                        "Practise2.Student two",
                        "2nd",
                        Gender.FEMALE, "std2@deerwalk.com"));
        stdCollection.add(
                new Student(
                        "Practise2.Student three",
                        "2nd",
                        Gender.MALE, "std3@deerwalk.com"));
        stdCollection.add(
                new Student(
                        "Practise2.Student 3",
                        "3rd",
                        Gender.MALE, "stdthree@deerwalk.com"));

        stdCollection.add(
                new Student(
                        "Practise2.Student four",
                        "4th",
                        Gender.FEMALE, "std4@deerwalk.com"));


        return stdCollection;
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

}
