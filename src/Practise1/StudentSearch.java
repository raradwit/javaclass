package Practise1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * Created by sachin on 2/28/2016.
 */
public class StudentSearch {
    interface StudentCondition{
        boolean check(Student student);
    }

    interface GenAndSemCondition{
        boolean CheckGenderAndSemester(Student.Gender gender, int semester);

    }

    public static void printStudentWithCondition(List<Student> stdList, StudentCondition condition){
        for (Student std : stdList) {
            if (condition.check(std)) {
                std.printStudent();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Callable<List<Student>> studentList = Student::populateList;
        System.out.println("Female Students");
        System.out.println("---------------------------------------");
        printStudentWithCondition(studentList.call(), student -> {
            Predicate<Student.Gender> isFemale = gender -> gender.equals(Student.Gender.FEMALE);
            return isFemale.test(student.gender);
        });

        System.out.println("Male Students and Fourth Semester");
        System.out.println("---------------------------------------");

        StudentCondition condition = (Student stdeunt)-> stdeunt.gender.equals(Student.Gender.MALE) && stdeunt.semester==4;
        printStudentWithCondition(studentList.call(),condition);


        System.out.println("All Students");
        System.out.println("------------------------------------------");
        studentList.call().forEach(Student::printStudent);



        }
}
