package Practise2;

import java.util.List;

/**
 * Created by spandey on 2/22/16.
 */
public class StudentSearch {

    interface StudentCondition{
        boolean check(Student student);
    }

    public static void printStudentWithCondition(List<Student> stdList,StudentCondition condition){
        for (Student std : stdList) {
            if (condition.check(std)) {
                std.printStudent();
            }
        }
    }

    static class ImplementingInt implements StudentCondition{

        @Override
        public boolean check(Student student) {
            return student.getGender() == Student.Gender.FEMALE &&
                    student.semester.equals("4th");
        }
    }



    public static void printStudentIn2ndSemester(List<Student> stdList,String semester){
        for (Student std : stdList) {
            if (std.semester ==semester) {
                std.printStudent();
            }
        }
    }

    public static void main(String... args){
        final List<Student> stdList = Student.populateList();

        for (Student std : stdList) {
            std.printStudent();
        }

        System.out.println("Students in 2nd semester");
        printStudentIn2ndSemester(stdList, "2nd");
        System.out.println();
        printStudentWithCondition(stdList,new ImplementingInt());
        printStudentWithCondition(stdList, new StudentCondition() {
            @Override
            public boolean check(Student student) {
                return student.getGender() == Student.Gender.FEMALE &&
                        student.semester.equals("4th");
            }
        });

        System.out.println("Practise2.Student Using Lamda Expression");

        Student.MathOperation addition = (int a,int b)->a+b;

//        printStudentWithCondition(stdList,(Practise2.Student s)->s.getGender()==Practise2.Student.Gender.FEMALE
//        &&s.semester=="4th");
    }
}
